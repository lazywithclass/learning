#!/usr/bin/env bash

SERVICE_PORT=8000
TUNNEL_PORT=9000

ssh -f -N -L ${TUNNEL_PORT}:localhost:${SERVICE_PORT} lazywithclass@localhost

python -m http.server ${SERVICE_PORT}
