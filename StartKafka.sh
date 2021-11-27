#!/bin/bash

bash setBD.sh
cd frontend
npm start &
sleep 5
cd /opt/kafka_2.13-3.0.0
bin/zookeeper-server-start.sh config/zookeeper.properties &
sleep 5
bin/kafka-server-start.sh config/server.properties &
sleep 5
bin/kafka-topics.sh --create --topic mytest --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 &
sleep 5
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
#cd -
#cd ..
#cd rest
#mvn exec:java -Dexec.mainClass="examplePack.SpringRestExample" &
#sleep 10
#cd ..
#cd Consumer
#mvn exec:java -Dexec.mainClass="examplePack.SpringRestExample" &
#sleep 10
#cd ..
#cd UpdateClientApi
#mvn exec:java -Dexec.mainClass="examplePack.UpdateClientApi.UpdateClientApiApplication" &