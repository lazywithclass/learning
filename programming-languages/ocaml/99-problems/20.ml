(* 20. Remove the K'th element from a list. (easy) *)

let rec remove_at n = function
  | []      -> []
  | x :: xs -> if n = 0
               then xs
               else x :: (remove_at (n - 1) xs)

let _ = remove_at 1 ["a"; "b"; "c"; "d"]

(* tail recursive version *)
let remove_at n xs =
  let rec aux n acc = function
    | []      -> acc
    | x :: xs -> if n = 0
                 then acc @ xs
                 else aux (n - 1) (x :: acc) xs
  in aux n [] xs

let _ = remove_at 1 ["a"; "b"; "c"; "d"]
