-module(queen_attack).

-export([can_attack/2]).


can_attack(_WhiteQueen, _BlackQueen) ->
    SameLineOrColumn = element(1, _WhiteQueen) == element(1, _BlackQueen) orelse
        element(2, _WhiteQueen) == element(2, _BlackQueen),

    XDiff = abs(element(1, _WhiteQueen) - element(1, _BlackQueen)),
    YDiff = abs(element(2, _WhiteQueen) - element(2, _BlackQueen)),

    SameLineOrColumn orelse XDiff == YDiff.
