-module(symmetric_difference).
-export([sd/2, sdlc/2]).


sd(L1, L2) ->
    L1Not = lists:filter(fun(E) -> not lists:member(E, L2) end, L1),
    L2Not = lists:filter(fun(E) -> not lists:member(E, L1) end, L2),
    L1Not ++ L2Not.

%% list comprehension solution
sdlc(L1, L2) ->
    [X || X <- L1, not lists:member(X, L2)] ++
        [Y || Y <- L2, not lists:member(Y, L1)].
