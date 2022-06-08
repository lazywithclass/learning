module ExamPreparation

// t := INT | STRING
// e := n | e + e | s | e ++ e | len e


open System
type expS =
    C of int
    | Add of (expS * expS)
    | Str of string
    | Cat of (expS * expS)
    | Len of expS

type ty = INT | STRING


let tpck exp =
    match exp with
        | C _ -> Some INT
        | Str _ -> Some STRING
        | Add (C _, C _) -> Some INT
        | Cat (Str _, Str _) -> Some STRING
        | Len (Str _) -> Some INT
        | _ -> None

let tpckNested exp =
    match exp with
        | C _ -> Some INT
        | Str _ -> Some STRING
        | Add (e1, e2) -> match (tpck e1, tpck e2) with
                            | Some INT, Some INT -> Some INT
                            | _ -> None
        | Cat (e1, e2) -> match (tpck e1, tpck e2) with
                                      | Some STRING, Some STRING -> Some STRING
                                      | _ -> None
        | Len (Str _) -> Some INT
        | _ -> None



// write a function that inserts an element x in every position of a list
// producing a list of lists as a result
// insE : x:'a -> xs:'a list -> 'a list list


// ugly solution, took me hours
let insE x xs =
    let rec loop before after =
        let porcatroia = before@(x::after)
        match after with
            | [] -> [porcatroia]
            | a::aa -> porcatroia :: (loop (a::before) aa)
    loop [] xs

// JessiBit's solution
let rec insEJ el xs =
    match xs with
        | [] -> [[el]]
        | head :: ys ->
            (el :: xs) :: (List.map (fun x -> head :: x) (insEJ el ys))

(*
execution example with 1 and [2;3]

insEJ 1 [2;3]
(1 :: [2;3]) :: (map (2::x) (insEJ 1 [3]))
insEJ 1 [3]
(1 :: [2;3]) :: (map (2::x) (1 :: [3]) :: (map (3 :: x) (insEJ 1 [])))
insEJ 1 []
(1 :: [2;3]) :: (map (2::x) (1 :: [3]) :: [[3;1]]))

(1 :: [2;3]) :: (map (2::x) [1;3] :: [[3;1]]))
(1 :: [2;3]) :: (map (2::x) [[1;3];[3;1]])
(1 :: [2;3]) :: (map (2::x) [[1;3];[3;1]])
(1 :: [2;3]) :: [[2;1;3];[2;3;1]])
[1;2;3] :: [[2;1;3];[2;3;1]]
[[1;2;3];[2;1;3];[2;3;1]]
*)

// examples
let t0 = insE 1 [] = [[ 1 ]]
let t1 = insE 1 [ 2 ] = [[ 1; 2 ]; [ 2; 1 ]]
let t2 = insE 1 [ 2; 3 ] = [[ 1; 2; 3 ]; [ 2; 1; 3 ]; [ 2; 3; 1 ]]
