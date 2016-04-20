#!/bin/bash

function do_login() {

    currentAPIURL=`cf api | awk '{print $3}' | sed -e 's/https*:\/\///g' `   
    
    if [ "$currentAPIURL" != "$apiUrl" ]; then
            cf login -a $apiUrl --skip-ssl-validation
    fi

    cf oauth-token > /dev/null
    if [ $? -ne 0 ]; then
        cf login -a $apiUrl --skip-ssl-validation
    fi

}


function do_cleanenv() {
   echo "=============== Clean test environment =========================================="
    cf service $SEVICE_INSTANCE_NAME
    if [ $? -eq 0 ]; then
        cf delete-service $SEVICE_INSTANCE_NAME -f
    fi 
    cf app $TestAPP_NAME
    if [ $? -eq 0 ]; then
            cf delete $TestAPP_NAME -f
    fi
    echo "=============== Clean test environment successfully =========================================="
}

function do_pushApp() {

    echo "============== Push App ============================================"
        cf app $TestAPP_NAME
        if [ $? -eq 0 ]; then
            cf delete $TestApp_
        fi
        cf push  $TestAPP_NAME -p $APP_FILE  --random-route 
        if [ $? -ne 0 ]; then
            echo "Fail to push application."
            return 254
        fi
    echo "============== Push App successfully================================="
}

function do_pushApp_nostart() {

    echo "============== Push App ============================================"
        cf app $TestAPP_NAME
        if [ $? -eq 0 ]; then
            cf delete $TestApp_
        fi
        cf push  $TestAPP_NAME -p $APP_FILE  --random-route --no-start
        if [ $? -ne 0 ]; then
            echo "Fail to push application."
            return 254
        fi
    echo "============== Push App successfully================================="
}

function setup_TestEnv() {
    do_cleanenv
    do_pushApp_nostart
}




    

