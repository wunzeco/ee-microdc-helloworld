#!/bin/bash

APP_NAME=collections-notification-service
WORKDIR=$(dirname $0)

echo "[$0] building..."
./gradlew clean build createDockerfile 

if [ $? -ne 0 ]; then
  echo "[$0] failure"
  exit 1
fi

mkdir -v build/artifacts

echo -e "\n[$0] copying artifacts..."
cp -v build/docker/Dockerfile build/libs/${APP_NAME}-0.0.1.jar build/artifacts/

cd  build/artifacts/

echo -e "\n[$0] archiving..."
tar -cvzf ${APP_NAME}.tar.gz Dockerfile ${APP_NAME}-0.0.1.jar

if [ $? -ne 0 ]; then
  echo "[$0] failure"
  exit 1
fi
