-module(dispatcher).
-export([start/1, loop/2, loop_mm/3]).

start(BankHost) ->
    net_adm:ping(BankHost),
    Pid = spawn(dispatcher, loop, [0, []]),
    global:register_name(dispatcher@nixos, Pid),
    ok.

loop(MMSeq, AtmsPids) ->
    receive
        {From, Msg, Atm} ->
            AtmPid = find_atm_pid(Atm, AtmsPids),
            case AtmPid of
                notfound ->
                    Pid = spawn(dispatcher, loop_mm, [MMSeq+1, 0, From]),
                    Pid ! {Msg, Atm},
                    loop(MMSeq+1, [{Atm, Pid}|AtmsPids]);
                Pid -> Pid ! {Msg, Atm},
                       loop(MMSeq, AtmsPids)
            end
    end.

loop_mm(MMNum, Local, From) ->
    receive
        {balance, Atm} ->
            io:format("I am MM~p and I dealt with MSG #~p~n", [MMNum, Local]),
            Pid = global:whereis_name(bank@nixos),
            Pid ! {self(), {balance, Atm}, Local},
            %% do not increase Local, will wait for answer
            loop_mm(MMNum, Local, From);
        {{balance_response, Balance, Atm}, Local} ->
            From ! {{balance_response, Balance, Atm}, Local},
            loop_mm(MMNum, Local+1, From);
        Msg ->
            io:format("I am MM~p and I dealt with MSG #~p~n", [MMNum, Local]),
            Pid = global:whereis_name(bank@nixos),
            Pid ! {self(), Msg, Local},
            loop_mm(MMNum, Local+1, From)
    end.

find_atm_pid(_, [])               -> notfound;
find_atm_pid(Atm, [{Atm, Pid}|_]) -> Pid;
find_atm_pid(Atm, [_|Atms])       -> find_atm_pid(Atm, Atms).
