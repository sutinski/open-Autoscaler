 #!/bin/bash
source ${basedir}/script/utils.sh

function createProfile(){
read -p " >>> Define customized Maven profile name: (default: test): " profile
if [ -z "$profile" ]; then
	profile="test"
fi
profileExist=true;
AutoScalingServerProfileDIR="${basedir}/../$AutoScalingServerProjectDirName/profiles/"
if [ ! -f ${AutoScalingServerProfileDIR}/$profile.properties ]; then
	cp ${AutoScalingServerProfileDIR}/${SampleProfile}.properties ${AutoScalingServerProfileDIR}/$profile.properties
	profileExist=false;
fi

AutoScalingAPIProfileDIR="${basedir}/../$AutoScalingAPIProjectDirName/profiles/"
if [ ! -f ${AutoScalingAPIProfileDIR}/$profile.properties ]; then
	cp ${AutoScalingAPIProfileDIR}/${SampleProfile}.properties ${AutoScalingAPIProfileDIR}/$profile.properties
	profileExist=false;
fi

AutoScalingBrokerProfileDIR="${basedir}/../$AutoScalingBrokerProjectDirName/profiles/"
if [ ! -f ${AutoScalingBrokerProfileDIR}/$profile.properties ]; then
	cp ${AutoScalingBrokerProfileDIR}/${SampleProfile}.properties ${AutoScalingBrokerProfileDIR}/$profile.properties
	profileExist=false;
fi

RuntimeProfileDIR="${basedir}/../$BinDirName/profiles/"
if [ ! -f ${RuntimeProfileDIR}/$profile.properties ]; then
	cp ${RuntimeProfileDIR}/${SampleProfile}.properties ${RuntimeProfileDIR}/$profile.properties
	profileExist=false;
fi


if [ "$profileExist" == "false" ]; then
	echo " >>> Please paste the following into ~/.m2/settings.xml <profiles> section"  
	echo "	<profile>
            	<id>$profile</id>
            	<properties>
                	<build.profile.id>$profile</build.profile.id>
            	</properties>
        </profile>
    	"
	echo " >>> Press any key to continue ..." 
	read input
fi

}


function configure_basic_info() {
	echo
	echo " >>> To scale applications on Cloudfoundry, $componentName need to be registered as a service on the target Cloudfoundry "

	echo " >>> Please provide required information for the target Cloudfoundry"
	cfUrl=`readConfigValue cfUrl "Cloudfoundry API URL in which you want to register $serviceName service "`
	cfDomain=${cfUrl##"api."}
	cfClientID=`readConfigValue cfClientId "CF Client ID for $cfDomain"`
	cfClientSecret=`readConfigValue cfClientSecret "CF Client Secret for $cfDomain"`

	echo " >>> Security configuration of $componentName : "
	brokerUsername=`readConfigValue brokerUsername "Define broker username to register service"`
	brokerPassword=`readConfigValue brokerPassword "Define broker password to register service"`

	internalAuthUsername=`readConfigValue internalAuthUsername "Define $componentName username for internal authorization"`
	internalAuthPassword=`readConfigValue internalAuthPassword "Define $componentName password for internal authorization"`

}

function configure_deployment_info {

	promptHint ">>> Please decide how $componentName will be hosted:"
	echo " 1. Deploy as cloudfoundry applications "
	echo " 2. Deploy with bosh release (bosh-lite) "
	echo " 3. Deploy on local environment manually "

	while true; do
    	read -p " >>> Please enter your choice: " input
    	case $input in
        [1]* )  deploymode="cfapp"
 				load_cf_deployment_info
 				load_couchdb_info
 				break;;
        [2]* )  deploymode="bosh"
 				load_bosh_deployment_info
 				break;;
 	[3]* )  deploymode="localenv"
 				load_local_deployment_info 
 				load_couchdb_info
 				break;;		
        * ) echo "Invalid option";;
    	esac
	done

}

