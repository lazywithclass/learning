#!/usr/bin/env sh

PGDATA=pgsql/data

echo "Stopping PostgreSQL server..."
pg_ctl stop -D "$PGDATA"
