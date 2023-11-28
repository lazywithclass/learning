-module(simple).
-export([main/0]).


%% meant to be invoked from the CLI
main() -> reverse(read_lines("INPUT"), []).
read_lines(FileName) ->
    {ok, BinaryData} = file:read_file(FileName),
    binary_to_list(BinaryData).

%% same implementation as the other module
reverse(String, Reversed) ->
    case String of
        [] -> io:format("Result: ~p~n", [Reversed]);
        [H|T] -> reverse(T, [H|Reversed])
    end.
