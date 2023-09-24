#!/usr/bin/env sh

DB_NAME="dvdrental"
DB_ROLE="postgres"
HOST=/home/nixos/workspace/learning/sql/data

psql -h $HOST $DB_NAME -c "DROP ROLE $DB_ROLE;"
psql -h $HOST template1 -c "DROP DATABASE $DB_NAME;"

psql -h $HOST template1 -c "CREATE DATABASE $DB_NAME;"
psql -h $HOST $DB_NAME -c "CREATE USER $DB_ROLE"
psql -h $HOST template1 -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_NAME;"

pg_restore -h $HOST -U $DB_ROLE -d $DB_NAME dvdrental.tar
