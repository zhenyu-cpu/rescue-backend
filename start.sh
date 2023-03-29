#!/bin/bash
docker pull zhenyu00/rescue-backend:latest
docker rm -f rescue||true&&docker run  --name=appName -d -p 8080:8080 zhenyu00/app:latest
docker image prune -af
