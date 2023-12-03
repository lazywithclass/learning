-module(atm).
-export([start/1, loop/2, balance/2, deposit/3, withdraw/3]).

start(Seq) ->
    Pid = spawn(atm, loop, [Seq, 0]),
    global:register_name("Atm"++Seq, Pid).

loop(Atm, Local) ->
    receive
        {{balance_response, Balance, Atm}, Local2} ->
            io:format("Currently, the balance is ~p~n", [Balance]),
            loop(Atm, Local2+1);
        Catch -> io:format("~p ~p ~p~n", [Catch, Atm, Local]),
                 loop(Atm, Local)
    end.

balance(Host, Seq) ->
    Pid = global:whereis_name(Host),
    Pid ! {global:whereis_name("Atm"++Seq), balance, Seq}.

deposit(Host, Seq, Amount) ->
    Pid = global:whereis_name(Host),
    Pid ! {global:whereis_name("Atm"++Seq), {deposit, Amount}, Seq}.

withdraw(Host, Seq, Amount) ->
    Pid = global:whereis_name(Host),
    Pid ! {global:whereis_name("Atm"++Seq), {withdraw, Amount}, Seq}.
