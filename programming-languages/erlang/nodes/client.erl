-module(client).
-export([start/1, send/0]).


start(OtherHost) -> %% we could also register OtherHost
                    %% so we could use it in send
                    ok.

send() -> {server, server@nixos} ! "test".
