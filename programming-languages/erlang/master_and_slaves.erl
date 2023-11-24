-module(master_and_slaves).
-export([
         start/1,
         spawn_slaves/1,
         slave_loop/1,
         to_slave/2,
         master_loop/1,
         spawn_and_wait/1
        ]).

start(N) ->
    Master = spawn(master_and_slaves, spawn_and_wait, [N]),
    register(master, Master).

%% this is needed so that the spawned slaves could be linked in
%% the same process, and so when they die they can be respawned
spawn_and_wait(N) ->
    %% dont die
    process_flag(trap_exit, true),
    Spawned = spawn_slaves(N),
    master_loop(Spawned).

spawn_slaves(0) -> io:format("spawned all slaves~n"), [];
spawn_slaves(N) ->
    Pid = spawn_link(master_and_slaves, slave_loop, [N]),
    io:format("spawned and monitored slave ~p (~p)~n", [N, Pid]),
    [{N, Pid}|spawn_slaves(N-1)].

find_by_pid(Pid, []) -> io:format("~p was not found!~n", [Pid]);
find_by_pid(Pid, Spawned) ->
    case Spawned of
        [{N, Pid}|_] -> N;
        [_|T] -> find_by_pid(Pid, T)
    end.

find_by_n(N, []) -> io:format("~p was not found!~n", [N]);
find_by_n(N, Spawned) ->
    case Spawned of
        [{N, Pid}|_] -> Pid;
        [_|T] -> find_by_n(N, T)
    end.

update_pid(N, _, []) -> io:format("~p was not found!~n", N);
update_pid(N, Pid, Spawned) ->
    case Spawned of
        [{N, _}|T] -> [{N, Pid}|T];
        [H|T] -> [H|update_pid(N, Pid, T)]
    end.

master_loop(Spawned) ->
    receive
        {'EXIT', Pid, _Why} ->
            N = find_by_pid(Pid, Spawned),
            io:format("~p exited, found ~p~n", [Pid, N]),
            PidNew = spawn_link(master_and_slaves, slave_loop, [N]),
            io:format("re-spawned and monitored slave ~p (~p)~n", [N, PidNew]),
            NewSpawned = update_pid(N, PidNew, Spawned),
            io:format("updated spawned list ~p~n", [NewSpawned]),
            master_loop(NewSpawned);
        {Msg, N} ->
            Pid = find_by_n(N, Spawned),
            io:format("sending ~p to ~p (~p)~n", [Msg, N, Pid]),
            Pid ! Msg,
            master_loop(Spawned)
    end.

slave_loop(N) ->
    receive
        die -> io:format("slave ~p commits suicide~n", [N]), exit(normal);
        Msg -> io:format("slave ~p received ~p~n", [N, Msg]), slave_loop(N)
    end.

to_slave(Msg, N) -> master ! {Msg,N}.
