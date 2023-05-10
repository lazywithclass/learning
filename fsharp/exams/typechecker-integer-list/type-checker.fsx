type tp = INT | L | E | C

type tm =
  | K of int
  | Nil
  | Cons of tm * tm
  | Hd of tm
  | Tl of tm

let rec tpck = function
    | K _ -> Some INT
    | Nil -> Some E
    | Cons (e1, e2) -> match (tpck e1, tpck e2) with
                           | (Some INT, Some L) | (Some INT, Some E) | (Some INT, Some C) -> Some C
                           | _ -> None
    | Hd e -> match tpck e with
                  | Some C -> Some INT
                  | _ -> None
    | Tl e -> match tpck e with
                  | Some C -> Some L
                  | _ -> None

tpck Nil;;  // Some E
tpck (Cons(K 3, Nil));;  // Some C
tpck (Cons(Nil , Nil));;  // None
tpck (Hd (Cons(K 3, Nil)));; // Some INT
tpck (Tl (Cons(K 3, Nil)));; // Some L
tpck (Hd Nil);; // None
