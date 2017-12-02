#!/bin/sh

JAVA=java

BIN_DIR=$(dirname "$0")
if [ "$BIN_DIR" = "." ]
then
	BIN_DIR=$(pwd)
fi

TOP_DIR=$(dirname "$BIN_DIR")
LIB_DIR="$TOP_DIR"/lib
LIB64_DIR="$LIB_DIR"/x86-64
LIB32_DIR="$LIB_DIR"/x86-32

CP="$TOP_DIR"/build/classes/main
for JAR in "$LIB_DIR"/*.jar
do
	CP=$CP:"$JAR"
done

if "$JAVA" -version 2>&1 | grep -q 64-Bit
then
	LD_LIBRARY_PATH="$LIB64_DIR"
else
	LD_LIBRARY_PATH="$LIB32_DIR"
fi

export LD_LIBRARY_PATH

"$JAVA" -classpath $CP charva.Jotunheimr "$@"
