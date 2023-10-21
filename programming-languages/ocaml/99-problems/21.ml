(* 21. Insert an element at a given position into a list. (easy) *)

let rec insert_at y n = function
  | []      -> if n >= 0 then [y] else []
  | x :: xs -> if n = 0
               then y :: x :: xs
               else x :: (insert_at y (n - 1) xs)

let _ = insert_at "alfa" 1 ["a"; "b"; "c"; "d"]

let _ = insert_at "alfa" 3 ["a"; "b"; "c"; "d"]

let _ = insert_at "alfa" 4 ["a"; "b"; "c"; "d"]

(* better implementation proposed *)
let rec insert_at x n = function
    | []          -> [x]
    | h :: t as l -> if n = 0
                     then x :: l
                     else h :: insert_at x (n - 1) t
