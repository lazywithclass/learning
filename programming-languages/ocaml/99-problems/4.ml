(* 4. Find the number of elements of a list. (easy) *)

let length xs =
  let rec loop acc = function
    | [] -> acc
    | x :: xs -> loop (acc + 1) xs
  in loop 0 xs

let _ = length ["a"; "b"; "c"]
let _ = length []
