-module(mm).
-export([loop/1]).


loop(MMNum) ->
    %% update group leader notes in obsidian
    group_leader(whereis(user), self()),
    receive
        Msg -> io:format("#~p received ~p~n", [MMNum, Msg]),
               global:whereis_name(server) ! {Msg, MMNum},
               loop(MMNum)
    end.
