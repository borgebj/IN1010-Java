@echo off

rem Compile all Java files
javac *.java

rem Run the main program
java oblig1Main

rem Delete compiled class files
del *.class

cmd /k
