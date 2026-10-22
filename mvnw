#!/bin/sh
set -eu

MAVEN_VERSION=3.9.16
MAVEN_CACHE_ROOT="${MAVEN_USER_HOME:-${HOME}/.m2}"
MAVEN_HOME_DIR="$MAVEN_CACHE_ROOT/wrapper/dists/apache-maven-$MAVEN_VERSION/kawz"
MAVEN_BIN="$MAVEN_HOME_DIR/bin/mvn"
MAVEN_URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip"

if command -v mvn >/dev/null 2>&1; then
    exec mvn "$@"
fi

if [ ! -x "$MAVEN_BIN" ]; then
    MAVEN_TEMP_DIR=$(mktemp -d)
    trap 'rm -rf "$MAVEN_TEMP_DIR"' EXIT HUP INT TERM
    if command -v curl >/dev/null 2>&1; then
        curl -fsSL "$MAVEN_URL" -o "$MAVEN_TEMP_DIR/maven.zip"
    elif command -v wget >/dev/null 2>&1; then
        wget -q "$MAVEN_URL" -O "$MAVEN_TEMP_DIR/maven.zip"
    else
        echo "Instale Maven 3.9+ ou curl/wget para executar o wrapper." >&2
        exit 1
    fi
    unzip -q "$MAVEN_TEMP_DIR/maven.zip" -d "$MAVEN_TEMP_DIR"
    mkdir -p "$(dirname "$MAVEN_HOME_DIR")"
    mv "$MAVEN_TEMP_DIR/apache-maven-$MAVEN_VERSION" "$MAVEN_HOME_DIR"
fi

exec "$MAVEN_BIN" "$@"
