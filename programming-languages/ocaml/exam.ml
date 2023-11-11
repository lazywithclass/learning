let increasing_sequence xs =
  let rec aux count result = function
    | []                           -> List.rev result
    | x :: (y :: xs as l) when x < y -> aux (count + 1) result l
    | x :: xs                       -> aux 1 (count :: result) xs
  in aux 1 [] xs

let _ = increasing_sequence [1;2;3;4;5;6;7]

let _ = increasing_sequence [1;2;3;0;1;6;0]
