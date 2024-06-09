@echo off
echo Executing Compiler...
mkdir bin
cd src
dir /S /B *.java > sources.txt
javac -d ..\bin @sources.txt
cd ..\bin

REM Check if an argument is passed
if "%1"=="" (
    echo No argument provided. Running Compiler without argument...
    echo INSERT PROGRAM HERE
    java main.CompMain
) else (
    echo Argument provided: %1. Running Compiler with file: %1...
    java main.CompMain ../examples/%1
)

echo Compile has completed. Generating JAR file...
cd compOut
java -jar ../../jasmin.jar *.j
java Demo
