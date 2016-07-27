set -e -x

openjdk_artifact_blob="openjdk/openjdk-1.8.0_91-x86_64-trusty.tar.gz"

echo "Downloading OpenJDK"

cd ${BUILD_DIR}

if [ ! -f $openjdk_artifact_blob ]; then
    mkdir -p `dirname $openjdk_artifact_blob`
    # Source https://github.com/cloudfoundry/uaa-release/blob/bfd101fe7d5fc6e964e7c8be5847fbb5a1cf2c12/config/blobs.yml#L18-L21
	curl -o $openjdk_artifact_blob -L https://s3.amazonaws.com/uaa-final-release-blobs/a0aeff72-54ff-424c-acc2-a8844ea930f9
fi
