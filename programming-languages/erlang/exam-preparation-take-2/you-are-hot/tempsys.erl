-module(tempsys).
-export([startsys/0, second_row_loop/0, first_row_loop/0]).


startsys() ->
    SecondPid = spawn(fun() -> second_row_loop() end),
    FirstPid = spawn(fun() -> first_row_loop() end),
    register(second, SecondPid),
    register(first, FirstPid).

second_row_loop() ->
    receive
        { from, From, to, To, Deg } ->
            case From of
                'C'  -> first ! { to, To, Deg };
                'K'  -> first ! { to, To, Deg - 273.15 };
                'F'  -> first ! { to, To, (Deg - 32) * 5/9 };
                'R'  -> first ! { to, To, (Deg * 5/9) - 273 };
                'De' -> first ! { to, To, 100 - (Deg * 2/3) };
                'N'  -> first ! { to, To, Deg * 100/33 };
                'Re' -> first ! { to, To, Deg * 5/4 };
                'Ro' -> first ! { to, To, (Deg - 7.5) * 40/21 }
            end,
            second_row_loop();

        Deg ->
            client ! Deg,
            second_row_loop()
    end.

%% all degrees arriving here are in Celsius
first_row_loop() ->
    receive
        { to, 'C', Deg }  -> second ! Deg, first_row_loop();
        { to, 'K', Deg }  -> second ! Deg + 273.15, first_row_loop();
        { to, 'F', Deg }  -> second ! (Deg * 9/5) + 32, first_row_loop();
        { to, 'R', Deg }  -> second ! (Deg + 273.15) * 9/5, first_row_loop();
        { to, 'De', Deg } -> second ! ((100 - Deg) * 3/2), first_row_loop();
        { to, 'N', Deg }  -> second ! Deg * 33/100, first_row_loop();
        { to, 'Re', Deg } -> second ! Deg * 4/5, first_row_loop();
        { to, 'Ro', Deg } -> second ! (Deg * 21/40) + 7.5, first_row_loop()
    end.
