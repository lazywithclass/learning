#!/usr/bin/env bash

kill -9 $(lsof -ti :9000)
echo "Killed process on port 9000, you should not see a pid here: '$(lsof -ti :9000)'."
echo "autossh should have restarted it now"
