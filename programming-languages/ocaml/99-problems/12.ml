(* 12. Decode a run-length encoded list. (medium) *)

type 'a rle =
  | One of 'a
  | Many of int * 'a

let decode xs =
  let rec expand = function
    | One x -> [x]
    | Many (n, x) ->
      if n = 0
      then []
      else x :: (expand (Many (n - 1, x)))
  in
  List.fold_left (fun acc rle -> acc @ (expand rle)) [] xs

let _ = decode [Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d"; Many (4, "e")]
