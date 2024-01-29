-module(server).
-export([loop/2]).


loop(Seq, IsPalindrome) ->
    group_leader(whereis(user), self()),
    receive
        {finished, From1} ->
            receive
                {finished, From2} when From1 /= From2 ->
                    io:format("is it palindrome? ~p~n", [IsPalindrome])
            end;
        {{el, X1, Seq}, From1} ->
            receive
                {{el, X2, Seq}, From2} when From1 /= From2 ->
                    io:format("server received ~p and ~p~n", [X1, X2]),
                    %% go onto the next element
                    loop(Seq+1, IsPalindrome andalso X1 == X2)
            end
    end.
