@echo off

javac -d .. *.java

java -cp .. innlevering4.Main 3

del ..\innlevering4\*.class

cmd /k
