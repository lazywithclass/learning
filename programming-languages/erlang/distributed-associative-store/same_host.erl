-module(same_host).
-export([start/0, main_loop/3, node_loop/2]).


start() ->
    Store = [],
    Node1 = spawn(same_host, node_loop, [Store, node1]),
    Node2 = spawn(same_host, node_loop, [Store, node2]),
    spawn(same_host, main_loop , [Store, [Node1, Node2], 1]).

main_loop(Store, Pids, Index) ->
    receive
        {set, Label, Value} ->
            NewStore = update_store(Store, Label, Value),
            lists:foreach(fun(Pid) -> Pid ! {set, NewStore} end, Pids),
            main_loop(NewStore, Pids, Index);
        {get, Label} ->
            io:format("index ~p~n", [Index]),
            Pid = lists:nth(Index, Pids),
            Pid ! {get, Label},
            io:format("~p ~p~n", [Index, length(Store)]),
            main_loop(Store, Pids, alternate_index(Index, length(Pids)))
    end.

%% thanks to the horrific 1-based list I got the chance to write
%% this function, which I find quite beatiful
alternate_index(Prev, Prev) -> 1;
alternate_index(Prev, _) -> Prev + 1.

update_store([], Label, Value) -> [{Label, [Value]}];
update_store(Store, Label, Value) ->
    case Store of
        [{Label, Values}|T] -> [{Label, [Value|Values]}|T];
        [H|T] -> [H|update_store(T, Label, Value)]
    end.

%% TODO option?
query_store([], _) -> {};
query_store(Store, Label) ->
    case Store of
        [{Label, Values}|_] -> Values;
        [_|T] -> query_store(T, Label)
    end.

%% we are on the same host, I dont mind sending the whole Store
%% down to the nodes (for the set operation)
node_loop(Store, Name) ->
    receive
        {set, NewStore} ->
            io:format("~p received set~n", [Name]),
            node_loop(NewStore, Name);
        {get, Label} ->
            io:format("~p received get~n", [Name]),
            Values = query_store(Store, Label),
            io:format("values for ~p: ~p~n", [Label, Values]),
            node_loop(Store, Name)
    end.
