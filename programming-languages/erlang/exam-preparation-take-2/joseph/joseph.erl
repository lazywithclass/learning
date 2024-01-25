-module(joseph).
-export([joseph/2, create_nodes/3]).


joseph(Nodes, Jumps) ->
    io:format("In a circle of ~p people killing number ~p\n", [Nodes, Jumps]),

    Pids = create_nodes(Nodes, Jumps, []),

    { FirstPid, FirstN } = lists:nth(1, Pids),
    { LastPid, LastN } = lists:last(Pids),

    FirstPid ! { setprev, LastPid },
    LastPid ! { setnext, FirstPid },
    set_prevs({FirstPid, FirstN}, lists:nthtail(1, Pids)),
    set_nexts({LastPid, LastN}, lists:nthtail(1, lists:reverse(Pids))),

    FirstPid ! { count, Jumps - 1 },
    %% the following is only needed to block the execution one at a time
    register(joseph, self()),
    receive
        { found, Position } -> io:format("Found at position ~p\n", [Position])
    end,
    unregister(joseph).

set_prevs(_, []) -> ok;
set_prevs({PrevPid, _}, [{Pid, N}|Pids]) ->
    Pid ! { setprev, PrevPid },
    set_prevs({Pid, N}, Pids).

set_nexts(_, []) -> ok;
set_nexts({NextPid, _}, [{Pid, N}|Pids]) ->
    Pid ! { setnext, NextPid },
    set_nexts({Pid, N}, Pids).

create_nodes(0, _, Pids)     -> %% io:format("done creating nodes\n"),
                                Pids;
create_nodes(N, Jumps, Pids) ->
    Pid = spawn(hebrew, loop, [N, Jumps, nil, nil]),
    create_nodes(N - 1, Jumps, [{Pid,N}|Pids]).
