
let reverse xs =
    let rec loop reversed = function
        | [] -> reversed
        | x::xs -> loop (x :: reversed) xs
    loop [] xs

let reverseCPS xs =
    let rec loop k = function
        | [] -> k []
        | x::xs -> loop (fun acc -> k (x :: acc)) xs
    loop id xs

(*
this is why reverseCPS as written DOES NOT reverse the list!

loop id [1;2;3;4;5]
loop (acc -> id (1 :: acc)) [2;3;4;5]
loop (acc -> (acc -> id (1 :: acc)) (2 :: acc)) [3;4;5]
...
*)

let reverseCPS2 xs =
    let rec loop k = function
        | [] -> k []
        | x::xs -> loop (fun acc -> x :: (k acc)) xs
    loop id xs


// write factorial using CPS
// use an error handler for the n < 0 scenario
let rec factCPS n k handler =
    match n with
        | n when n < 0 -> handler "n cannot be less than 0"
        | 0 -> k 1
        | _ -> factCPS (n - 1) (fun x -> k (n * x)) handler

(*
1) Define a function sumTreeSmallNat : IntBTree -> int option
such that
 * sumTreeSmallNat tr = Some s if all the nodes in tr are smallInt,
 and s is the sum of the nodes and s is a smallInt
 * None otherwise

Define sumTreeSmallNat using recursion (do not use the CPS functions defined above).

2) Redefine sumTreeSmallNat by calling sumTreeSmallNatCPS with suitable continuations.

3) Use FsCheck to check that the two functions are equivalent.
*)

type IntBTree =
    | Leaf
    | Node of int * IntBTree * IntBTree

let isSmallNat x = (0 <= x) && (x <= 50)

let rec sumTreeSmallNat btree =
    match btree with
        | Leaf -> Some 0
        | Node (n, bL, bR) when (isSmallNat n) ->
            match sumTreeSmallNat bL, sumTreeSmallNat bR with
                | Some rL, Some rR -> let res = n + rL + rR
                                      if isSmallNat res then Some res
                                      else None
                | _ -> None
        | _ -> None

let rec sumTreeSmallNatCPS btree k err =
    match btree with
        | Leaf -> k (Some 0)
        | Node (n, bL, bR) when (isSmallNat n) ->
            sumTreeSmallNatCPS bL (fun rL ->
               sumTreeSmallNatCPS bR (fun rR ->
                                      match rL, rR with
                                          | Some l, Some r ->
                                                let res = n + l + r
                                                if isSmallNat res then k (Some res)
                                                else err None
                                          | _ -> err None
                                      ) err) err
        | _ -> err None

// JessiBit's approach
let sumTreeSmallNatCPSJ btree =
    let rec loop bt k err =
        match bt with
            | Leaf -> k 0
            | Node (n, bL, bR) ->
                if not (isSmallNat n) then err
                else loop bL (fun l -> loop bR (fun r ->
                                                let sum = n + l + r
                                                if isSmallNat sum then (k sum)
                                                else err) err) err
    loop btree Some None

let tr3 = Node(1,
               Node (2, Node (4,Leaf,Leaf), Leaf),
               Node (20, Node(15,Leaf,Leaf), Node(6,Leaf,Leaf)))

#r "nuget:FsCheck"
open FsCheck

let sameTreeResultProp btree =
    let noCPS = sumTreeSmallNat btree
    let withCPS = sumTreeSmallNatCPS btree id id
    let j = sumTreeSmallNatCPSJ btree
    noCPS = withCPS && withCPS = j

do Check.Quick sameTreeResultProp


// Next exercise
// Let us consider integer expressions of the kind

type expr =
  | C of int
  | Sum  of expr * expr
  | Diff of expr * expr
  | Prod of expr * expr

// and the following evaluation function

let rec eval e  =
    match e with
        | C n   -> n
        | Sum (e1, e2)   -> eval e1 + eval e2
        | Diff (e1, e2)  -> eval e1 - eval e2
        | Prod (e1, e2)  -> eval e1 * eval e2

// Define a tail recursive evaluation function evalC equivalent to eval.

let rec evalC e k =
    let evalWithOp op e1 e2 =
        evalC e1 (fun f -> evalC e2 (fun s -> k (op f s)))
    match e with
        | C n -> k n
        | Sum (e1, e2)  -> evalWithOp (+) e1 e2
        | Diff (e1, e2) -> evalWithOp (-) e1 e2
        | Prod (e1, e2) -> evalWithOp (*) e1 e2

let sameResultExpressionProp expr =
    eval expr = evalC expr id

do Check.Quick sameResultExpressionProp


// Next exercise
// Consider a function that traverses a list and returns the shortest prefix
// of the list such that a predicate p is false on the next element.
// If p holds on all elements, we return None (meaning that no such prefix
// exists).

// Let us consider the predicate isEven which checks if an integer is even

let isEven n = n % 2 = 0

// Then:
// prefix isEven [2;4;5;8] ==> Some [2; 4] // isEven 2, isEven 4, NOT (isEven 5)
// prefix isEven [2;4;8]   ==> None        // isEven 2, isEven 4, isEven 8
// prefix isEven [1;2;3;4] ==> Some []     // NOT (isEven 1)

// i) Define prefix (use recursion).
let prefix pred xs =
    let rec loop ys acc =
        match ys with
            | [] -> None
            | y::_ when not (pred y) -> Some (List.rev acc)
            | y::ys -> loop ys (y::acc)
    loop xs []

// ii) Define prefix using CPS, so to get a tail recursive function.
let rec prefixCPS pred xs k =
    match xs with
        | [] -> None
        | y::ys -> if pred y then prefixCPS pred ys (fun x -> k (y::x))
                   else k []

// iii) Compare the two functions with FsCheck.
let prefixProps pred xs =
    prefix pred xs = prefixCPS pred xs Some

do Check.Quick prefixProps

