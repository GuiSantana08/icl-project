#!/bin/bash

echo "Executing Interpreter..."

mkdir -p bin
cd src
find . -name "*.java" > sources.txt
javac -d ../bin @sources.txt
cd ../bin

# Check if an argument is passed
if [ -z "$1" ]; then
    echo "No argument provided. Running InterMain without argument..."
    java main.InterMain
else
    echo "Argument provided: $1. Running InterMain with argument..."
    java main.InterMain ../examples/"$1"
fi
