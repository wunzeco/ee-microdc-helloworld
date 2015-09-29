#!/bin/bash

NEXUS_LOGIN=$1
APP_VERSION=$2

./uploadToNexus.sh collections-notification-service $APP_VERSION jar $GIT_COMMIT "Collections-Notification-Service" build/libs/collections-notification-service-0.0.2-SNAPSHOT.jar $NEXUS_LOGIN

if [ $? -ne 0 ]
  then echo "[uploadToNexus.sh] failure! - failed to upload collections-notification-service artifact"
  exit 1
fi
