#!/usr/bin/env bash

./mvnw clean install
./mvnw -pl test exec:java -Dexec.mainClass=test.Test
