# Auto-Scaler BOSH Release

This is a release repository for Auto-Scaler that deploys the Auto-Scaler service which includes four components: Service Broker, API Server, Scaling Server and CouchDB.

Only bosh-lite release is supported in current version.

## Release Contents

### Jobs

The release has the following jobs:

* `autoscaler_servicebroker`: implements the service borker interface of Cloud Foundry, running as a web application on tomcat
* `autoscaler_api`:  provides APIs for user to interact with the Auto-Scaling service, running as a web application on tomcat
* `autoscaler_server`: provides the core functions of metrics collection, evaluation and action taken, running as a web application
* `couchdb`: the database tier for data persistency

###Packages:

* java : as a depency for `autoscaler_servicebroker`,  `autoscaler_server`, `autoscaler_api`
* tomcat:  as a depency for `autoscaler_servicebroker`,  `autoscaler_server`, `autoscaler_api`
* autoscaler_servicebroker: `autoscaler_servicebroker`
* autoscaler_api:  `autoscaler_api`
* autoscaler_server: `autoscaler_server`
* couchdb: `couchdb`
* pid_utils: as a depency to fulfill monit requirement


## Deploy 

* Add blobs:
As a starter bosh-release, no external release blobstore configured for this project yet. <br>
Please add blobs with script:`./script/generate_blobs.sh`
The script will fetch the latest autoscaler artifacts & download required dependencies.

* Create & upload release
Run command `bosh create release --force` to generate a dev release<br>
Run command `bosh upload release <release file>

* Configure deployment metadata
You need to configure `./stubs/boshlite-stub.yml` with the following items: <br>

 ```shell
 ---
 metadata:
  name: cf-open-autoscaler-warden
  director_uuid: 17a45148-1d00-43bc-af28-9882e5a6535a
  network_name: cf-open-autoscaler-network
  network_subnet: 10.244.4
  stemcell_name: bosh-warden-boshlite-ubuntu-trusty-go_agent
 ```

 `director_uuid` could be fetch with cmd `bosh status --uuid`<br>
 `stemcell_name` could be fetch with cmd `bosh stemcell` <br>
 `network_subnet` will define the subnet for `autoscaler_servicebroker`,  `autoscaler_server`, `autoscaler_api` and `couchdb`.  Please use format `10.244.4` , instead of `10.244.4.*` to avoid further error 

* Generate deployment manifest 
Run script `./script/generate_deployment_manifest ./stubs/boshlite-stub.yml > <deployment_manifest>` to create the deployment manifest. <br>
The script `generate_deployment_manifest` will merge the template manifest yml `./templates/open-autoscaler-warden-template.yml` and the stub yml with Spiff. <br>

* Deploy
Run command "bosh deployment <deployment_manifest>" <br>
Run command "bosh deploy" <br>

* Play with Auto-Scaler bosh-lite release

Assume the network subnet is "10.244.4", then:  <br>
`autoscaler_servicebroker` will be started at http://10.244.4.2/broker <br>
`autoscaler_api` will be started at http://10.244.4.3/api <br>
`autoscaler_server` will be started at http://10.244.4.4/server <br>
`couchdb` will be started at http://10.244.4.5:5984 <br>

Notes:<br>
Please ensure your cloudfoundry is hosted on the same bosh-lite to ensure the network connectivities. <br>
Please ensure the `date` setting on the Vagrant box is configured as UTC time & Synced with world clock, otherwise the `autoscaler_server` can't serve well for shedule based scaling.  <br>


