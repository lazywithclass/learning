-module(hc).
-export([create/0, neighbours/1, loop_node/3, loop_main/1, hamilton/2]).

%% because we cant register Nodes (as it was in the assignment) it
%% all becomes way more difficult
%% just one is registered (assignment wasnt super clear to me)

create() ->
    Nodes = [
             [0,0,0,0],
             [0,0,0,1],
             [0,0,1,1],
             [0,0,1,0],
             [0,1,1,0],
             [0,1,1,1],
             [0,1,0,1],
             [0,1,0,0],
             [1,1,0,0],
             [1,1,0,1],
             [1,1,1,1],
             [1,1,1,0],
             [1,0,1,0],
             [1,0,1,1],
             [1,0,0,1],
             [1,0,0,0]
            ],
    NodePids = lists:map(
                 fun(Node) ->
                         Pid = spawn(hc, loop_node, [Node, {}, []]),
                         io:format("spawned ~p (~p)~n", [Node, Pid]),
                         {Node, Pid}
                 end,
                 Nodes),

    %% all neighbours of this Node will receive its Pid, so
    %% that they can store it
    lists:foreach(fun(NP) -> notify_pids(NP, NodePids) end, NodePids),
    MainPid = spawn(hc, loop_main, [NodePids]),
    register(hypercube, MainPid).

hamilton(Msg, Path) -> hypercube ! {Msg, Path}.

%% is this actually called just once?
loop_main(NodePids) ->
    receive
        {Msg, [Node|Path]} ->
            Pid = find_pid(Node, NodePids),
            Pid ! {{msg, src, Node, Msg}, Path},
            loop_main(NodePids)
    end.

%% in a real world scenario we would deal with this "not found scenario", which, if
%% I understand my code correctly, means that we did not find the Pid in the
%% neighbours, so we are done
find_pid(Node, [])              -> io:format("node ~p not found in the neigbours, exiting~n", [Node]),
                                   unregister(hypercube),
                                   exit(normal);
find_pid(Node, [{Node, Pid}|_]) -> Pid;
find_pid(Node, [_|Pids])        -> find_pid(Node, Pids).

notify_pids({Node, Pid}, NodePids) ->
    Neighbours = neighbours(Node),
    NPs = lists:filter(fun({N, _}) ->
                               lists:member(N, Neighbours)
                       end, NodePids),
    lists:foreach(fun({N, P}) -> Pid ! {set_neighbour, N, P} end, NPs).

neighbours([Fi, Se, Th, Fo]) ->
    [
     [Fi, Se, Th, (Fo+1) rem 2],
     [Fi, Se, (Th+1) rem 2, Fo],
     [Fi, (Se+1) rem 2, Th, Fo],
     [(Fi+1) rem 2, Se, Th, Fo]
    ].

loop_node(Node, Msg, NeighbourPids) ->
    receive
        {set_neighbour, NeighbourNode, NeighbourPid} ->
            %% io:format("~p received ~p as pid for its neighbour ~p~n", [Node, NeighbourPid, NeighbourNode]),
            loop_node(Node, Msg, [{NeighbourNode, NeighbourPid}|NeighbourPids]);
        {Message, []} -> io:format("loop_node: thanks Church we are done~n~p~n", [Message]);
        %% use a different name so we dont pattern match on the function parameter
        {Message, [NextNode|Path]} ->
            Pid = find_pid(NextNode, NeighbourPids),
            %% io:format("loop_node: found pid ~p sending message~n", [Pid]),
            NewMsg = {msg, {src, NextNode, Message}},
            Pid ! {NewMsg, Path},
            loop_node(Node, NewMsg, NeighbourPids);
        Else -> io:format("loop_node: wat? ~p~n", [Else]),
                loop_node(Node, Msg, NeighbourPids)
    end.
