(* 17. Split a list into two parts; the length of the first part is given. (easy) *)

let split xs n =
  let rec aux acc m = function
    | []      -> (List.rev acc, [])
    | x :: xs -> if m < n
                 then aux (x :: acc) (m + 1) xs
                 else ((List.rev acc), [x::xs])
  in aux [] 0 xs


let _ = split ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 3

let _ = split ["a"; "b"; "c"; "d"] 5
