(* 19. Rotate a list N places to the left. (medium) *)

let rotate xs n =
  let rec aux acc n = function
    | []             -> []
    | (x :: xs) as l ->
      if n = 0
      then l @ (List.rev acc)
      else aux (x :: acc) (n - 1) xs
  in aux [] n xs


let _ = rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] 3

let _ = rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] (-2)
