-module(generator).
-export([column/4]).


column(Curr, To, IterationMax, IterationMax) ->
    receive
        From -> From ! Curr,
                column(next_num(Curr, To), To, 1, IterationMax)
    end;
column(Curr, To, IterationIndex, IterationMax) ->
    receive
        From -> From ! Curr,
                column(Curr, To, IterationIndex + 1, IterationMax)
    end.

next_num(Curr, To) when Curr < To -> Curr + 1;
next_num(_, _)                    -> 1.
