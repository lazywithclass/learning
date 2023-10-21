(* 22. Create a list containing all integers within a given range. (easy) *)

let rec range a b =
   if a - b = 0
   then [a]
   else if a - b < 0
        then a :: (range (a + 1) b)
        else a :: (range (a - 1) b)

let _ = range 4 9

let _ = range 9 4
