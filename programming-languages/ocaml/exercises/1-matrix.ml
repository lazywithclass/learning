let print m =
  let rec print_rec i j =
    if i = j then
      ()
    else (
      print_endline (Array.fold_left (fun acc n -> acc^" "^(string_of_int n)) "" m.(i));
      print_rec (i + 1) j
    )
  in
  print_rec 0 (Array.length m)

let zeroes n m =
  let matrix = Array.make n [||] in
  let rec fill_rows i =
    match i with
        | -1 -> ()
        | _ ->
           matrix.(i) <- Array.make m 0;
           fill_rows (i - 1)
  in
  fill_rows (n - 1);
  matrix

(* Or better: Array.make_matrix 5 4 0 *)

let identity n =
  let matrix = zeroes n n in
  let rec fill_diagonal n m =
    match n with
        | -1 -> ()
        | _ ->
           matrix.(n).(m) <- 1;
           fill_diagonal (n - 1) (m - 1)
  in
  fill_diagonal (n - 1) (n - 1);
  matrix

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

let res = init 5

let matrix = zeroes 5 4 in print matrix

let matrix2 = identity 5 in print matrix2
