#!/usr/bin/env sh

PGDATA=$PWD/pgsql/data
DB_NAME=imdb
DB_USER=lazywithclass

psql -U $DB_USER -d $DB_NAME -c "DROP SCHEMA imdb CASCADE;" -h $PGDATA
psql -U $DB_USER -d $DB_NAME -f imdb.sql -h $PGDATA
