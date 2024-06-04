@echo off
echo Executing Interpreter...
cd src
dir /S /B *.java > sources.txt
javac @sources.txt
java main.InterMain ../examples/%1