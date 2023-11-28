-module(generate_slaves).
-export([start/1, to_slave/2, master/3, spawn_processes/1]).

start(N) ->
    io:format("GENERATOR OF BOTH MASTER AND SLAVES IS: ~p\n",[self()]),
    register(main,self()),
    Master=spawn(fun()->master([],N,main)end),
    register(master,Master),
    io:format("MASTER PROCESS IS: ~p \n",[Master]),
    receive
        {Slaves} -> io:format("~p\n",[Slaves]);
        _->io:format("Not supported\n")
    end.


spawn_processes(N) when N==0 -> [];
spawn_processes(N) -> [spawn_link(fun()->slaves()end)|spawn_processes(N-1)].


master(Slaves,N,Spawner) ->
    case Slaves of
        [] ->
            Slaves=spawn_processes(N),
            Spawner!{Slaves},
            master(Slaves,N,Spawner);
        Slaves -> ok
    end.

slaves() ->
    receive
        {From,{msg}} ->
            From!{ack,msg},
            slaves()
    end.

to_slave(Msg,N) -> {Msg,N}.
