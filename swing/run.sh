#!/bin/bash

# set -x

SKIP_PACKAGE_VERSION=false
# These get extracted from the pom.xml, keeping the default values for backwad compatibility
DEFAULT_MAIN_CLASS='SwetMainFlowPanel'
APP_NAME='swet_javafx_app'
APP_PACKAGE='example'
APP_VERSION='0.0.2-SNAPSHOT'

which xmllint > /dev/null

if [ $? -eq  0 ] ; then
echo 0
  APP_VERSION=$(xmllint -xpath "/*[local-name() = 'project' ]/*[local-name() = 'version' ]/text()" pom.xml)
  APP_PACKAGE=$(xmllint -xpath "/*[local-name() = 'project' ]/*[local-name() = 'groupId' ]/text()" pom.xml)
  APP_NAME=$(xmllint -xpath "/*[local-name() = 'project' ]/*[local-name() = 'artifactId' ]/text()" pom.xml)
  # may be empty
  DEFAULT_MAIN_CLASS=$(xmllint -xpath "/*[local-name() = 'project' ]/*[local-name() = 'properties' ]/*[local-name() = 'mainClass']/text()" pom.xml 2> /dev/null)
fi

MAIN_APP_CLASS=${1:-$DEFAULT_MAIN_CLASS}

for APP_CONFIG_ENTRY in 'MAIN_APP_CLASS' 'APP_PACKAGE' 'APP_VERSION' 'APP_NAME'; do echo "${APP_CONFIG_ENTRY}=$(eval echo \$$APP_CONFIG_ENTRY )" 1>& 2 ; done

if $SKIP_PACKAGE_VERSION; then
  APP_JAR="$APP_NAME.jar"
else
  APP_JAR="$APP_NAME-$APP_VERSION.jar"
fi

if $(uname -s | grep -qi Darwin)
then
  JAVA_VERSION='1.8.0_121'
  MAVEN_VERSION='3.3.9'
  export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk$JAVA_VERSION.jdk/Contents/Home
  export M2_HOME="$HOME/Downloads/apache-maven-$MAVEN_VERSION"
  export M2="$M2_HOME/bin"
  export MAVEN_OPTS='-Xms256m -Xmx512m'
  export PATH=$M2_HOME/bin:$PATH

  # https://bugs.openjdk.java.net/browse/JDK-8167419
  # Problematic frame:
  # C  [libGL.dylib+0x1c3d]  glGetString+0x1c
  # may need to install some of https://github.com/phracker/MacOSX-SDKs

fi

if [[ "$SKIP_BUILD" != 'true' ]]
then
  mvn -Dmaven.test.skip=true package install
fi
echo "java -cp target/$APP_JAR:target/lib/* $APP_PACKAGE.$MAIN_APP_CLASS"
java -cp target/$APP_JAR:target/lib/* $APP_PACKAGE.$MAIN_APP_CLASS
# mvn clean spring-boot:run
