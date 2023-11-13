%% Write a program that will create N processes connected in a ring. Once started, these processes will send M number of messages around the ring and then terminate gracefully when they receive a quit message. You can start the ring with the call ring:start(M, N, Message).

-module(ring).
%% Remember you need to export functions to be able to spawn them!
-export([start/3, create_node/4, loop/3]).


start(M, N, Msg) ->
    %% the parent node is the one from the erlang shell, but whatever
    spawn(ring, create_node, [M, N-1, Msg, self()]).

%% assumes we always pass N > 0
create_node(_, 0, _, _) ->
    io:format("done spawning nodes~n");
create_node(M, N, Msg, FirstPid) ->
    NextPid = spawn(ring, create_node, [M, N-1, Msg, FirstPid]),
    io:format("~p -> ~p~n", [self(), NextPid]),
    self() ! {Msg, M},
    loop(self(), NextPid, M).

loop(Self, Next, M) ->
    receive
        "Quit" ->
            io:format("~p quitting~n", [Self]),
            Next ! "Quit";
        {Msg, 0} ->
            io:format("~p got ~p, sending to ~p~n", [Self, Msg, Next]),
            loop(Self, Next, M);
        {Msg, M} ->
            io:format("~p got ~p, sending to ~p~n", [Self, Msg, Next]),
            Next ! {Msg, M - 1},
            loop(Self, Next, M - 1)
    end.
