#!/usr/bin/env bash

docker-compose -f mongodb-docker-compose.yml down
docker container prune
docker network prune
docker volume prune