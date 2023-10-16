(* 1. Write a function last : 'a list -> 'a option that returns the last element of a list. (easy) *)

let rec last = function
  | [] -> None
  | x::xs -> if List.length xs = 0
    then Some x
    else last xs

let _ = last []
let _ = last [1;2;3]

(* the proposed solution is way better than mine *)

(* the idea in this case is to remove items until *)
(* I get to one item, and that's the last *)

let rec last = function
  | [] -> None
  | [x] -> Some x
  | _ :: t -> last t

(* we could also reverse the List and then pick the first *)

let last = function
  | [] -> None
  | xs -> Some (List.hd (List.rev xs))


let _ = last [1;2;3]
