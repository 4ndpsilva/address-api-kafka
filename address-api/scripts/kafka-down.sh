#!/usr/bin/env bash

docker-compose -f docker/kafka-docker-compose.yml down
docker container prune
docker network prune