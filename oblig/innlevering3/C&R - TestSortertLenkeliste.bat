@echo off

javac -d .. *.java

java -cp .. innlevering3.TestSortertLenkeliste

del ..\innlevering3\*.class

cmd /k
