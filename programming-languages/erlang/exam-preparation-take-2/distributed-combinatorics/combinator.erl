-module(combinator).
-export([start/2]).

start(N, M) ->
    Cycles = lists:map(fun(Cycle) -> round(math:pow(M, Cycle)) end, lists:seq(0, N - 1)),
    GenPids = [spawn(generator, column, [1, M, 1, C]) || C <- lists:reverse(Cycles)],
    NumOfMessages = round(math:pow(M, N)),
    Pids = lists:flatmap(fun(_) -> GenPids end, lists:seq(1, NumOfMessages)),

    [send(PidIndex, N) || PidIndex <- lists:zip(Pids, lists:seq(1, length(Pids)))].

send({Pid, Index}, Break) ->
    Pid ! self(),
    receive
        Num -> case Index rem Break == 0 of
                   true  -> io:format("~p \n", [Num]);
                   false -> io:format("~p, ", [Num])
               end
    end.
