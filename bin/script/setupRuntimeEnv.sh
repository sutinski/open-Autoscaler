 #!/bin/bash
source ${basedir}/script/utils.sh

function initRuntimeConfiguration(){
	cfUrl=`getConfigurationValue cfUrl ${AutoScalingServerProfileDIR}/$profile.properties`
	cfDomain=${cfUrl##"api."}
	brokerUsername=`getConfigurationValue brokerUsername ${AutoScalingBrokerProfileDIR}/$profile.properties`
	brokerPassword=`getConfigurationValue brokerPassword ${AutoScalingBrokerProfileDIR}/$profile.properties`

	serverURIList=`getConfigurationValue serverURIList ${AutoScalingBrokerProfileDIR}/$profile.properties`
	apiServerURI=`getConfigurationValue apiServerURI ${AutoScalingBrokerProfileDIR}/$profile.properties`
	brokerURI=`getConfigurationValue brokerURI ${RuntimeProfileDIR}/$profile.properties`

	deploymode=`getConfigurationValue deploymode ${RuntimeProfileDIR}/$profile.properties`


	
}

function deployRuntime() {

    case $deploymode in
        [cfapp]* )  
		 	hostingCFDomain=`getConfigurationValue hostingCFDomain ${RuntimeProfileDIR}/$profile.properties`
			hostingCustomDomain=`getConfigurationValue hostingCustomDomain ${RuntimeProfileDIR}/$profile.properties`
			org=`getConfigurationValue org ${RuntimeProfileDIR}/$profile.properties`
			space=`getConfigurationValue space ${RuntimeProfileDIR}/$profile.properties`
			cfUsername=`getConfigurationValue cfUsername ${RuntimeProfileDIR}/$profile.properties`
			cfPassword=`getConfigurationValue cfPassword ${RuntimeProfileDIR}/$profile.properties`

			cf login -a https://api.$hostingCFDomain -u $cfUsername -p $cfPassword -o $org -s $space  --skip-ssl-validation
			pushMavenPackageToCF ${AutoScalingServerName} ${AutoScalingServerProjectDirName} ${hostingCustomDomain}
			pushMavenPackageToCF ${AutoScalingAPIName} ${AutoScalingAPIProjectDirName} ${hostingCustomDomain}
			pushMavenPackageToCF ${AutoScalingBrokerName} ${AutoScalingBrokerProjectDirName} ${hostingCustomDomain}
			;;
        [bosh]* )  
 			boshdir="${basedir}/../bosh-release/" ;
 			bosh_stub_file=${boshdir}/stubs/boshlite-stub.yml
 			deploymentYML=$boshdir/examples/$componentName.yml;
 			set -e
 			${boshdir}/scripts/generate_blobs.sh
 			${boshdir}/scripts/generate_deployment_manifest.sh $bosh_stub_file > $boshdir/examples/$componentName.yml
 			cd $boshdir
 			bosh create release --force | tee ${basedir}/output.txt
 			releasefile=`cat ${basedir}/output.txt | tail -n 1 | awk '{print $3}'`
			rm ${basedir}/output.txt
			bosh upload release $releasefile
			bosh deployment $deploymentYML
			bosh deploy
			cd ${basedir}
			set +e
 			;;
 		[localenv]* )  
 			echo " >>> Please setup your runtime environment MANUALLY to align with previous setting : "
			echo " serverURIList : $serverURIList"
			echo " apiServerURI : $apiServerURI"
			echo " serviceBrokerURI : $brokerURI"
    		read -p "Press Any key to continue when runtime environment is launched ... " input
 			;;		
        * ) echo "Invalid deployment mode \"$deploymode\"";;
    esac

}

function registerService(){

echo " >>> To register service on $cfDomain, please login api.$cfDomain first .."

if  [ "$deploymode" != "cfapp" ] || [ "$cfDomain" != "$hostingCFDomain" ] ; then
	cf login  -a https://api.$cfDomain --skip-ssl-validation
else
	cf login -a https://api.$cfDomain -u $cfUsername -p $cfPassword -o $org -s $space --skip-ssl-validation
fi

cf marketplace -s $serviceName

if [ $? -ne 0 ]; then
	echo "cf create-service-broker $componentName <brokerUserName> <brokerPassword> $brokerURI"
	cf create-service-broker $componentName $brokerUsername $brokerPassword $brokerURI
	cf enable-service-access $serviceName
else 
	echo "cf update-service-broker $componentName <brokerUserName> <brokerPassword> $brokerURI"
	cf update-service-broker $componentName $brokerUsername $brokerPassword $brokerURI
fi
}


function setupRuntimeEnv(){
	initRuntimeConfiguration
	deployRuntime
	registerService
}
