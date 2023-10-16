(* 18. Extract a slice from a list. (medium) *)

let slice xs f t =
  let rec aux i = function
    | []      -> []
    | x :: xs -> if i >= f && i <= t
                 then x :: (aux (i + 1) xs)
                 else aux (i + 1) xs
  in aux 0 xs

let _ = slice ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 2 6
