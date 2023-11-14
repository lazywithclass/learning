# Ping pong

To use this

``` shell
# in a shell:
$ npm start 3001
# ...and in another shell:
$ npm start 3002
# ...then 
$ curl -X PUT "http://localhost:3002/start?url=http://localhost:3001" 
# or write a simple script to perform the HTTP call instead of curl
```
