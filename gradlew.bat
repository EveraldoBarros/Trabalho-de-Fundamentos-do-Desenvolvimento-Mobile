@echo off
setlocal

set DIRNAME=%~dp0
set APP_BASE_NAME=gradlew
set APP_HOME=%DIRNAME%
set CLASSPATH=%APP_HOME%gradle\wrapper\gradle-wrapper.jar

if not defined JAVA_HOME goto findJavaFromPath
set JAVA_EXE=%JAVA_HOME%\bin\java.exe
goto execute

:findJavaFromPath
set JAVA_EXE=java.exe

:execute
"%JAVA_EXE%" -cp "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
