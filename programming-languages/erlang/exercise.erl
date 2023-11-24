-module(exercise).
-export([reverse/1]).

reverse([]) -> [];
reverse(Arr) -> reverse(Arr, []).
reverse([], Acc) -> Acc;
reverse([H|T], Acc) -> reverse(T, [H|Acc]).
