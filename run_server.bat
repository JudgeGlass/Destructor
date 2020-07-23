@echo off
del Server\plugins\destructor-0.0.1a.jar
xcopy.exe /s target\destructor-0.0.1a.jar Server\plugins
cd C:\Users\hunte\Documents\Destructor\Server
run.bat