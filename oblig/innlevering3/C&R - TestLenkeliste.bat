@echo off

javac -d .. *.java

java -cp .. innlevering3.TestLenkeliste

del ..\innlevering3\*.class

cmd /k
