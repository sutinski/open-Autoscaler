#!/bin/bash
basedir=$(cd "$(dirname "$0")"; pwd)
source $basedir/prerequisite.sh
source $basedir/fat.sh


function setup_TestConfig() {
    SEVICE_INSTANCE_NAME="ScalingServiceTest"
    TestAPP_NAME="ScalingTestApp"

    Resource_DIR="$basedir/resource"
    APP_FILE="${Resource_DIR}/app/nodeApp/"
    DYNAMIC_APP_FILE="${Resource_DIR}/app/nodeApp/"
    Default_Memory="700m"
    logfile=${basedir}/"fat.log"
}

function cleanup_TestConfig(){
	rm -f $logfile
}

function highlightPrompt () {
	defaultColor="\033[0m"
	highlightColor="\033[1m"
	echo -e ${highlightColor}$1 $defaultColor 
}

function startTestCase(){
	local testcase=$1
	echo " $testcase: Running"
}

function reportStatus(){
	local testcase=$1
	local statuscode=$2

	if [ $statuscode -eq 0 ]; then
		echo " $testcase: Succeed"
	else
		echo " $testcase: Failed"
		echo " >>> The last 10 lines of output: "
		tail -n 10 $logfile
		echo " >>> Please review the detail log in $logfile"
		exit 1
	fi
}

function invokeTest(){
	local testcase=$1
	local functionName=$2
	echo " $testcase: Running"
	$functionName 2>&1 >$logfile
	reportStatus "$testcase" $?
}

function invokeTestWithOutput(){
	local testcase=$1
	local functionName=$2
	echo " $testcase: Running"
	$functionName  2>$logfile
	reportStatus "$testcase" $?

}

if  [ $# -lt 2 ]
then 
	echo -e  "Usages: launchTest.sh <serviceName> <cf api url>"
	echo -e  "e.g. launchTest.sh CF-AutoScaler api.bosh-lite.com"
	exit -1
fi

###Start of main
serviceName=$1
apiUrl=$2

setup_TestConfig
do_login
echo " >>> The latest test progress are logged in $logfile"

invokeTest "Prepare basic test environment " setup_TestEnv
invokeTest "Testcase: Service broker API" test_servicebroker_API
invokeTest "Prepare test environment for $serviceName Public API" setup_PublicAPI_TestEnv

invokeTest "Testcase: Policy API" test_Policy_API
invokeTest "Testcase: Metric API" test_Metric_API
invokeTest "Testcase: History API" test_History_API

invokeTestWithOutput "Testcase: Schedule based scaling" doCheckScaleByRecurringSchedule
invokeTestWithOutput "Testcase: Metric based scaling" doCheckScaleByMetrics

cleanup_TestConfig