function bosh_obsolete_load_bosh_deployment_info() {

	bosh_stub_file=${basedir}/../bosh-release/stubs/boshlite-stub.yml
	bosh_director_uuid=`bosh status --uuid`;
	default_bosh_stemcell_name=`cat $bosh_stub_file | grep stemcell_name | awk '{print $2}'`
	bosh_stemcell_name=`readConfigValue bosh_stemcell_name "The bosh stellcell name to host $componentName" $default_bosh_stemcell_name`
	default_bosh_subnet=`cat $bosh_stub_file | grep network_subnet | awk '{print $2}'`.*
	bosh_subnet=`readConfigValue bosh_subnet "The network subnet to host $componentName" $default_bosh_subnet | sed -e 's/\.\*$//g'`

	if [ "$SHELL" == "mac" ]; then
			sed -i '' "s/director_uuid:.*/director_uuid: $bosh_director_uuid/g" $bosh_stub_file
			sed -i '' "s/stemcell_name:.*/stemcell_name: $bosh_stemcell_name/g" $bosh_stub_file
			sed -i '' "s/network_subnet:.*/network_subnet: $bosh_subnet/g" $bosh_stub_file
	else
			sed -i "s/director_uuid:.*/director_uuid: $bosh_director_uuid/g" $bosh_stub_file
			sed -i "s/stemcell_name:.*/stemcell_name: $bosh_stemcell_name/g" $bosh_stub_file
			sed -i "s/network_subnet:.*/network_subnet: $bosh_subnet/g" $bosh_stub_file
	fi
 	brokerURI="http:\/\/"${bosh_subnet}".2\/servicebroker"; 
 	apiServerURI="http:\/\/"${bosh_subnet}".3\/api"; 
	serverURIList="http:\/\/"${bosh_subnet}".4\/server";
 	couchdbHost=${bosh_subnet}".5"
	couchdbPort=5984
	couchdbUsername=
	couchdbPassword=

}

function load_bosh_deployment_info() {

	role_manifest_file=${basedir}/../../../../container-host-files/etc/hcf/config/role-manifest.yml
	brokerURI="http://autoscaler-servicebroker:28863/servicebroker"
 	apiServerURI="http://autoscaler-api:28861/api"
	serverURIList="http://autoscaler-server:28862/server"
 	couchdbHost=couchdb
	couchdbPort=5984
	couchdbUsername=
	couchdbPassword=

}

function load_local_deployment_info() {

	echo " >>> To host $componentName on local envrionment,  please provide the access information for $componentName applications:  : "
	brokerURI=`readConfigValue brokerURI "$componentName service broker url"`
	apiServerURI=`readConfigValue apiServerURI "$componentName API url"`;
 	serverURIList=`readConfigValue serverURIList "$componentName Server url list"`;

}



function load_cf_deployment_info() {
 
	echo " >>> To host $componentName on Cloudfoundry, please provide deployment information for Cloudfoundry:  "
 	hostingCFDomain=`readConfigValue hostingCFDomain "CF domain to host $componentName applications" $cfDomain`;
 	hostingCustomDomain=`readConfigValue hostingCustomDomain "CF custom domain of the hosting applications " $cfDomain`;
 	brokerURI="http:\/\/${AutoScalingBrokerName}.$hostingCustomDomain"
 	apiServerURI="$AutoScalingAPIName.$hostingCustomDomain"; 
 	serverURIList="$AutoScalingServerName.$hostingCustomDomain";

	org=`readConfigValue org "CF Org to host $componentName"`
	space=`readConfigValue space "CF Space to host $componentName"`
 	cfUsername=`readConfigValue cfUsername "CF Username to access $org/$space "`
	cfPassword=`readConfigValue cfPassword "CF Password to access $org/$space "`

}

function load_couchdb_info() {
 
	echo " >>> Please enter access info for external couchdb:  "
	couchdbHost=`readConfigValue couchdbHost "Hostname of Couchdb"`
	couchdbPort=`readConfigValue couchdbPort "Port of Couchdb"`
	couchdbUsername=`readConfigValue couchdbUsername "Username of Couchdb"`
	couchdbPassword=`readConfigValue couchdbPassword "Password of Couchdb"`
}



