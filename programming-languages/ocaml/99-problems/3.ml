(* 3. Find the K'th element of a list. (easy) *)

let rec at i = function
  | [] -> None
  | x :: xs -> if i = 0
    then Some x
    else at (i - 1) xs

let _ = at 3 ["a"; "b"; "c"; "d"; "e"]
let _ = at 3 ["a"]
