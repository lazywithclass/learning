---
cssclasses:
  - cornell-note
tags:
  - programming-languages/erlang
  - concurrency
  - parallelism
  - purely-functional
---

- [[#Tutorials|Tutorials]]
	- [[#Tutorials#Erlang Master Class|Erlang Master Class]]
	- [[#Tutorials#Erlang Express Course|Erlang Express Course]]
- [[#The language|The language]]
- [[#The syntax|The syntax]]
	- [[#The syntax#Punctuation|Punctuation]]
	- [[#The syntax#Rounding|Rounding]]
	- [[#The syntax#A recursive function|A recursive function]]
	- [[#The syntax#Reversing a list|Reversing a list]]
	- [[#The syntax#Tuples and records|Tuples and records]]
	- [[#The syntax#Pattern matching|Pattern matching]]
	- [[#The syntax#Libraries|Libraries]]
- [[#Running Erlang|Running Erlang]]
- [[#Shell commands|Shell commands]]
- [[#Actors|Actors]]
	- [[#Actors#Receive|Receive]]
	- [[#Actors#Functions that work at the actor level|Functions that work at the actor level]]
	- [[#Actors#Group leader|Group leader]]
	- [[#Actors#Nodes|Nodes]]
- [[#Error handling|Error handling]]
	- [[#Error handling#Link|Link]]
	- [[#Error handling#Monitor|Monitor]]
- [[#Useful stuff|Useful stuff]]
- [[#Dyalizer|Dyalizer]]
- [[#Testing|Testing]]

## Tutorials

### Erlang Master Class

https://www.cs.kent.ac.uk/ErlangMasterClasses/

Course is hosted on Youtube and taught by:
 * Simon Thompson, Professor of Logic and Computation at the University of Kent
 * Joe Armstrong, is one of the "gang of three" Erlang inventors, and is also professor at KTH. Stockholm

In the first part they talk about interpreters. Pretty interesting, not really tied to Erlang though so I'd skip it if you were not interested.

Second part is about concurrency and its patterns (rpc, Futures, ...).

### Erlang Express Course

https://www.youtube.com/watch?v=aEyQcZg-Njs&list=PLoFxPv8jwGVVlajiMxaW9zG1IZgHODTuq

Much longer than the [[Erlang#Erlang Master Class]]. High quality content, taught by [Simon Thompson](https://www.kent.ac.uk/computing/people/3164/thompson-simon).

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

<aside>Mind blowing integers</aside>
Integers in Erlang have arbitrary precision.

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

Also

```erlang
1> {F,F,G} = {23,24,12}
```

Gives an error, as the same `F` can't be both 23 and 24.

### Punctuation

At first Erlang syntax looks a bit alien, especially with `,`, `;`, and `.`, use English as an analogy:
* `,` separates expressions within a clause expressions
* `;` separates parts of a sentence (clauses in Erlang)
* `.` ends a sentence (function)

### Rounding

```erlang
1> round(1.0).
1
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

You could have guards like so:

```erlang
foo(A, B) when A == B -> equal;
foo(A, B)             -> not_equal;
```

Use 
* `,` if all guards have to succeed
* `;` if one guard has to succeed

### Libraries

Common libraries

* io.erl - i/o functionality
* file.erl - filesystem
* lists.erl - standard list processing
* code.erl - load test manipulate code

## Running Erlang

```shell
$ erl
# output removed
1> c(fact). % this way you compile your module
{ok,fact}
2> fact:fact(7).
5040
```

## Shell commands

* help() - prints out a list of shell commands
* h() - history, print the last 20 commands
* b() - bindings, see all variable bindings
* f() - forget, forget all variable bindings
* f(X) - forget, forget the binding of variable X

## Actors

Actor model explained in under 5 minutes:

<iframe width="560" height="315" src="https://www.youtube.com/embed/ELwEdb_pD0k?si=kRuxinyLZoH6DCbO" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

<aside>mailbox + behaviour</aside>
Erlang achieves concurrency via actors which communicate with messages, each actor has
* a message queue
* a way to send messages to other actors

Actors are not processes, they run on the [BEAM](https://en.wikipedia.org/wiki/BEAM_(Erlang_virtual_machine)), not on the OS.

Lightweight, you can easily run 20k 30k in a VM. They don't share memory, they interact by sending messages.

```erlang
-module(processes_demo). 
-export([start/2, loop/2]). 

start(N,A) -> spawn (processes_demo, loop, [N,A]). 
loop(0,A) -> io:format("~p(~p) ~p~n", [A, self(), stops]); 
loop(N,A) -> io:format("~p(~p) ~p~n", [A, self(), N]), loop(N-1,A).
```

You could use `?MODULE` to reference to the module you're in instead of, for example, `processes_demo`.

`spawn` returns a process identifier, which is the only knowledge you get about that process; to `spawn` something you need to `export` it.

### Send

To send a message to a PID you use the `!` construct: `Pid ! {self(), something}`

Sending a message will never fail, messages sent to non existing processes are thrown away, received messages are stored in the process mailbox.

### Receive 

A pattern and a body to execute if that pattern is matched

```erlang
receive
  {something, MoreSpecific} -> % do something specific;
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
      loop_receiving([From|Senders])
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
* [`spawn_link`](https://www.erlang.org/doc/man/erlang#spawn_link-4) spawns and then [[#Link]]
* pattern match - works through the message queue, messages are ordered by [sent order](https://www.erlang.org/blog/message-passing/)
* `self()` - gives the PID of the current actor
* `register(an_atom, Pid)` 
* `unregister(an_atom)` 
* `whereis(an_atom) -> Pid|undefined`
* `global:whereis_name(an_atom) -> Pid|undefined`, remember to `net_adm:ping(Host)` (see [[#Useful stuff]])
* `registered()`

<aside>why register</aside>

`register` is useful in those situations where you don't want to specify a Pid, maybe because you don't have in that portion of code, but still you want to send a message.

The following allows to extend the max number of processes, you can get the max with `erlang:system_info(process_limit)`.

```bash
$ erl +p $NUM_PROCESSES
```

### Group leader

Each output is associated to a certain group_leader, you configure to have `io:format` output to go on a certain node by using `group_leader(whereis(user), self())`.

### Nodes

You could start a node with (note that the hostname is `hostname`)

```bash
$ erl -sname server
```

which contains this code

```erlang
-module(server).
-export([start/0, loop/0]).


start() -> Pid = spawn(fun() -> loop() end),
           register(server, Pid).

loop() ->
    receive
        Msg -> io:format("node received ~p\n", [Msg]),
               loop()
    end.
```

and then when you to send messages to that node you could do

```erlang
{server, server@hostname} ! "test".
```

where `server` is how you registered the pid of the process, this line basically means "send `"test"` to the node identified by `server` in the host called `server@hostname`"; so that tuple could be generally viewed as `{registered_name,node_name}`.

	So if you know the pid of a process, the "!" operator can be used to send it a message disregarding if the process is on the same node or on a different node.

More on this [in the docs](https://www.erlang.org/doc/getting_started/conc_prog#distributed-programming)

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

## Useful stuff

`badmatch` usually means you are redefining a variable, you can't.

Literally put `io:format("Received ~p from ~p~n", [Msg, Pid]).` everywhere to understand what's going on in your programs, otherwise it's easy to lose yourself between all communications.

This is the code to get all permutations of a list using list comprehensions, from [the docs](https://www.erlang.org/doc/programming_examples/list_comprehensions.html):

```erlang
perms([]) -> [[]];
perms(L)  -> [[H|T] || H <- L, T <- perms(L--[H])].
```

"Why do I have to export the function used by spawn?", none other than Robert Virding [answers](https://stackoverflow.com/questions/19671081/why-we-have-to-export-the-function-used-by-spawn#comment29251924_19676612): "Sorry `spawn` is a language construct. It might be in the `erlang` module but it is part of the erlang language, just one that looks like a function call".

Calling with a `:` in the string like so `io:format("loop: looping again")` will color `"loop"` in the console, it's a small but helpful thing (or at least that's happening in my Emacs setup).

Nodes are loosely coupled so to make them aware of each other you could `net_adm:ping(Host)` to create a connection, remember to expect `pong` as an answer and not `pang`, which means that the other side was not found.

## Dyalizer

[[Dialyzer]]

## Testing

[[Testing]] Erlang could be done via [[Rebar3]]


https://duckduckgo.com/?q=how+to+deal+with+erlang+purity+when+modifying+stuff&atb=v340-1&ia=web
https://user.it.uu.se/~kostis/Papers/purity.pdf

