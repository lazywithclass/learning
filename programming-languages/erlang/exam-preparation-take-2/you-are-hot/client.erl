-module(client).
-export([convert/5]).


convert(from, From, to, To, Deg) ->
    register(client, self()),
    second ! { from, From, to, To, Deg },
    receive
        Res -> io:format("~p°~s are equivalent to ~p°~s\n", [Deg, From, Res, To]),
               unregister(client)
    end.