function setConfiguration() {

setProfileProperties ${RuntimeProfileDIR} deploymode ${deploymode} 
setProfileProperties ${RuntimeProfileDIR} brokerURI ${brokerURI}

if [ $deploymode == "cfapp" ]; then
	setProfileProperties ${RuntimeProfileDIR} hostingCFDomain ${hostingCFDomain}
	setProfileProperties ${RuntimeProfileDIR} hostingCustomDomain ${hostingCustomDomain}
	setProfileProperties ${RuntimeProfileDIR} org ${org}
	setProfileProperties ${RuntimeProfileDIR} space ${space}
	setProfileProperties ${RuntimeProfileDIR} cfUsername ${cfUsername}
	setProfileProperties ${RuntimeProfileDIR} cfPassword ${cfPassword}
fi

setProfileProperties ${AutoScalingServerProfileDIR} cfUrl ${cfUrl}
setProfileProperties ${AutoScalingServerProfileDIR} cfClientId ${cfClientID}
setProfileProperties ${AutoScalingServerProfileDIR} cfClientSecret ${cfClientSecret}
setProfileProperties ${AutoScalingServerProfileDIR} couchdbHost ${couchdbHost}
setProfileProperties ${AutoScalingServerProfileDIR} couchdbPort ${couchdbPort}
setProfileProperties ${AutoScalingServerProfileDIR} couchdbUsername ${couchdbUsername}
setProfileProperties ${AutoScalingServerProfileDIR} couchdbPassword ${couchdbPassword}
setProfileProperties ${AutoScalingServerProfileDIR} internalAuthToken ${internalAuthToken}


setProfileProperties ${AutoScalingAPIProfileDIR} cfUrl ${cfUrl}
setProfileProperties ${AutoScalingAPIProfileDIR} cfClientId ${cfClientID}
setProfileProperties ${AutoScalingAPIProfileDIR} cfClientSecret ${cfClientSecret}
setProfileProperties ${AutoScalingAPIProfileDIR} internalAuthUsername ${internalAuthUsername}
setProfileProperties ${AutoScalingAPIProfileDIR} internalAuthPassword ${internalAuthPassword}

setProfileProperties ${AutoScalingBrokerProfileDIR} couchdbHost ${couchdbHost}
setProfileProperties ${AutoScalingBrokerProfileDIR} couchdbPort ${couchdbPort}
setProfileProperties ${AutoScalingBrokerProfileDIR} couchdbUsername ${couchdbUsername}
setProfileProperties ${AutoScalingBrokerProfileDIR} couchdbPassword ${couchdbPassword}
setProfileProperties ${AutoScalingBrokerProfileDIR} brokerUsername ${brokerUsername}
setProfileProperties ${AutoScalingBrokerProfileDIR} brokerPassword ${brokerPassword}

setProfileProperties ${AutoScalingBrokerProfileDIR} serverURIList ${serverURIList}
setProfileProperties ${AutoScalingBrokerProfileDIR} apiServerURI ${apiServerURI}
setProfileProperties ${AutoScalingBrokerProfileDIR} internalAuthUsername ${internalAuthUsername}
setProfileProperties ${AutoScalingBrokerProfileDIR} internalAuthPassword ${internalAuthPassword}

}

