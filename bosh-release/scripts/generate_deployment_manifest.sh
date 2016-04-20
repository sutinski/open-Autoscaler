#!/bin/bash -e
base_dir=$(cd "$(dirname "$0")"; pwd)

infrastructure=warden
spiff merge ${base_dir}/../templates/open-autoscaler-warden-template.yml "$@"
