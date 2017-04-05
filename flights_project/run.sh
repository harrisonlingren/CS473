#!/bin/bash

javac -cp "libs/*" src/cs473/*.java
cd src/
java -cp "../libs/*" cs473.Main localhost "../files/simpleData.csv"
