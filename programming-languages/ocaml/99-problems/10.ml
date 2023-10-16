(* 10. Run-length encoding of a list. (easy) *)

let encode xs =
  let rec aux count acc xs =
    print_int (List.length xs);
    match xs with
    | []                             -> []
    | [x]                            -> (count + 1, x) :: acc
    | x :: (y :: xs as l) when x = y -> aux (count + 1) acc l
    | x :: xs                        -> aux 0 ((count + 1, x) :: acc) xs
  in List.rev (aux 0 [] xs)

let _ = encode ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "e"; "e"; "e"; "e"]
