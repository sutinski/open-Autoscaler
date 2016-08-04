echo "Extracting Apache Maven ..."

mkdir -p maven
tar xzvf maven/apache-maven-3.3.9-bin.tar.gz -C maven
if [[ $? != 0 ]] ; then
  echo "Failed extracting Apache Maven"
  exit 1
fi

# Copy Apache Maven package
echo "Copying Apache Maven ..."
mkdir -p ${BUILD_DIR}/maven

pushd maven/apache-maven-3.3.9
  cp -a * ${BUILD_DIR}/maven
popd
