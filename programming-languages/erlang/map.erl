-module(map).
-export([map/2, maplc/2]).

map(_, []) -> [];
map(F, [H|T]) -> [F(H)|map(F, T)].

%% map with list comprehension
maplc(F, L) -> [F(H) || H <- L].
