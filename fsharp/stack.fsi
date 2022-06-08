module Stack

exception EmptyStack

type 'a sparse when 'a : equality

val empty : 'a Stack
val isEmpty : 'a Stack -> bool
val push : 'a -> 'a Stack -> 'a Stack
val pop : 'a Stack -> 'a * 'a Stack
val pop : 'a Stack -> 'a * 'a Stack
val size : 'a Stack -> int
