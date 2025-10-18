@echo off

javac -cp .. ..\innlevering3\*.java

javac -cp .. *.java

java -cp .. innlevering7.GUI

IF ERRORLEVEL 1 del *.class

cmd /k
