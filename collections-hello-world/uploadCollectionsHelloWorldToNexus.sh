#!/bin/bash

NEXUS_LOGIN=$1
APP_VERSION=$2

./uploadToNexus.sh collections-hello-world $APP_VERSION jar $GIT_COMMIT "Collections-Hello-World" build/libs/collections-hello-world-0.0.2-SNAPSHOT.jar $NEXUS_LOGIN

if [ $? -ne 0 ]
  then echo "[uploadToNexus.sh] failure! - failed to upload collections-hello-world artifact"
  exit 1
fi
