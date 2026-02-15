
## Cleanly closing a tunnel

[source](https://unix.stackexchange.com/a/525388/4855)

```bash
CTRL_SOCKET=/app/docker/tunnel-socket  
echo "SSH tunnel: starting"  
ssh \  
    -q \  
    -M -S "$CTRL_SOCKET" \  
    -o "StrictHostKeyChecking=no" -o "UserKnownHostsFile=/dev/null" \  
    -i ./docker/tunnel_key \  
    -f -N -D 8443 user@host  
  
  
echo "SSH tunnel: started"  
  
sleep 10  

echo "SSH tunnel: killing"  
ssh -S "$CTRL_SOCKET" -O exit user@host
