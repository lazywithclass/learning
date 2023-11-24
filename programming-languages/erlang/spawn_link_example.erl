-module(spawn_link_example).
-export([start/0, loop/0, inverse/1]).

start() ->
    process_flag(trap_exit, true),
    spawn_link(spawn_link_example, inverse, [0]),
    loop().

loop() ->
    receive
        Msg -> io:format("~p~n", [Msg])
    end,
    loop().

inverse(N) -> 1/N.
