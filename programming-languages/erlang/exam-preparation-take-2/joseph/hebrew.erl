-module(hebrew).
-export([loop/4]).


loop(Position, Jumps, Prev, Next) ->
    receive
        { setprev, Pid } -> %% io:format("~p prev is ~p\n", [Position, Pid]),
                            loop(Position, Jumps, Pid, Next);
        { setnext, Pid } -> %% io:format("~p next is ~p\n", [Position, Pid]),
                            loop(Position, Jumps, Prev, Pid);

        { count, 0 } when Prev == self() -> joseph ! { found, Position };
        { count, 0 }                     -> %% io:format("setting prev and next\n"),
                                            Prev ! { setnext, Next },
                                            Next ! { setprev, Prev },
                                            Next ! { count, Jumps - 1 };
        { count, C }                     -> %% io:format("forwarding count ~p\n", [C]),
                                            Next ! { count, C - 1 },
                                            loop(Position, Jumps, Prev, Next)
    end.
