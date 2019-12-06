#!/bin/bash

echo "Remove old logs"
rm -rf ~/eventpro-*

DATE_WITH_TIME=`date "+%Y%m%d-%H%M%S"`
echo $DATE_WITH_TIME

kill -9 $(lsof -i:443 -t) 2> /dev/null
echo "Killed port 443"
mvn clean install

nohup java -Dspring.profiles.active=prod -jar target/event-pro-0.0.1-SNAPSHOT.jar > ~/eventpro-${DATE_WITH_TIME}.log &

echo "Started event-pro application"
