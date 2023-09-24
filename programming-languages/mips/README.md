## Learning MIPS

I've coded this using Docker because I didn't want to have Java installed in my system.

`DISPLAY` env variable used in Dockerfile has been populated as such `export DISPLAY=$(cat /etc/resolv.conf | grep nameserver | awk '{print $2; exit;}'):0.0`, so Mars can be run from the container and displayed as GUI.
