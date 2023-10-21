(* 19. Rotate a list N places to the left. (medium) *)

let rotate xs n =
  let rec aux acc n = function
    | []             -> []
    | (x :: xs) as l ->
      if n = 0
      then l @ (List.rev acc)
      else aux (x :: acc) (n - 1) xs
  in
  if n > 0
  then aux [] n xs
  else aux [] ((List.length xs) - (-n)) xs


let _ = rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] 3

let _ = rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] (-2)


(* without List.rev *)
let rotate xs n =
  let rec aux k n = function
    | []             -> k []
    | (x :: xs) as l ->
      if n = 0
      then l @ (k [])
      else aux (fun l -> k (x :: l)) (n - 1) xs
  in
  if n > 0
  then aux Fun.id n xs
  else aux Fun.id ((List.length xs) - (-n)) xs

let _ = rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] 3

let _ = rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] (-2)
