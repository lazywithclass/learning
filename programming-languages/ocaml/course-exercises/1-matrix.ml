let print matrix =
  let rec print_row i =
    if i < (List.length matrix) then (
      print_endline (List.fold_left (fun acc n -> acc^"\t"^(string_of_int n)) "" (List.nth matrix i));
      print_row (i + 1)
    )
  in
  print_row 0

let zeroes size =
  let rec row i j =
    if i = j then
      []
    else
      0 :: (row (i + 1) j)
  and matrix i j =
    if i = j then
      []
    else
      (row 0 j) :: (matrix (i + 1) j)
  in
  matrix (-1) size

(* lol? Array.make_matrix 5 4 0 *)

let identity size =
  let rec row i length line =
    if i = length then
      []
    else
      (if i = line then 1 else 0) :: (row (i + 1) length line)
  and matrix i j line =
    if i = j then
      []
    else
      (row 0 j line) :: (matrix (i + 1) j (line + 1))
  in
  matrix 0 size 0

let init size =
  let rec row i j curr =
    if i = j then
      []
    else
      curr :: (row (i + 1) j (curr + 1))
  and matrix i j curr =
    if i = j then
      []
    else
      (row 0 j curr) :: (matrix (i + 1) j (curr + j))
  in
  matrix (-1) size 0

(*
i is the index representing the i-th sublist
j is the index representing the j-th element in the sublist
*)
let transpose matrix =
  let rec row i j =
    if i = (List.length matrix) || j = (List.length (List.nth matrix i)) then
      []
    else
      (List.nth (List.nth matrix i) j) :: row (i + 1) j
  and column i j =
    if j = (List.length matrix) then
      []
    else
      (row i j) :: (column i (j + 1))
  in
  column 0 0

(* transpose and init seem to share a lot of similarities, could we do something about this? *)

(* tests *)

let _ = init 5 |> print

let _ = init 5 |> transpose |> print

let _ = zeroes 5 |> print

let _ = identity 6 |> print

