#!/usr/bin/env sh
# -----------------------------------------------------------------------------
# gradle wrapper
# -----------------------------------------------------------------------------
set -e

if [ -z "$JAVA_HOME" ] ; then
  echo "WARNING: JAVA_HOME is not set. Gradle may not run correctly."
fi

DIRNAME="$(dirname "$0")"
APP_BASE_NAME="gradlew"
APP_HOME="$(cd "$DIRNAME" && pwd -P)"

CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

if [ -z "$JAVA_CMD" ] ; then
  if [ -n "$JAVA_HOME" ] ; then
    JAVA_CMD="$JAVA_HOME/bin/java"
  else
    JAVA_CMD="java"
  fi
fi

exec "$JAVA_CMD" -cp "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
