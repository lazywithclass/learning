-module(client).
-export([start/0, send/1, loop/0]).

start() ->
    io:format("linked self with ~p~n", [erlang:whereis(echo_server)]),
    spawn(client, loop, []).

loop() ->
    process_flag(trap_exit, true),
    link(erlang:whereis(echo_server)),
    receive
        {'EXIT', Pid, Why} ->
            io:format("bye (~p), cause ~p~n", [Pid, Why]);
        Msg -> io:format("received ~p~n", [Msg])
    end.

send(Msg) -> erlang:whereis(echo_server) ! Msg.
