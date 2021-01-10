#!/bin/sh
if [ $# -eq 0 ]
  then
    echo "Need to provide git branch";exit 1;
fi

echo "DEPLOY INFO : GIT PULL FROM BRANCH $1"
git pull origin $1
echo "DEPLOY INFO : BUILD JAR FILE"
./mvnw clean package -DskipTests
echo "DEPLOY INFO : DOCKER COMPOSE BUILD"
docker-compose build
echo "DEPLOY INFO : DOCKER COMPOSE DOWN"
docker-compose down
echo "DEPLOY INFO : DOCKER COMPOSE UP"
docker-compose up -d