function showConfiguration() {

promptHint " >>> display $componentName $AutoScalingServerProjectDirName configuration: "
showConfigurationEntry cfUrl ${AutoScalingServerProfileDIR}/$profile.properties
showConfigurationEntry cfClientId ${AutoScalingServerProfileDIR}/$profile.properties
showConfigurationEntry cfClientSecret ${AutoScalingServerProfileDIR}/$profile.properties cfClientSecret
showConfigurationEntry couchdbHost ${AutoScalingServerProfileDIR}/$profile.properties
showConfigurationEntry couchdbPort ${AutoScalingServerProfileDIR}/$profile.properties
showConfigurationEntry couchdbUsername ${AutoScalingServerProfileDIR}/$profile.properties
showConfigurationEntry couchdbPassword ${AutoScalingServerProfileDIR}/$profile.properties couchdbPassword
showConfigurationEntry internalAuthUsername ${AutoScalingServerProfileDIR}/$profile.properties
showConfigurationEntry internalAuthPassword ${AutoScalingServerProfileDIR}/$profile.properties

promptHint " >>> display $componentName $AutoScalingAPIProjectDirName configuration: "
showConfigurationEntry cfUrl ${AutoScalingAPIProfileDIR}/$profile.properties
showConfigurationEntry cfClientId ${AutoScalingAPIProfileDIR}/$profile.properties
showConfigurationEntry cfClientSecret ${AutoScalingAPIProfileDIR}/$profile.properties cfClientSecret
showConfigurationEntry internalAuthUsername ${AutoScalingAPIProfileDIR}/$profile.properties
showConfigurationEntry internalAuthPassword ${AutoScalingAPIProfileDIR}/$profile.properties

promptHint " >>> display $componentName $AutoScalingBrokerProjectDirName configuration: "
showConfigurationEntry couchdbHost ${AutoScalingBrokerProfileDIR}/$profile.properties
showConfigurationEntry couchdbPort ${AutoScalingBrokerProfileDIR}/$profile.properties
showConfigurationEntry couchdbUsername ${AutoScalingBrokerProfileDIR}/$profile.properties
showConfigurationEntry couchdbPassword ${AutoScalingBrokerProfileDIR}/$profile.properties couchdbPassword

showConfigurationEntry brokerUsername ${AutoScalingBrokerProfileDIR}/$profile.properties
showConfigurationEntry brokerPassword ${AutoScalingBrokerProfileDIR}/$profile.properties

showConfigurationEntry internalAuthUsername ${AutoScalingBrokerProfileDIR}/$profile.properties
showConfigurationEntry internalAuthPassword ${AutoScalingBrokerProfileDIR}/$profile.properties

showConfigurationEntry serverURIList ${AutoScalingBrokerProfileDIR}/$profile.properties
showConfigurationEntry apiServerURI ${AutoScalingBrokerProfileDIR}/$profile.properties

promptHint " >>> display $componentName deployment configuration: "
showConfigurationEntry deploymode ${RuntimeProfileDIR}/$profile.properties
showConfigurationEntry brokerURI ${RuntimeProfileDIR}/$profile.properties
if [ "$deploymode" == "cfapp" ]; then
	showConfigurationEntry hostingCFDomain ${RuntimeProfileDIR}/$profile.properties cfClientSecret
	showConfigurationEntry hostingCustomDomain ${RuntimeProfileDIR}/$profile.properties
	showConfigurationEntry org ${RuntimeProfileDIR}/$profile.properties
	showConfigurationEntry space ${RuntimeProfileDIR}/$profile.properties
	showConfigurationEntry cfUsername ${RuntimeProfileDIR}/$profile.properties couchdbPassword
	showConfigurationEntry cfPassword ${RuntimeProfileDIR}/$profile.properties
fi
echo

}


function configProject() {
	createProfile

	configRequired=true;
	if [ "$profileExist" == "true" ]; then
		reuseExistingProfile=$(confirmYes " Would you like to reuse all configuration in profile $profile? (y/n) ")
		if [ $reuseExistingProfile == "y" ]; then
			showConfiguration | tee output.txt
			missingItems=`cat output.txt | grep "<MISSING>"` ;
			if [ -z "$missingItems" ] ; then 
				configRequired=false
			else 
				promptHint " Re-run the configuration process to fix the <MISSING> items in profile $profile " 
			fi
		fi
	fi

	if [ "$configRequired" == "true" ] ; then 
		configure_basic_info
		configure_deployment_info
		setConfiguration
		showConfiguration
	fi

	confirmConfiguration=$(confirmYes " Proceed $componentName with above configuration? (y/n) ")
	if [ $confirmConfiguration == "n" ]; then
		exit 0
	fi


}
