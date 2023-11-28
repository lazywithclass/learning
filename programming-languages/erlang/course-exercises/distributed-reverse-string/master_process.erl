-module(master_process).
-export([main/0, loop/3, reverse/3, long_reverse_string/2, decompose/3]).


%% meant to be invoked from the CLI
main() -> long_reverse_string(read_lines("INPUT"), 10).
read_lines(FileName) ->
    {ok, BinaryData} = file:read_file(FileName),
    binary_to_list(BinaryData).

long_reverse_string(String, ChunkLength) ->
    Decomposed = decompose(String, 0, ChunkLength),
    Pid = spawn(fun() -> loop(1, "", length(Decomposed)) end),
    register(master, Pid),

    Indexes = lists:seq(1, length(Decomposed)),
    WithIndexes = lists:zip(Indexes, Decomposed),
    lists:foreach(
      fun({Index, El}) ->
              spawn(master_process, reverse, [El, [], Index])
      end, WithIndexes).

loop(Index, Received, Tot) ->
    receive
        {Index, Reversed} ->
            %% io:format("received ~p from ~p~n", [Reversed, Index]),
            case Index == Tot of
                true -> io:format("~p~n", [Reversed++Received]);
                false -> loop(Index+1, Reversed++Received, Tot)
            end
    end.

decompose(String, From, Length) ->
    Slice = string:slice(String, From, Length),
    case From + Length < string:length(String) of
        true -> [Slice|decompose(String, From+Length, Length)];
        false -> [Slice]
    end.

reverse(Chunk, Reversed, Index) ->
    case Chunk of
        [] ->
            %% io:format("~p is sending reversed ~p~n", [Index, Reversed]),
            master ! {Index, Reversed};
        [H|T] -> reverse(T, [H|Reversed], Index)
    end.

%% master_process:long_reverse_string("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", 5).
