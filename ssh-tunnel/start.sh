#!/usr/bin/env bash

FROM=8000
TO=9000
ssh -f -N -L $TO:localhost:$FROM lazywithclass@localhost

python -m http.server
