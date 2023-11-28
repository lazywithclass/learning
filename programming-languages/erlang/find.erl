-module(find).
-export([contains/2]).

contains(X, []) -> false;
contains(X, [X|Xs]) -> true;
contains(X, [_|Xs]) -> contains(X, Xs).
