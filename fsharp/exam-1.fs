#r "nuget:FsCheck"
open FsCheck

exception EmptyListException

// without helping function
let rec suf xs =
    match xs with
        | [] -> raise EmptyListException
        | [y] -> [[y]]
        | y::ys -> suf (ys) @ [y::ys]

let suf2 xs =
    let rec loop acc xs =
        match xs with
            | [] -> acc
            | _::ys -> loop (xs::acc) ys
    loop [] xs

// TODO
// let sufCPS

let sufProp ls =
    try
        let result = suf ls
        let hs = List.map (fun ys -> List.head ys) result
        hs = List.rev ls
    with EmptyListException -> true

do Check.Quick sufProp

// -------------------------------------------

let rec reduce r xs =
    match xs with
        | [] -> raise EmptyListException
        | [x] -> x // TODO
        | y::ys -> r (reduce r ys) y

let reduceLoop r xs =
    let rec loop acc xs =
        match xs with
            | [] -> raise EmptyListException
            | [x] -> r x acc
            | y::ys -> loop (r y acc) ys
    loop (List.head xs) (List.tail xs)

let reduceProp r xs =
    try
        reduce r xs = (List.reduce r xs)
    with EmptyListException -> true

do Check.Quick reduceProp

// -------------------------------------------

let last xs =
    if List.isEmpty xs then raise EmptyListException
    else reduceLoop (fun y _ -> y) xs

// TODO
let lastProp xs =
    // (not (List.isEmpty xs)) ==> (List.last xs = last xs)
    if List.isEmpty xs then true else List.last xs = last xs

do Check.Quick lastProp

// -------------------------------------------

(*
t := INT | STRING
e := n | e + e | s | e ++ e | len e
*)

type expS =
    C of int
    | Add of (expS * expS)
    | Str of string
    | Cat of (expS * expS)
    | Len of expS

type ty = INT | STRING

let rec tpck exp =
    match exp with
        | C _ -> Some INT
        | Str _ -> Some STRING
        | Add (e1, e2) -> match (tpck e1, tpck e2) with
                              | Some _, Some _ -> Some INT
                              | _ -> None
        | Cat (e1, e2) -> match (tpck e1, tpck e2) with
                              | Some _, Some _ -> Some STRING
                              | _ -> None
        | Len e -> match tpck e with
                       | Some STRING -> Some INT
                       | _ -> None
tpck (C 2)
tpck (Str "ziocarodai")
tpck (Add (C 1, C 41))
tpck (Cat (Str "ziocaro", Str "dai"))
tpck (Len (Str "ziocarodai"))

// -------------------------------------------

let rec eval expS =
    match expS with
        | Str s -> Str s
        | Cat (e1, e2) -> match (eval e1, eval e2) with
                              | Str s1, Str s2 -> Str (s1 + s2)
        | Len e -> match eval e with
                       | Str s -> C (String.length s)

eval (Cat (Str "ziocaro", Str "dai"))
eval (Len (Str "ziocarodai"))

let repl expS =
    match tpck expS with
        | Some e ->
            match e with
                | INT -> printf "exp %A di tipo INT ha valore %A\n" expS (eval expS)
                | STR -> printf "exp %A di tipo STRING ha valore %A\n" expS (eval expS)
        | None -> printf "exp: %A non tipabile\n" expS
// TODO ^ controllare ricorsivamente l'expr

repl (Cat(Str"aa", Str"bb"))
repl (Cat(Str"aa", C 4))

// -------------------------------------------

type 'a binTree =
    | Leaf                                     // empty tree
    | Node of 'a * 'a binTree * 'a binTree    // Node(root, left, right)

(*
t1:
         10
     /       \
    7         8
     \      /   \
      5    4     12
            \    /
             3  18
*)

let t1 =
    Node( 10,
        Node(7, Leaf, Node(5,Leaf,Leaf)),
           Node(8,
               Node(4,Leaf,Node(3,Leaf,Leaf)),
               Node(12,Node(18,Leaf,Leaf),Leaf)))

let rec count pred tree =
    match tree with
        | Leaf -> 0
        | Node (v, l, r) -> (if pred v then 1 else 0) + (count pred l) + (count pred r)

let mult3 n = n % 3 = 0

let b1 =
    List.map (count mult3) [ Node(5,Leaf, Leaf) ; t1 ]  = [0 ; 3]

// -------------------------------------------

let first pred tree =
    let rec loop k tree
        match tree with
            | Leaf -> k None
            | Node (v, l, r) -> if pred v then k (Some v)
                                else loop (fun lacc -> loop (fun racc ->
                                  if pred v then Some v

                                  ))

let rec first2 pred tree =
    match tree with
        | Leaf -> None
        | Node (v, l, r) -> if pred v then Some v
                            else match (first2 pred l, first2 pred r) with
                                     | Some resl, _ -> Some resl
                                     | _, Some resr -> Some resr
                                     | _ -> None
