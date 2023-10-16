(* 7. Flatten a nested list structure. (medium) *)
(* There is no nested list type in OCaml, so we need to define one first. A node of a nested list is either an element, or a list of nodes. *)

type 'a node =
  | One of 'a
  | Many of 'a node list

(* This produces a list that loses the original order *)
(* For example for the example below it gives  *)
(* ["d"; "a"; "c"; "b"] *)
let rec flatten xs =
  List.fold_left (fun acc x ->
      match x with
      | One o   -> o :: acc
      | Many xs -> acc @ (flatten xs)
    ) [] xs

let _ = flatten [One "a"; Many [One "b"; One "c"]; One "d"]


(* This on the other hand would just piss my FP prof *)
(* It sucks to create a list with just one element and then use append *)
(* to put that element at the end of the list *)
let rec flatten xs =
  List.fold_left (fun acc x ->
      match x with
      | One o   -> acc @ [o]
      | Many xs -> acc @ (flatten xs)
    ) [] xs

let _ = flatten [
    One "a"; Many [
      One "b"; Many [
        One "c"; One "d"; One "e"; One "f"]; One "g"]]


(* this gives the expected result but there's that List.rev at the end... *)
let flatten xs =
  let rec aux xs =
    List.fold_left (fun acc x ->
        match x with
        | One o   -> o :: acc
        | Many xs -> (aux xs) @ acc
      ) [] xs
  in aux xs |> List.rev

(* Following are two brilliant solutions, but they're not tail recursive *)

(* This is the given solution *)
let flatten list =
  let rec aux acc = function
    | [] -> acc
    | One x :: t  -> aux (x :: acc) t
    | Many l :: t -> aux (aux acc l) t in
  List.rev (aux [] list)

let _ = flatten [One "a"; Many [One "b"; Many [One "c"; One "d"]; One "e"]]


(* Or even better Jessibit's solution *)
let rec flatten = function
  | []              -> []
  | Many xs :: tail -> flatten xs @ flatten tail
  | head :: tail    -> head :: flatten tail

let _ = flatten [One "a"; Many [One "b"; Many [One "c"; Many[One "d"; One "e"]; One "f"]; One "g"]]
