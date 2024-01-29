%% a system process spawn_links N more processes
%% and watches them die

-module(killing_slaves).
-export([start/1, loop/1]).


start(N) -> process_flag(trap_exit, true),
            init(N, self(), []),
            receive
                {'EXIT', From, Why} -> io:format("start: (~p) exited ~p ~p~n", [self(), From, Why]);
                {done, Pids}        -> io:format("start: killing slaves~n"),
                                       lists:foreach(fun(S) -> S ! die end, Pids),
                                       %% ugly, I know, imagine you sent a
                                       %% message instead of sleeping
                                       timer:sleep(100);
                Any -> io:format("any ~p~n", [Any])
            end.

init(0, Parent, Pids) -> io:format("init: done spawning slaves~n"),
                         io:format("~p~n", [Parent]),
                         Parent ! {done, Pids};
init(N, Parent, Pids) -> Pid = spawn_link(killing_slaves, loop, [N]),
                         io:format("init: spawned ~p (~p)~n", [N, Pid]),
                         init(N-1, Parent, [Pid|Pids]).

loop(SlaveNum) ->
    receive
        Msg -> io:format("slave #~p received ~p~n", [SlaveNum, Msg]),
               exit(normal)
    end.
