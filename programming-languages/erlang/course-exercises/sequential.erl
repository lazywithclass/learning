-module(sequential).
-export([
         is_palindrome/1
        ]).


is_letter(L) -> (L >= 65 andalso L =< 90) orelse (L >= 97 andalso L =< 122).

to_upper(L) when L >= 97 andalso L =< 122 -> L - 32;
to_upper(L)                               -> L.

reverse([], Acc)    -> Acc;
reverse([H|T], Acc) -> reverse(T, [H|Acc]).

is_palindrome(S) ->
    U = lists:map(fun to_upper/1, S),
    F = lists:filter(fun is_letter/1, U),
    Rev = reverse(F, []),
    F == Rev.

% "Do geese see God?"
% "Rise to vote, sir."
