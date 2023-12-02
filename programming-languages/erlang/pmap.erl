-module(pmap).
-export([pmap/1, future/2, yield/1]).

%% write a parallel version of map


rpc(Pid, Request) ->
    Tag = erlang:make_ref(),
    %% make the request
    Pid ! {self(), Tag, Request},
    receive
        %% wait for that message
        {Tag, Response} -> Response
    end.

%% which then becomes the following if message being send
%% and received are split in two functions

future(Pid, Request) ->
    Ref = erlang:make_ref(),
    Pid ! {self(), Ref, Request},
    Ref.

yield(Tag) ->
    receive
        {Tag, Response} -> Response
    end.

%% we just wrote futures from the primitives of the language
%% which could be used as in

pmap(L) ->
    S = self(),
    Pids = [do(S, F) || F <- L],
    [receive {Pid, Val} -> Val end || Pid <- Pids].

do(Parent, F) -> spawn(fun() -> Parent ! {self(), F()} end).
