---
cssclasses:
  - cornell-note
tags:
  - programming-languages/erlang
  - category-theory
---

TODO 
- [ ] write a simple chat server with 3 commands
	- [ ] join
	- [ ] send message
	- [ ] leave
 
## Erlang Master Class

https://www.cs.kent.ac.uk/ErlangMasterClasses/

Course is hosted on Youtube and taught by:
 * Simon Thompson, Professor of Logic and Computation at the University of Kent
 * Joe Armstrong, is one of the "gang of three" Erlang inventors, and is also professor at KTH. Stockholm

In the first part they talk about interpreters. Pretty interesting, not really tied to Erlang though so I'd skip it if you were not interested.

Second part is about concurrency and its patterns (rpc, Futures.

## The language

Exercises, exam attempted solutions, and the likes could be found [here](https://github.com/lazywithclass/learning/tree/master/programming-languages/erlang).

[[Dynamically typed]] [[purely functional]] programming language.
Fault tolerant, supports [[hot code swapping]].

Being [[stateless]] it has a huge advantage when dealing with [[concurrency]] since there is no need to lock the state.

<aside>concurrent programs</aside>

 * concurrency - same computer, competition for the CPU resource
 * distributed - among different computers
 * parallelism - on the same computer, with more CPUs

[[Beam]] is the VM on top of which Erlang runs.

## The syntax

`=` is not assignment, it is always pattern matching, to see it in action try to create a variable with a value and then re-assign it. Read the error message.

```erlang
1> test. % an atom called test, notice the  
         % lowercase
test
2> T = test. % a variable called T that contains 
             % the atom test, notice the 
             % uppercase
test
```

### A recursive function

```erlang
-module(fact).
-export([fact/1]). % 1 is the arity

% different clauses are separated by ;
fact(0) -> 1;
fact(N) -> N * fact(N-1).
```

```erlang
1> $A.
65 # ASCII
```

### Reversing a list

```erlang
-module(exercise).
-export([reverse/1]).

reverse([]) -> [];
reverse(Arr) -> reverse(Arr, []).
reverse([], Acc) -> Acc;
reverse([H|T], Acc) -> reverse(T, [H|Acc]).
```

Different cases in a function declaration are separated with `;`, once you're done finish it with `.`.

<aside>lists 1-based</aside>

Careful, lists in Erlang are 1-based!

### Tuples and records

Tuples could be used to pass around structured information: `{ msgname, msgbody, extra }`; for records look into [this exercise](https://github.com/lazywithclass/learning/blob/master/programming-languages/erlang/course-exercises/counting.erl).

### Pattern matching

Besides the usual structural pattern matching on arrays (`[H|T]`) or tuples (`{a, b, _}`) you could also:

```erlang
alternate_index(Prev, Prev) -> 1;
alternate_index(Prev, _) -> Prev + 1.
```

That means if we get the same value in `Prev` then the first case will match, another example: 

```erlang
contains(X, [])     -> false;
contains(X, [X|Xs]) -> true;
contains(X, [_|Xs]) -> contains(X, Xs).
```

## Running Erlang

```shell
$ erl
# output removed
1> c(fact). % this way you compile your module
{ok,fact}
2> fact:fact(7).
5040
```

## Actors

Actor model explained in under 5 minutes:

<iframe width="560" height="315" src="https://www.youtube.com/embed/ELwEdb_pD0k?si=kRuxinyLZoH6DCbO" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

<aside>mailbox + behaviour</aside>
Erlang achieves concurrency via actors which communicate with messages, each actor has
* a message queue
* a way to send messages to other actors

Actors are not processes, they run on the [BEAM](https://en.wikipedia.org/wiki/BEAM_(Erlang_virtual_machine)), not on the OS.

```erlang
-module(processes_demo). 
-export([start/2, loop/2]). 

start(N,A) -> spawn (processes_demo, loop, [N,A]). 
loop(0,A) -> io:format("~p(~p) ~p~n", [A, self(), stops]); 
loop(N,A) -> io:format("~p(~p) ~p~n", [A, self(), N]), loop(N-1,A).
```

You could use `?MODULE` to reference to the module you're in instead of, for example, `processes_demo`.

### Receive 

```erlang
receive
  Msg -> % do something with Msg
end
```

After receiving a message you tipically want the actor to start listening again so this would be a way to do it (notice the recursion with the updated list)

```erlang
% this is started by calling loop_receiving([])

loop_receiving(Senders) ->
  receive
    {Msg, From} -> 
      io:format("received ~p from ~p~n", [Msg, From]),
      loop_receiving([State|Senders])
    %% more code
  end.
```

`loop_receiving` keeps a state of what it received in `Senders`.

Messages that haven't yet been handled by the `receive` loop are kept in the waiting line, and whenever the `receive` is ready to handle them they will be processed, for example if we have to receive messages in a certain order we could do

```erlang
loop(MsgIndex) ->
	receive
		MsgIndex -> 
			%% do something with the index
			loop(MsgIndex+1)
	end.
```

This way all messages will be properly handled without any auxiliary data structure.

<aside>receive blocks</aside>

`receive` blocks the execution, meaning that unless a message that could be handled is received nothing else is being processed by that actor; one way to not block the execution is to `spawn`, so the following would block

```erlang
loop_receiving([]),
io:format("done").
```

while this would print `"done"` right away and then enter in the `receive` loop, asynchronously

```erlang
spawn(module, loop_receiving, []),
io:format("done").
```

### Functions that work at the actor level

* [`spawn`](https://www.erlang.org/doc/reference_manual/processes.html#process-creation) -  creates actors
* [`!`](https://www.erlang.org/doc/reference_manual/processes.html#sending-signals) - sends messages
* [`spawn_link`](https://www.erlang.org/doc/man/erlang#spawn_link-4) spawns and then link [link](#link)
* pattern match - works through the message queue, messages are ordered by [sent order](https://www.erlang.org/blog/message-passing/)
* `self()` - gives the PID of the current actor
* `register(an_atom, Pid)` 
* `unregister(an_atom)` 
* `whereis(an_atom) -> Pid|undefined`
* `registered()`

<aside>why register</aside>

`register` is useful in those situations where you don't want to specify a Pid, maybe because you don't have in that portion of code, but still you want to send a message.

The following allows to extend the max number of processes, you can get the max with `erlang:system_info(process_limit)`.

```bash
$ erl +p $NUM_PROCESSES
```

### Group leader

Each output is associated to a certain group_leader, you could pass it as first argument of the `io:format` like so `global:where_is(YourRegisteredAtom)`.

## Error handling

On [receiving exit signals](https://www.erlang.org/doc/reference_manual/processes#receiving-exit-signals).

### Link

Symmetric, when `B` dies an exit signal is sent to `A`

```erlang
% inside A
link(B)
```

<aside>trap</aside>

We can create a system process if we don't want it to die when it receives the signal: `process_flag(trap_exit, true)`, it will be treated as a message.

### Monitor

Asymmetric link, one stares at the other, but that's it, just the stared one dies.

<img src="https://i.imgflip.com/86l0zp.jpg"
	 height=250/>

```erlang
% inside A
monitor(process, B)
```

### An example to tie it all together

TODO!!!!!!!!!!!!!!!!!!!!!!!!!

## IRC example

Group manager handles all discussions about a single topic

client si connette, c'e' gia' il group? se si si attacca se no lo crea



TODO section about lib_chan
how to use it, how to install it

## Useful stuff

`badmatch` usually means you are redefining a variable, you can't.

Literally put `io:format("Received ~p from ~p~n", [Msg, Pid]).` everywhere to understand what's going on in your programs, otherwise it's easy to lose yourself between all communications.

This is the code to get all permutations of a list using list comprehensions, from [the docs](https://www.erlang.org/doc/programming_examples/list_comprehensions.html):

```erlang
perms([]) -> [[]];
perms(L)  -> [[H|T] || H <- L, T <- perms(L--[H])].
```

"Why do I have to export the function used by spawn?", none other than Robert Virding [answers](https://stackoverflow.com/questions/19671081/why-we-have-to-export-the-function-used-by-spawn#comment29251924_19676612): "Sorry `spawn` is a language construct. It might be in the `erlang` module but it is part of the erlang language, just one that looks like a function call".

Calling with a `:` in the string like so `io:format("loop: looping again")` will color `"loop"` in the console, it's a small but helpful thing.

## Dyalizer

[[Dialyzer]]

## Testing

[[Testing]] Erlang could be done via [[Rebar3]]



https://duckduckgo.com/?q=how+to+deal+with+erlang+purity+when+modifying+stuff&atb=v340-1&ia=web
https://user.it.uu.se/~kostis/Papers/purity.pdf


https://www.cs.kent.ac.uk/ErlangMasterClasses/



<iframe width="560" height="315" src="https://www.youtube.com/embed/4ZIPijEqrNI?si=dR1Z25aNtvvwdXLZ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

The idea of having processes tied together is at the core of the language

<iframe width="560" height="315" src="https://www.youtube.com/embed/3MvKLOecT1I?si=bZx1UkxSh7FAiV1h" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
