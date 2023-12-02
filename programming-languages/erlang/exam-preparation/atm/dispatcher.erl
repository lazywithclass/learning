-module(dispatcher).
-export([start/1]).

start(Host) ->
    ok.


%% per ogni ATM deve spawnare un processo
%% allo start dell'ATM

%% per il resto deve solo fare da passacarte
