#!/usr/bin/env bash

SERVICE_PORT=8000
TUNNEL_PORT=9000

# Set up logging for autossh
export AUTOSSH_DEBUG=1
export AUTOSSH_LOGLEVEL=4
export AUTOSSH_LOGFILE=log

autossh -M 0 -gNC -f -L ${TUNNEL_PORT}:localhost:${SERVICE_PORT} lazywithclass@localhost

echo "Starting web server on port ${SERVICE_PORT}..."
echo "Access it via the tunnel at http://localhost:${TUNNEL_PORT}"
python -m http.server ${SERVICE_PORT}
