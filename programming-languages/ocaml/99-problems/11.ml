(* 11. Modified run-length encoding. (easy) *)

type 'a rle =
  | One of 'a
  | Many of int * 'a

let encode xs =
  let create_rle count el =
      if count = 1
      then One el
      else Many (count, el)
  in
  let rec aux count acc xs =
    match xs with
    | []  -> []
    | [x] -> (create_rle (count + 1) x) :: acc
    | x :: (y :: xs as tail) ->
      if x = y
      then aux (count + 1) acc tail
      else aux 0 ((create_rle (count + 1) x) :: acc) tail
  in List.rev (aux 0 [] xs)

let _ = encode ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "e"; "e"; "e"; "e"]
