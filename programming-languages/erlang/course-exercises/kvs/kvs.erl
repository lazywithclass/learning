-module(kvs).
-export([start/0, store/2, lookup/1]).

start() -> register(kvs, spawn(fun() -> loop() end)).
store(Key, Value) -> rpc({store, Key, Value}).
lookup(Key) -> 
    rpc({lookup, Key}).

rpc(Q) ->
    io:format("wat ~p~n", [kvs]),
    kvs ! {self(), Q},
    receive
        {kvs, Reply} -> Reply
    end.

loop() ->
    receive
        {From, {store, Key, Value}} -> put(Key, {ok, Value}), From ! {kvs, true}, loop();
        {From, {lookup, Key}} -> From ! {kvs, get(Key)}, loop()
    end.


%% let's assume that I start two nodes, a and b
%% and then I do

%% (a@nixos)1> kvs:start().
%% true
%% (b@nixos)1> rpc:call(a@nixos, kvs, store, [weather, sunny]).
%% true

%% why is the following happening? Notice I am calling from b
%% (b@nixos)2> kvs:lookup(weather).
%% ** exception error: bad argument
%%      in function  erlang:send/2
%%         called as erlang:send(kvs,{<0.88.0>,{lookup,weather}})
%%         *** argument 1: invalid destination
%%      in call from kvs:rpc/1 (kvs.erl, line 10)
