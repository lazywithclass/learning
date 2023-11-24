-module(matrix).
-export([mproduct/2, spawn_rows/3]).

%% !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
%% when dealing with lists:nth, lists start from 1
%% !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


%% [
%%   [1,1,2],
%%   [0,1,3],
%% ]

mproduct(A, B) ->
    ColumnPids = spawn_columns(A, length(B), []),
    spawn_rows(A, ColumnPids, length(A)),
    ciao.

spawn_columns(B, 0, Pids) -> io:format("done spawning columns~n"), Pids;
spawn_columns(B, Index, Pids) ->
    Column = lists:map(fun(Row) -> lists:nth(Index, Row) end, B),
    Pid = spawn(matrix, loop_column, [Column]),
    spawn_columns(B, Index-1, [Pid|Pids]).

loop_column(Column) ->
    receive
        {Row, Index} -> Products = lists:map()
        end.

sum([], []) -> 0;
sum(Row, Column) ->
    case



%% could be done recurring on the list by shrinking it without the index
spawn_rows(_, _, 0) -> io:format("done spawning rows~n");
spawn_rows(A, ColumnPids, Index) ->
    R = lists:nth(Index, A),
    io:format("spawning row ~p ~p~n", [R, Index]),
    lists:foreach(fun(ColumnPid) -> ColumnPid ! {R, Index} end, ColumnPids),
    spawn_rows(R, ColumnPids, Index-1).
