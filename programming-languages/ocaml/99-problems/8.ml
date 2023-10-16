(* 8. Eliminate consecutive duplicates of list elements. (medium) *)

let compress xs =
  let rec aux curr xs =
    match xs with
    | []                    -> []
    | x :: xs when x = curr -> aux curr xs
    | x :: xs               -> x :: (aux x xs)
  in (List.hd xs) :: (aux (List.hd xs) (List.tl xs))

let _ = compress ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "e"; "e"; "e"; "e"]

(* Better, as proposed, allows to avoid remembering the last *)
let rec compress = function
  | a :: (b :: _ as t) -> if a = b then compress t else a :: compress t
  | smaller -> smaller
