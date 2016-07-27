set -e -x

echo "Extracting OpenJDK 8 ..."

mkdir -p ${BUILD_DIR}/jdk
tar xzvf openjdk/openjdk-1.8.0_91-x86_64-trusty.tar.gz -C ${BUILD_DIR}/jdk
if [[ $? != 0 ]] ; then
  echo "Failed extracting OpenJDK 8"
  exit 1
fi
