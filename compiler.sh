#!/bin/bash

echo "Executing Compiler..."
mkdir -p bin
cd src
find . -name "*.java" > sources.txt
javac -d ../bin @sources.txt
cd ../bin

# Check if an argument is passed
if [ -z "$1" ]; then
    echo "No argument provided. Running Compiler without argument..."
    java main.CompMain
else
    echo "Argument provided: $1. Running Compiler with file: $1..."
    java main.CompMain ../examples/"$1"
fi

echo "Compile has completed. Generating JAR file..."

cd compOut
java -jar ../../jasmin.jar *.j
java Demo

echo "Script execution completed."
