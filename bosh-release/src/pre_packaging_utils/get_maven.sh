set -e -x

maven_artifact_blob="maven/apache-maven-3.3.9-bin.tar.gz"

echo "Download Maven"

cd ${BUILD_DIR}

if [ ! -f $maven_artifact_blob ]; then
    mkdir -p `dirname $maven_artifact_blob`
	curl -o $maven_artifact_blob -L http://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
fi
