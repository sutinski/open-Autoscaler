#!/bin/bash
boshdir=$(cd "$(dirname "$0")"; pwd)
componentName="AutoScaler"

bosh_stub_file=${boshdir}/stubs/boshlite-stub.yml
deploymentYML=$boshdir/examples/$componentName.yml;
set -e
${boshdir}/scripts/generate_blobs.sh
${boshdir}/scripts/generate_deployment_manifest.sh $bosh_stub_file > $boshdir/examples/$componentName.yml

bosh create release --force | tee output.txt
releasefile=`cat output.txt | tail -n 1 | awk '{print $3}'`
rm output.txt
bosh upload release $releasefile
bosh deployment $deploymentYML
#bosh deployment examples/as_warden.yml
bosh deploy << EOF
yes
EOF
