-module(j).
-export([joseph/2, node_loop/3]).


%% N is the number of nodes
%% K is the Killing position
joseph(N, K) ->
    register(joseph, self()),
    create(0, N, undef, undef),
    receive
        {done, FirstPid} ->
            %% io:format("created all nodes~n"),
            start_the_killing(FirstPid, K)
    end,
    unregister(joseph).

create(N, N, PrevPid, FirstPid) ->
    FirstPid ! {setprev, PrevPid},
    PrevPid ! {setnext, FirstPid},
    joseph ! {done, FirstPid};
create(N, Max, undef, undef) ->
    P = spawn(j, node_loop, [N, undef, undef]),
    %% io:format("created node 0 (~p)~n", [P]),
    create(N+1, Max, P, P);
create(N, Max, PrevPid, FirstPid) ->
    P = spawn(j, node_loop, [N, PrevPid, undef]),
    %% io:format("created node ~p (~p)~n", [N, P]),
    PrevPid ! {setnext, P},
    create(N+1, Max, P, FirstPid).

node_loop(Number, PrevPid, NextPid) ->
    receive
        {countdown, _, _} when NextPid == self() ->
            io:format("last man standing ~p (~p)~n", [Number, self()]);
        {countdown, 0, Max} ->
            %% io:format("~p (~p) suicides~n", [Number, self()]),
            PrevPid ! {setnext, NextPid},
            NextPid ! {setprev, PrevPid},
            NextPid ! {countdown, Max-1, Max};
        {countdown, N, Max} ->
            %% io:format("~p sent from ~p to ~p~n", [N-1, self(), NextPid]),
            NextPid ! {countdown, N-1, Max},
            node_loop(Number, PrevPid, NextPid);
        {setprev, Pid} ->
            %% io:format("~p (~p) prev (~p)~n", [Number, self(), Pid]),
            node_loop(Number, Pid, NextPid);
        {setnext, Pid} ->
            %% io:format("~p (~p) next (~p) prev (~p)~n", [Number, self(), Pid, PrevPid]),
            node_loop(Number, PrevPid, Pid)
    end.

start_the_killing(Pid, K) ->
    %% io:format("starting the killing from ~p~n", [Pid]),
    Pid ! {countdown, K, K}.
