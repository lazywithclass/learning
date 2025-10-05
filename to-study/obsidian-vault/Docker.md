---
cssclasses: 
tags:
  - docker
---
## Dev containers

Example configuration

```
{  
  "name": "Kuder Dev Container",  
  "image": "kuder:latest",  
  "workspaceMount": "source=${localWorkspaceFolder},target=/app,type=bind",  
  "workspaceFolder": "/app",  
  "forwardPorts": [7888]  
}
```

## Running a container

Remember to add `-i` if you need interactive mode, otherwise any command you run in the container will wait indefinitely.\
After three C-c the container will exit with `got 3 SIGTERM/SIGINTs, forcefully exiting`

## Detect if running in a container

Docker creates an empty file named `.dockerenv` at the root of the container's filesystem:

```language-bash
#!/bin/bash

if [ -f /.dockerenv ]; then
  echo "Running inside a Docker container (via .dockerenv check)"
else
  echo "Not running inside a Docker container (.dockerenv not found)"
fi
```

## Override `ENTRYPOINT`

Just pass `--entrypoint` to `docker run ...`