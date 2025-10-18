@echo off

javac -d .. *.java

java -cp .. innlevering2.testLegemiddel

del ..\innlevering2\*.class

cmd /k
