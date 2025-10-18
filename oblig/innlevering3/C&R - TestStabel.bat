@echo off

javac -d .. *.java

java -cp .. innlevering3.TestStabel

del ..\innlevering3\*.class

cmd /k
