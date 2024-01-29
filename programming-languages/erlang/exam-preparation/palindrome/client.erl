-module(client).
-export([start/0, close/0, is_palindrome/1]).


start() ->
    global:register_name(mm1, spawn(mm@nixos, mm, loop, [1])),
    global:register_name(mm2, spawn(mm@nixos, mm, loop, [2])),
    global:register_name(server, spawn(server@nixos, server, loop, [1, true])),
    ok.

close() ->
    %% TODO
    ok.

is_palindrome(Xs) ->
    {Xs1, Xs2} = lists:split(round(length(Xs) / 2), Xs),
    send(Xs1, 1, global:whereis_name(mm1)),
    send(lists:reverse(Xs2), 1, global:whereis_name(mm2)),
    is_palindrome.

send([], _, Pid)       -> Pid ! finished;
send([X|Xs], Seq, Pid) -> Pid ! {el, X, Seq}, send(Xs, Seq+1, Pid).
