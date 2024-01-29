-module(killing_master).
-export([start/1, loop/1, stop/0]).


start(SlaveNumber) ->
    [spawn_link(fun() -> process_flag(trap_exit, true), loop(N) end)
     || N <- lists:seq(1, SlaveNumber)].

stop() -> exit(normal).

loop(SlaveNum) ->
    receive
        {'EXIT', _, Reason} ->
            io:format("received EXIT ~p~n", [Reason]);
        Msg ->
            io:format("slave #~p received ~p~n", [SlaveNum, Msg])
    end.
