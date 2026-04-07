# SSH

## Disconnection problems

One fix could editing `/etc/ssh/sshd_config` and adding the following lines:

```
ClientAliveInterval 60
ClientAliveCountMax 3
```

then restart the SSH service:

```bash
sudo systemctl restart sshd
```

