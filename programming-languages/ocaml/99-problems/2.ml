(* 2. Find the last but one (last and penultimate) elements of a list. (easy) *)

let rec last_two = function
  | [] | [_] -> None
  | [x; y] -> Some (x, y)
  | _::xs -> last_two xs

let _ = last_two ["a"; "b"; "c"; "d"]
let _ = last_two ["a"]


