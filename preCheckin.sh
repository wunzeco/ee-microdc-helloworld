#!/usr/bin/env bash
./gradlew -DfatJarLocation=./web/build/libs/collections-hello-world-0.0.2-SNAPSHOT.jar clean build stopApp startApp integrationTest
