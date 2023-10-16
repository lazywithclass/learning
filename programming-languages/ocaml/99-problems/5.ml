(* 5. Reverse a list. (easy) *)

let rev xs =
  let rec loop reversed = function
    | [] -> reversed
    | x :: xs -> loop (x :: reversed) xs
  in loop [] xs

let _ = rev ["a"; "b"; "c"]
