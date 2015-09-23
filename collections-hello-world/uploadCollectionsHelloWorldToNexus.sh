#!/bin/bash

NEXUS_LOGIN=$1

./uploadToNexus.sh collections-hello-world $BUILD_NUMBER jar $GIT_COMMIT "Collections-Hello-World" collections-hello-world/build/libs/collections-hello-world-0.0.2-SNAPSHOT.jar $NEXUS_LOGIN

if [ $? -ne 0 ]
  then echo "[uploadToNexus.sh] failure! - failed to upload collections-hello-world artifact"
  exit 1
fi
