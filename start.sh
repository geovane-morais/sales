#!/bin/sh

case $1 in
    'install') install=1 ;;
    'mssql') mssql=1 ;;
esac

if [ $mssql ] 
then
    echo "*** Instalando o container docker mssql ***"
    docker stop MsDataBase
    docker rm MsDataBase
    docker run --name MsDataBase -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=MsSql#D2t2B2s3" -p 1433:1433 --net mySubNet --ip 172.0.0.10 mcr.microsoft.com/mssql/server:2022-latest &
fi

if [ $install ] 
then
    echo "*** Install container docker application ***"
    mvn clean package -DskipTests
    docker build -t java_projeto-programacao-web ./
    docker stop java_web
    docker rm java_web
    docker run --name java_web -d --net mySubNet --ip 172.0.0.8 -p 5000:5000 java_projeto-programacao-web
    docker restart java_web
fi


# docker logs java_web -n 40
# docker exec –it java_web /bin/bash
# docker cp . java_web:/app/

#docker network create \
#  --driver=bridge \
#  --subnet=172.0.0.0/24 \
#  --ip-range=172.0.0.3/24 \
#  --gateway=172.0.0.2 \
#  mySubNet
