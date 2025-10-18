@echo off

javac -d .. *.java

java -cp .. innlevering2.testResepter

del ..\innlevering2\*.class

cmd /k
