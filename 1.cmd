@echo off
setlocal enabledelayedexpansion

REM ----------------------------------------------------------------------------
REM Licensed to the Apache Software Foundation (ASF) under one
REM or more contributor license agreements.  See the NOTICE file
REM distributed with this work for additional information
REM regarding copyright ownership.  The ASF licenses this file
REM to you under the Apache License, Version 2.0 (the
REM "License"); you may not use this file except in compliance
REM with the License.  You may obtain a copy of the License at
REM
REM    http://www.apache.org/licenses/LICENSE-2.0
REM
REM Unless required by applicable law or agreed to in writing,
REM software distributed under the License is distributed on an
REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
REM KIND, either express or implied.  See the License for the
REM specific language governing permissions and limitations
REM under the License.
REM ----------------------------------------------------------------------------

REM ----------------------------------------------------------------------------
REM Maven2 Start Up Batch script
REM
REM Required ENV vars:
REM ------------------
REM   JAVA_HOME - location of a JDK home dir
REM
REM Optional ENV vars
REM -----------------
REM   M2_HOME - location of maven2's installed home dir
REM   MAVEN_OPTS - parameters passed to the Java VM when running Maven
REM     e.g. to debug Maven itself, use
REM       set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
REM   MAVEN_SKIP_RC - flag to disable loading of mavenrc files
REM ----------------------------------------------------------------------------

if "%MAVEN_SKIP_RC%"=="" (
  if exist "%ALLUSERSPROFILE%\mavenrc" call "%ALLUSERSPROFILE%\mavenrc"
  if exist "%USERPROFILE%\.mavenrc" call "%USERPROFILE%\.mavenrc"
)

REM OS specific support
set "cygwin=false"
set "mingw=false"
set "darwin=false"

REM Determine OS (simplified for Windows)
if exist "C:\cygwin" set "cygwin=true"
if exist "C:\mingw" set "mingw=true"

REM Handle JAVA_HOME detection for Windows
if "%JAVA_HOME%"=="" (
  for /f "delims=" %%i in ('where javac') do (
    set "javaExecutable=%%i"
    goto :foundJavac
  )
  :foundJavac
  if defined javaExecutable (
    set "javaHome=%~dp0.."
    for /f "delims=" %%j in ('dir /b /ad "%javaHome%\.."') do set "JAVA_HOME=%%j"
  )
)

REM Determine M2_HOME if not set
if "%M2_HOME%"=="" (
  set "PRG=%~dpnx0"
  :resolveLink
  if exist "%PRG%" (
    for /f "delims=" %%i in ('dir /al "%PRG%"') do (
      set "link=%%i"
      goto :processLink
    )
  )
  :processLink
  if defined link (
    if "!link:~0,1!"=="\\" (
      set "PRG=!link!"
    ) else (
      set "PRG=%~dp0%link%"
    )
    goto :resolveLink
  )
  set "saveddir=%cd%"
  set "M2_HOME=%~dp0.."
  pushd "%M2_HOME%" >nul
  set "M2_HOME=%cd%"
  popd >nul
  cd "%saveddir%"
)

REM Path normalization for Cygwin/Mingw
if %cygwin%==true (
  set "M2_HOME=%M2_HOME:\=/%
  set "JAVA_HOME=%JAVA_HOME:\=/%
)
if %mingw%==true (
  pushd "%M2_HOME%" >nul
  set "M2_HOME=%cd%"
  popd >nul
  pushd "%JAVA_HOME%" >nul
  set "JAVA_HOME=%cd%"
  popd >nul
)

REM Verify JAVA_HOME
if "%JAVA_HOME%"=="" (
  echo Warning: JAVA_HOME environment variable is not set.
)

set "JAVACMD=java"
if defined JAVA_HOME (
  if exist "%JAVA_HOME%\bin\java.exe" (
    set "JAVACMD=%JAVA_HOME%\bin\java.exe"
  )
)

if not exist "%JAVACMD%" (
  echo Error: JAVA_HOME is not defined correctly.
  echo We cannot execute %JAVACMD%
  exit /b 1
)

REM Find project base directory
set "MAVEN_PROJECTBASEDIR=%cd%"
:findBaseDir
if exist "%MAVEN_PROJECTBASEDIR%\.mvn" goto baseDirFound
if "%MAVEN_PROJECTBASEDIR%"=="\" goto baseDirNotFound
set "MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR%\.."
goto findBaseDir

:baseDirFound
rem 找到 .mvn 目录后的处理逻辑（当前脚本未写具体操作，可按需添加）
goto continueProcess

:baseDirNotFound
set "MAVEN_PROJECTBASEDIR=%cd%"
:continueProcess

REM Read JVM config
set "MAVEN_OPTS="
if exist "%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config" (
    for /f "delims=" %%i in ('type "%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config"') do (
        set "MAVEN_OPTS=!MAVEN_OPTS! %%i"
    )
)

REM Build command line
set "MAVEN_CMD_LINE_ARGS=%MAVEN_CONFIG% %*"

REM Execute Maven
"%JAVACMD%" !MAVEN_OPTS! ^
  -classpath "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" ^
  -Dmaven.home="%M2_HOME%" ^
  -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" ^
  org.apache.maven.wrapper.MavenWrapperMain %MAVEN_CONFIG% %*

endlocal