-module(server).
-export([start/0, loop/0]).


start() -> Pid = spawn(fun() -> loop() end),
           register(server, Pid).

loop() ->
    receive
        Msg -> io:format("node received ~p\n", [Msg]),
               loop()
    end.

