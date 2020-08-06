#/usr/bin/env bash
echo "Running perf testin using yml files: "$MODULE
echo "API version: "$VERSION
echo "Selected Project: " $PROJECT
bzt target/test-classes/$PROJECT/$VERSION/$MODULE