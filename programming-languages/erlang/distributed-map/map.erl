-module(map).
-export([start/0, loop/2]).


%% A distributed map that allows to query a map
%% exclusively to clients that register themselves


start() -> Pid = spawn(fun() -> loop(#{}, []) end),
           register(server, Pid).

is_registered(_, [])          -> false;
is_registered(User, [{User, _}|_]) -> true.

get_pid(_, [])                 -> notfound;
get_pid(User, [{User, Pid}|_]) -> Pid.

loop(Map, Users) ->
    receive
        { register, User, Pid }   -> loop(Map, [{User, Pid}|Users]);
        { set, Key, Value, User } -> case is_registered(User, Users) of
                                         true -> loop(maps:put(Map, Key, Value), Users);
                                         _    -> loop(Map, Users)
                                     end;
        { get, Key, User }        -> case is_registered(User, Users) of
                                         true ->
                                             Pid = get_pid(User, Users),
                                             case Pid of
                                                 notfound -> ok;
                                                 Pid      -> Pid ! maps:get(Map, Key)
                                             end,
                                             loop(Map, Users);
                                         _    -> loop(Map, Users)
                                     end
    end.
