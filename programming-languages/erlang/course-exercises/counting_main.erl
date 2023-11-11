-module(counting_main).
-export([run/0]).

run() ->
    Pid = counting:start(),
    D1 = counting:spawn_dummy1(Pid),
    D2 = counting:spawn_dummy2(Pid),
    D3 = counting:spawn_dummy3(Pid),

    D1 ! get,
    D2 ! get,
    D3 ! get,

    D1 ! set,
    D2 ! set,
    D2 ! set,
    D3 ! set,
    D3 ! set,
    D3 ! set,

    [D1, D2, D3, Pid].
