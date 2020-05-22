#!/usr/bin/env bash

./mvnw clean install
./mvnw -pl combo -P combo exec:java -Dexec.mainClass=test.Test
