@echo off
echo Executing Interpreter...
mkdir bin
cd src
dir /S /B *.java > sources.txt
javac -d ..\bin @sources.txt
cd ..\bin

REM Check if an argument is passed
if "%1"=="" (
    echo No argument provided. Running Interpreter without argument...
    java main.InterMain
) else (
    echo Argument provided: %1. Running Interpreter with file: %1...
    java main.InterMain ../examples/%1
)
cd ..\bin