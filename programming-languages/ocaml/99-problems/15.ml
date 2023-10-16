(* 15. Replicate the elements of a list a given number of times. (medium) *)

let replicate xs n =
  let rec times x n =
    match n with
    | 0 -> []
    | _ -> x :: times x (n - 1)
  in
  let rec aux = function
    | []      -> []
    | x :: xs -> (times x n) @ (aux xs)
  in
  aux xs

let _ = replicate ["a"; "b"; "c"] 3

(* or I could do the usual rev on the acc instead of appending, or using cps *)
