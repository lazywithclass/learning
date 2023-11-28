-module(matrix).
-export([
         mproduct/2,
         master_loop/3,
         spawn_row_nodes/3,
         spawn_col_nodes/4,
         col_node_loop/5,
         to_columns/2
        ]).


%% MatrixA = [[1,1,2], [0,1,-3]].
%% MatrixB = [[1,2,0], [1,5,-2], [1,1,1]].


%% PAIN POINTS
%% Lots of reverse

mproduct(A, B) ->
    %% TODO invert B so that its rows are actually its columns
    ColPids = spawn_col_nodes(to_columns(B, 1), 1, length(A), []),
    %% why can't I call self() in an anonymous function passed to spawn?
    %% why cant I pass an anonimous function to spawn
    Pid = spawn(matrix, master_loop, [1, length(B), []]),
    register(master, Pid),
    spawn_row_nodes(A, 1, ColPids).

master_loop(Index, ColTot, Result) ->
    receive
        {ColTot, Sums} ->
            Res = lists:reverse([Sums|Result]),
            io:format("result: ~p~n", [Res]);
        {Index, Sums} -> master_loop(Index+1, ColTot, [Sums|Result])
    end.

spawn_row_nodes([], _, _) -> ok_spawn_row_nodes;
spawn_row_nodes([Row|Rows], Index, ColPids) ->
    lists:foreach(fun(ColPid) -> ColPid ! {Index, Row} end, ColPids),
    spawn_row_nodes(Rows, Index+1, ColPids).

spawn_col_nodes([], _, _, ColPids) -> ColPids;
spawn_col_nodes([Col|Cols], RowIndex, RowTot, ColPids) ->
    ColPid = spawn(matrix, col_node_loop, [1, RowIndex, Col, RowTot, []]),
    spawn_col_nodes(Cols, RowIndex+1, RowTot, [ColPid|ColPids]).

to_columns(Matrix, Index) ->
    Length = length(lists:nth(1, Matrix)),
    case Index > Length of
        true -> [];
        false ->
            Column = lists:map(fun(Row) -> lists:nth(Index, Row) end, Matrix),
            [Column|to_columns(Matrix, Index+1)]
    end.

col_node_loop(Index, RowIndex, Col, Tot, Sums) ->
    receive
        {Tot, Row} ->
            Pairs = lists:zip(Row, Col),
            Products = lists:map(fun({R, C}) -> R*C end, Pairs),
            Sum = lists:foldl(fun(S, Sum) -> S+Sum end, 0, Products),
            master ! {RowIndex, lists:reverse([Sum|Sums])};
        {Index, Row} ->
            Pairs = lists:zip(Row, Col),
            Products = lists:map(fun({R, C}) -> R*C end, Pairs),
            Sum = lists:foldl(fun(S, Sum) -> S+Sum end, 0, Products),
            col_node_loop(Index+1, RowIndex, Col, Tot, lists:reverse([Sum|Sums]))
    end.
