#!/usr/bin/env bash

FROM=8000
TO=9000
AUTOSSH_DEBUG=1 AUTOSSH_LOGLEVEL=4 AUTOSSH_LOGFILE=log autossh -M 0 -gNC -f -L $TO:localhost:$FROM lazywithclass@localhost

python -m http.server

