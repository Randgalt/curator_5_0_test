#!/usr/bin/env bash

./mvnw install
./mvnw -pl test exec:java -Dexec.mainClass=test.Test
