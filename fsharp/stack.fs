module Stack

type 'a Stack = S of 'a list

exception EmptyStack

let empty = S []

let isEmpty (S s) = List.isEmpty s

let push x (S s) = S (x::s)

let pop (S s) =
    match s with
        | [] -> raise EmptyStack
        | x::s -> x, S s

// data structures are immutable so we don't care if we pop it
let top s =
    let x, _ = pop s
    x

let size (S s) = List.length s
