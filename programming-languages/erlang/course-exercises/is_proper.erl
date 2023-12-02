-module(is_proper).
-export([is_proper/1, divisors/2]).


is_proper(N) ->
    SumDivs = lists:foldl(fun(S, D) -> S+D end, 0, divisors(N, 1)),
    N > 0 andalso N == SumDivs.

divisors(N, From) when From >= N       -> [];
divisors(N, From) when N rem From == 0 -> [From|divisors(N, From+1)];
divisors(N, From)                      -> divisors(N, From+1).
