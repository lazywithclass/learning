% Write a program that will create N processes connected in a ring. Once started, these processes will send M number of messages around the ring and then terminate gracefully when they receive a quit message. You can start the ring with the call ring:start(M, N, Message).

-module(ring).
% Remember you need to export functions to be able to spawn them!
-export([start/3, create_node/3, create_node/4]).


start(M, N, Msg) ->
    spawn(ring, create_node, [M, N, Msg]).

create_node(M, N, Msg) ->
    Pid = spawn(ring, create_node, [M, N-1, Msg, self()]),
    io:format("spawned node ~p -> ~p~n", [self(), Pid]),
    loop(self(), Pid, M).

% assumes we always pass N >= 0
create_node(_, 0, _, _) ->
    io:format("done spawning nodes~n");
create_node(M, 1, Msg, FirstPid) ->
    io:format("spawned node ~p -> ~p~n", [self(), FirstPid]),
    %send_msg(FirstPid, Msg, M-1),
    loop(self(), FirstPid, M);
create_node(M, N, Msg, FirstPid) ->
    NextPid = spawn(ring, create_node, [M, N-1, Msg, FirstPid]),
    io:format("spawned node ~p -> ~p~n", [self(), NextPid]),
    %send_msg(NextPid, Msg, M-1),
    loop(self(), NextPid, M).

send_msg(Pid, Msg, 1) ->
    Pid ! Msg;
send_msg(Pid, Msg, Count) ->
    Pid ! Msg,
    send_msg(Pid, Msg, Count - 1).

loop(Self, Next, M) ->
    receive
        "Quit" ->
            io:format("~p quitting~n", [Self]),
            send_msg(Next, "Quit", M);
        Msg ->
            io:format("~p got ~p, sending to ~p~n", [Self, Msg, Next]),
            send_msg(Next, Msg, M),
            loop(Self, Next, M)
    end.
