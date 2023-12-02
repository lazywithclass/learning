-module(server).
-export([start/0, print/1, stop/0, loop/0]).


start() ->
    register(echo_server, spawn(fun() -> loop() end)).

loop() ->
    receive
        stop -> io:format("stopping...~n"),
                stop();
        Msg -> io:format("received ~p~n", [Msg]),
                loop()
    end.

print(Msg) -> echo ! Msg.

stop() -> unregister(echo_server), exit(usti).
