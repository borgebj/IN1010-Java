@echo off

javac -cp .. ..\innlevering3\*.java

javac -cp .. *.java

java -cp .. innlevering7.Oblig7 1.in

IF ERRORLEVEL 1 del *.class

cmd /k
