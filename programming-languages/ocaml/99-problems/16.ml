(* 16. Drop every N'th element from a list. (medium) *)

let drop xs n =
  let rec aux m = function
    | []      -> []
    | x :: xs -> if m = 0
                 then aux n xs
                 else x :: (aux (m - 1) xs)
  in aux n xs

let _ = drop ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 3
