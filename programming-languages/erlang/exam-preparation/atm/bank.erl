-module(bank).
-export([start/0, loop/2]).

start() ->
    Pid = loop(5000, 0),
    %% TODO in obsidian metti note su questo
    global:register_name(bank, Pid),
    ok.

%% TODO
%% balance sync? che vuol dire?

loop(Balance, Seq) ->
    receive
        {Seq, From, balance, Atm, Local} ->
            io:format("I got a balance request from ATM ~p (local msg ~p global msg ~p)~n", [Atm, Local, Seq]),
            From ! {Seq, ok, Balance},
            loop(Balance, Seq+1);
        {Seq, From, withdraw, Amount, Atm, Local} when Amount =< Balance ->
            io:format("I got a withdraw of ~p from ATM ~p (local msg ~p global msg ~p)~n", [Amount, Atm, Local, Seq]),
            NewBalance = Balance - Amount,
            From ! {Seq, ok, NewBalance},
            loop(NewBalance, Seq+1);
        {Seq, From, withdraw, _, _, _} ->
            From ! {Seq, insufficient_balance},
            loop(Balance, Seq+1);
        {Seq, From, deposit, Amount, Atm, Local} when Amount >= 0 ->
            io:format("I got a deposit of ~p from ATM ~p (local msg ~p global msg ~p)~n", [Amount, Atm, Local, Seq]),
            NewBalance = Balance + Amount,
            From ! {Seq, ok, NewBalance},
            loop(NewBalance, Seq+1);
        {Seq, From, deposit, _, _, _} ->
            From ! {Seq, negative_deposit},
            loop(Balance, Seq+1)
    end.
