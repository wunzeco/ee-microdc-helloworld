#!/bin/bash

NEXUS_LOGIN=$1
APP_VERSION=$2
APP_NAME=collections-notification-service
APP_DESC=$(echo $APP_NAME | tr 'a-z' 'A-Z')

./uploadToNexus.sh $APP_NAME $APP_VERSION tar.gz $GIT_COMMIT "$APP_DESC" build/artifacts/$APP_NAME.tar.gz $NEXUS_LOGIN

if [ $? -ne 0 ]
  then echo "[$0] failure! - failed to upload $APP_NAME artifact"
  exit 1
fi
