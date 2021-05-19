#!/usr/bin/env bash

sudo docker rm app-db
sudo docker rmi guide-helper-db
sudo docker build -t guide-helper-db .
sudo docker run --name app-db -p 3307:3306 -d guide-helper-db
sudo docker start app-db