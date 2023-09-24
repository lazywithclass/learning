type tm =
  | K of int
  | Nil
  | Cons of tm * tm
  | Hd of tm
  | Tl of tm

 let rec evalo = function
     | K k -> Some (K k)
     | Nil -> Some Nil
     | Cons (e1, e2) ->
         match (evalo e1, evalo e2) with
             | (Some (K k), Some (Cons (e1', e2'))) -> Some (Cons (K k, Cons (e1', e2')))
             | (Some (K k), Some Nil) -> Some (Cons (K k, Nil))
             | _ -> None
     | Hd e -> match evalo e with
                   | Some (Cons (e1, _)) -> Some e1
                   | _ -> None
     | Tl e -> match evalo e with
                   | Some (Cons (_, e2)) -> Some e2
                   | _ -> None

// evalo (Hd (Cons((K 3), Nil)));;     // Some (K 3)
// evalo (Tl (Cons(K 3,Nil)));;        // Some Nil
// evalo (Hd (Tl (Cons(K 3,Nil))));;   // None
// evalo (Cons (K 1, (Tl (Cons((K 3), Nil)))))
