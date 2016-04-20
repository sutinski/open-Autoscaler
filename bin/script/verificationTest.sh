 #!/bin/bash
source ${basedir}/script/utils.sh

function verificationTest() {

	cfUrl=`getConfigurationValue cfUrl ${AutoScalingServerProfileDIR}/$profile.properties`
	
	echo " >>> Verification test will be launched on $cfUrl"
	${basedir}/script/test/launchTest.sh $serviceName $cfUrl

}