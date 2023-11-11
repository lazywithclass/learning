%% Write a module counting which provides the functionality for interacting with a server that counts how many times its services has been requested.
%% It has to implement several services dummy1, ... dummyn (doesn't matter what they do or their real interface) and a service tot that returns a list of records indexed on each service (tot included) containing also how many times such a service has been requested. Test it from the shell.

%% to have a recursive function call itself you need to pass it
%% otherwise it wont be seen

-module(counting).
%% CAREFUL NOT TO CALL THIS AS "exportS" WITH A FUCKING FINAL "S"
%% lost 1hr to that error
-export([
         start/0,
         spawn_dummy1/1,
         spawn_dummy2/1,
         spawn_dummy3/1,
         dummy/3,
         update/0
        ]).


start() -> spawn(counting, update, []).

spawn_dummy1(Tot) -> spawn(counting, dummy, [Tot, dummy1, 0]).
spawn_dummy2(Tot) -> spawn(counting, dummy, [Tot, dummy2, 0]).
spawn_dummy3(Tot) -> spawn(counting, dummy, [Tot, dummy3, 0]).

-record(total, {d1=0, d2=0, d3=0, all=0}).

update() ->
    T = fun(T, Tot) ->
                %% can I do a better pattern match?
                %% I dont know how to reference to a record when
                %% the key is a variable
                receive
                    tot ->
                        io:format("total is ~p!~n", [Tot#total.all]),
                        T(T, Tot);
                    {dummy1, Count} ->
                        T(T, Tot#total{
                               all = Tot#total.all + Tot#total.d1 + Count,
                               d1 = Tot#total.d1 + Count
                              });
                    {dummy2, Count} ->
                        T(T, Tot#total{
                               all = Tot#total.all + Tot#total.d2 + Count,
                               d2 = Tot#total.d2 + Count
                              });
                    {dummy3, Count} ->
                        T(T, Tot#total{
                               all = Tot#total.all + Tot#total.d3 + Count,
                               d3 = Tot#total.d3 + Count
                              })
                end
        end,
    T(T, #total{}).

dummy(Pid, Dn, Val) ->
    receive
        set ->
            Pid ! {Dn, Val + 1},
            io:format("~p has been increased to ~p~n", [Dn, Val + 1]),
            dummy(Pid, Dn, Val + 1);
        get ->
            io:format("~p has been called ~p times~n", [Dn, Val]),
            dummy(Pid, Dn, Val)
    end.
