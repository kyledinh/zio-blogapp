#!/bin/bash

## ENV and VARs
REPO_BASE=kyledinh
APP_IMG_NAME=$REPO_BASE/blogapp-nginx
DOCKERFILE=blogapp-nginx.dockerfile

## VERSION
echo; echo $(head -n 1 ../sem-version)-$(git show -s --pretty="%h") >| version
BUILD_VERSION=$(cat version)

## BUILD SCALA.JS
cp -R ../js-frontend ignored/.

## BUILD 
docker build -t $APP_IMG_NAME:$BUILD_VERSION -f $DOCKERFILE .
docker tag $APP_IMG_NAME:$BUILD_VERSION $APP_IMG_NAME:latest

docker images | grep $APP_IMG_NAME

## UPLOAD TO DOCKER HUB
