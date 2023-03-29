#!/bin/bash
docker pull zhenyu00/rescue-backend:latest
docker rm -f rescue||true&&docker run  --name=rescue -d -p 3088:3088 zhenyu00/rescue-backend:latest
docker image prune -af
