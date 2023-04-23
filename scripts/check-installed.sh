#!/bin/bash
# Checks for installed software packages 

function fn_check_docker {
  if command -v docker >/dev/null 2>&1; then
    docker --version
  else
    echo "!!! docker required, but it's not installed."; 
  fi
}

function fn_check_java {
  if command -v java >/dev/null 2>&1; then
    java --version
  else
    echo "!!! java required, but it's not installed."; 
  fi
}

function fn_check_make {
  if command -v make >/dev/null 2>&1; then
    make --version
  else
    echo "!!! make required, but it's not installed."; 
  fi
}

function fn_check_node {
  if command -v npm >/dev/null 2>&1; then
    npm version
  else
    echo "!!! node/npm/yarn required, but it's not installed."; 
  fi
}

function fn_check_sbt {
  if command -v sbt >/dev/null 2>&1; then
    sbt --version
  else
    echo "!!! sbt required, but it's not installed."; 
  fi
}

function fn_check_scala {
  if command -v scala >/dev/null 2>&1; then
    scala --version
  else
    echo "!!! scala required, but it's not installed."; 
  fi
}

## MAIN
echo "Checking installed software packages:"
echo "docker, java, make, sbt, scala"
echo
fn_check_docker
fn_check_java
fn_check_make
fn_check_sbt
fn_check_scala
fn_check_node
