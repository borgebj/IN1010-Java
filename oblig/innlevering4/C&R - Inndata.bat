@echo off

javac -cp .. ..\innlevering3\*.java *.java

java -cp .. innlevering4.Main 1

del *.class

cmd /k
