#!/usr/bin/env sh

PGDATA=$PWD/pgsql/data
DB_NAME=imdb
DB_USER=lazywithclass

psql -U $DB_USER -d postgres -c "CREATE DATABASE $DB_NAME;" -h $PGDATA
psql -U $DB_USER -d postgres -c "CREATE USER $DB_USER WITH PASSWORD '';" -h $PGDATA
psql -U $DB_USER -d postgres -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_USER;" -h $PGDATA
psql -U $DB_USER -d postgres -c "ALTER DATABASE $DB_NAME OWNER TO $DB_USER;" -h $PGDATA

