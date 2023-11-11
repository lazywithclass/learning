(* implement fold_right *)
(* ('a -> 'b -> 'b) -> 'a list -> 'b -> 'b *)

let xs = [1;2;3;4;5;6]
let reducer x acc = x + acc


let fold_right reducer xs start =
  let rec aux acc = function
    | []     -> acc
    | x :: xs -> (aux [@tailcall]) (reducer x acc) xs
  in aux start (List.rev xs)

let _ = (fold_right reducer xs 0) = (List.fold_right reducer xs 0)

(* not tail recursive *)
let rec fold_right reducer xs start =
  match xs with
  | []     -> start
  | x :: xs -> reducer x (fold_right reducer xs start)

let _ = (fold_right reducer xs 0) = (List.fold_right reducer xs 0)

(* ------------------------ *)
(* taking exercises from F# *)
(* ------------------------ *)

let rmEven xs =
  let rec aux toggle = function
    | []                 -> []
    | x :: xs when toggle -> aux false xs
    | x :: xs             -> x :: (aux true xs)
  in aux true xs

let _ = rmEven [0;1;2;3;4;5;6;7;8]


let splitEvenOdd xs =
  let rec aux even odd = function
    | []                      -> [even, odd]
    | x :: xs when x mod 2 = 0 -> aux (x :: even) odd xs
    | x :: xs                  -> aux even (x :: odd) xs
  in aux [] [] xs

let _ = splitEvenOdd [0;1;2;3;4;5;6;7;8]


(* given a list of integers, a sequence is composed by consecutive *)
(* numbers followed by one or more zeroes. *)
(* return a list with sum of the sequences. *)
(* example given [1, 2, 3, 0, 4, 5, 0, 0, 6, 0] should yield [6, 9, 6] *)

let sumSequences ns =
  let rec aux prevWasZero acc = function
    | [] -> []
    | 0 :: ns ->
      if prevWasZero
      then aux true 0 ns
      else acc :: (aux true 0 ns)
    | n :: ns ->
      aux false (n + acc) ns
  in aux false 0 ns

let _ = sumSequences [1; 2; 3; 0; 4; 5; 0; 0; 6; 0]


(* tail rec *)
let sumSequences ns =
  let rec aux zero sum acc  = function
    | [] -> List.rev acc
    | 0 :: ns ->
      if zero
      then aux true 0 acc ns
      else aux true 0 (sum :: acc) ns
    | n :: ns ->
      aux false (n + sum) acc ns
  in aux false 0 [] ns

let _ = sumSequences [1; 2; 3; 0; 4; 5; 0; 0; 6; 0]


let rec tail = function
  | []     -> None
  | [x]    -> Some x
  | _ :: xs -> tail xs

let _ = tail [1;2;3;4]
let _ = tail []

let rec lastTwo = function
  | []         -> None
  | [x]        -> None
  | x :: y :: [] -> Some (x, y)
  | x :: xs     -> lastTwo xs

let _ = lastTwo [1;2;3;4]
let _ = lastTwo [1;2]
let _ = lastTwo []


let drop xs n =
  let rec aux m = function
    | [] -> []
    | x :: xs -> if m = n
      then aux 1 xs
      else x :: (aux (m + 1) xs)

  in aux 1 xs

let _ = drop ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 3


let slice xs s e =
  let rec aux c = function
    | [] -> []
    | x :: xs -> if c >= s && c <= e
      then x :: (aux (c + 1) xs)
      else aux (c + 1) xs
  in aux 0 xs

let _ = slice ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 2 6


let rotate xs n =
  let rec aux c acc xs =
    match xs with
    | [] -> []
    | x :: xs -> if c = n
               then xs @ List.rev (x :: acc)
               else aux (c + 1) (x :: acc) xs
  in aux 1 [] xs

let _ = rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] 3
