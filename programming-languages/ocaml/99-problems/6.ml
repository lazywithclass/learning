(* 6. Find out whether a list is a palindrome. (easy) *)

let rev xs =
  let rec loop reversed = function
    | [] -> reversed
    | x :: xs -> loop (x :: reversed) xs
  in loop [] xs

let is_palindrome xs = xs = (rev xs)

let _ = is_palindrome ["x"; "a"; "m"; "a"; "x"]
let _ = is_palindrome ["a"; "b"]
