#!/usr/bin/env sh

PGDATA=pgsql/data
LOGFILE=pgsql/logfile

# Ensure the data directory exists
if [ ! -d "$PGDATA" ]; then
    echo "Initializing PostgreSQL data directory at $PGDATA..."
    initdb -D "$PGDATA"
fi

echo "Starting PostgreSQL server..."
pg_ctl start -D "$PGDATA" -l "$LOGFILE"
