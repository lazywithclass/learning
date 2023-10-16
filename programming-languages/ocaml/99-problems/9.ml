(* 9. Pack consecutive duplicates of list elements into sublists. (medium) *)

let compress xs =
  let rec aux curr acc xs =
    print_int (List.length xs);
    match xs with
    | []                             -> []
    | [x]                            -> (x :: curr) :: acc
    | x :: (y :: xs as l) when x = y -> aux (x :: curr) acc l
    | x :: xs                        -> aux [] ((x :: curr) :: acc) xs
  in List.rev (aux [] [] xs)

let _ = compress ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "e"; "e"; "e"; "e"]
