#!/usr/bin/env bash

LOGS_DIR="logs"

mkdir -p "${LOGS_DIR}"

echo "Running shard 1"
mongod --dbpath shard1 --port 27000 > "${LOGS_DIR}/shard1.log" &

echo "Running shard 2"
mongod --dbpath shard2 --port 27001 > "${LOGS_DIR}/shard2.log" &

echo "Running config server"
mongod --configsvr --dbpath config --port 27002  > "${LOGS_DIR}/config.log" &

echo "Running database instance"
mongos --configdb 127.0.0.1:27002 > "${LOGS_DIR}/db.log" &

echo "Mongo database set up successfully"
