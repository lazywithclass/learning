-module(bank).
-export([start/0, loop/2]).

start() ->
    Pid = spawn(fun() -> loop(5000, 0) end),
    global:register_name(bank@nixos, Pid),
    ok.


loop(Balance, Seq) ->
    receive
        {From, {balance, Atm}, Local} ->
            io:format("I got a balance request from ATM ~p (local msg ~p global msg ~p)~n", [Atm, Local, Seq]),
            From ! {{balance_response, Balance, Atm}, Local},
            loop(Balance, Seq+1);
        {_From, {{withdraw, Amount}, Atm}, Local} when Amount =< Balance ->
            io:format("I got a withdraw of ~p from ATM ~p (local msg ~p global msg ~p)~n", [Amount, Atm, Local, Seq]),
            NewBalance = Balance - Amount,
            loop(NewBalance, Seq+1);
        {_From, {{deposit, Amount}, Atm}, Local} when Amount >= 0 ->
            io:format("I got a deposit of ~p from ATM ~p (local msg ~p global msg ~p)~n", [Amount, Atm, Local, Seq]),
            NewBalance = Balance + Amount,
            loop(NewBalance, Seq+1);
        Catch -> io:format("WAT ~p~n", [Catch]),
                 loop(Balance, Seq)
    end.
