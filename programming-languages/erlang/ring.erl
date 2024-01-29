-module(ring).
-export([create/0]).


%% I create a fake array of data just to show of
%% state is passed around in the Ring
%% The first node of the Ring also communicates with the exterior
%% Other nodes communicate with the exterior through this first node

create() ->
    NumArr = lists:seq(1, 5),
    %% first spawn all processes with the function that gives the loop the data
    Pids = [spawn(fun() -> init(Num) end) || Num <- NumArr],
    io:format("created ~p~n", [Pids]),
    [Gate|[Next|PidsTail]] = Pids,
    %% configure the Gate
    Gate ! {config, Gate, Next},
    %% configure the rest
    create_ring(Gate, [Next|PidsTail]).

create_ring(_, []) -> io:format("finished~n");
create_ring(Gate, [Pid|Pids]) ->
   case Pids of
       [] ->
           Pid ! {config, Gate, Gate},
           io:format("finished~n");
       [Next|PidsTail] ->
           Pid ! {config, Gate, Next},
           create_ring(Gate, [Next|PidsTail])
   end.

init(Num) ->
    receive
        {config, Gate, Next} ->
            io:format("config ~p->~p~n", [self(), Next]),
            loop(Gate, Next, Num);
        Any -> io:format("unexpected ~p~n", [Any])
    end.

%% this is the loop that deals with data
loop(Gate, Next, Num) ->
    receive
        {pass, From} ->
            io:format("pass ~p->~p~n", [From, Next]),
            loop(Gate, Next, Num)
    end.
