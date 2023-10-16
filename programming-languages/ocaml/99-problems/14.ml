(* 14. Duplicate the elements of a list. (easy) *)

let rec duplicate = function
  | []      -> []
  | x :: xs -> x :: x :: (duplicate xs)

let _ = duplicate ["a"; "b"; "c"; "c"; "d"]

let duplicate xs =
  let rec aux acc = function
    | [] -> acc
    | x :: xs -> aux (x :: x :: acc) xs
  in List.rev (aux [] xs)

let _ = duplicate ["a"; "b"; "c"; "c"; "d"]

(* But the List.rev and the end is ugly *)

let duplicate xs =
  let rec aux_cps k = function
    | []      -> k []
    | x :: xs -> aux_cps (fun xs -> k (x :: x :: xs)) xs
  in aux_cps (fun x -> x) xs

let _ = duplicate ["a"; "b"; "c"; "c"; "d"]
