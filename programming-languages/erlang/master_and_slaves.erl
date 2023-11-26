%% This problem illustrates a situation where we have a process (the master) which supervises other processes (the slaves). In a real example the slave could, for example, be controlling different hardware units. The master's job is to ensure that all the slave processes are alive. If a slave crashes (maybe because of a software fault), the master is to recreate the failed slave.

%% Write a module ms with the following interface:

%% start(N) which starts the master and tell it to start N slave processes and registers the master as the registered process master.
%% to_slave(Message, N) which sends a message to the master and tells it to relay the message to slave N; the slave should exit (and be restarted by the master) if the message is die.
%% the master should detect the fact that a slave process dies, restart it and print a message that it has done so.
%% The slave should print all messages it receives except the message die
%% Hints:

%% the master should trap exit messages and create links to all the slave processes.
%% the master should keep a list of pids of the slave processes and their associated numbers.

-module(master_and_slaves).
-export([
         start/1,
         spawn_slaves/2,
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
    Spawned = spawn_slaves(1, N),
    master_loop(Spawned).

spawn_slaves(From, To) ->
    case From == To of
        true -> io:format("finished spawning~n");
        false ->
            Pid = spawn_link(master_and_slaves, slave_loop, [From]),
            io:format("spawned and monitored slave ~p (~p)~n", [From, Pid]),
            [Pid|spawn_slaves(From+1, To)]
    end.

update_spawned(PidOld, _, []) -> io:format("~p was not found!~n", [PidOld]);
update_spawned(PidOld, PidNew, Spawned) ->
    case Spawned of
        [PidOld|T] -> [PidNew|T];
        [H|T] -> [H|update_spawned(PidOld, PidNew, T)]
    end.

find_position(Pid, []) -> io:format("~p was not found!~n", [Pid]);
find_position(Pid, Spawned) ->
    case Spawned of
        [Pid|_] -> 1;
        [_|T] -> 1 + find_position(Pid, T)
    end.

master_loop(Spawned) ->
    receive
        {'EXIT', Pid, _Why} ->
            io:format("~p exited~n", [Pid]),
            Pos = find_position(Pid, Spawned),
            PidNew = spawn_link(master_and_slaves, slave_loop, [Pos]),
            io:format("re-spawned and monitored slave ~p (~p)~n", [Pos, PidNew]),
            NewSpawned = update_spawned(Pid, PidNew, Spawned),
            master_loop(NewSpawned);
        {Msg, Pos} ->
            Pid = lists:nth(Pos, Spawned),
            io:format("sending ~p to ~p (~p)~n", [Msg, Pos, Pid]),
            Pid ! Msg,
            master_loop(Spawned)
    end.

slave_loop(Pos) ->
    receive
        die -> io:format("slave ~p commits suicide~n", [Pos]), exit(normal);
        Msg -> io:format("slave ~p received ~p~n", [Pos, Msg]), slave_loop(Pos)
    end.

to_slave(Msg, N) -> master ! {Msg,N}.
