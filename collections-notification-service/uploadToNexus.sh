#!/bin/bash

ARTIFACT_ID=$1
ARTIFACT=$6
ARTIFACT_DESC=$5
ARTIFACT_EXTENSION=$3
ARTIFACT_NEXUS_REPO=bukt-build-artifacts
ARTIFACT_GROUP_ID=com.barclaycard
GIT_COMMIT=$4
GIT_URL="https://github.com/EqualExperts/barclaycard-uk-transformation.git"
GIT_CONNECTION="git@github.com:EqualExperts/barclaycard-uk-transformation.git"
POM_FILE=pom.xml
NEXUS_LOGIN=$7
NEXUS_ENDPOINT="http://tool-bukt-nexus1.internal:8081/nexus/service/local/artifact/maven/content"

if [ "$GIT_BRANCH" == "origin/master" ] || [ "$GIT_BRANCH" == "master" ]
then
        ARTIFACT_VERSION=$2
else
        ARTIFACT_VERSION="${GIT_BRANCH##*/}-$2"
fi


echo GIT_BRANCH = $GIT_BRANCH
echo ARTIFACT_VERSION = $ARTIFACT_VERSION 

cat >pom.xml <<EOL
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
   <modelVersion>4.0.0</modelVersion>
   <groupId>$ARTIFACT_GROUP_ID</groupId>
   <artifactId>$ARTIFACT_ID</artifactId>
   <version>$ARTIFACT_VERSION</version>
   <packaging>$ARTIFACT_EXTENSION</packaging>
   <name>$ARTIFACT_DESC</name>
   <scm>
       <connection>scm:git:$GIT_CONNECTION</connection>
       <url>$GIT_URL</url>
       <tag>$GIT_COMMIT</tag>
  </scm>
</project>
EOL


curl -k -v -F r=$ARTIFACT_NEXUS_REPO -F hasPom=true -F e=$ARTIFACT_EXTENSION -F file=@$POM_FILE -F file=@$ARTIFACT -u $NEXUS_LOGIN $NEXUS_ENDPOINT 
