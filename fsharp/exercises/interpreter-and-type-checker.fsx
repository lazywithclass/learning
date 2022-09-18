// write an interpreter for a simple language like the following
// e := n | e + e | s | e ++ e | len e
type exp =
    | Int of int
    | Plus of exp * exp
    | Str of string
    | Concat of exp * exp
    | Len of exp

let rec eval exp =
    match exp with
        | Int _ -> exp
        | Str _ -> exp
        | Plus (i1, i2) -> let (Int ii1) = eval i1
                           let (Int ii2) = eval i2
                           Int (ii1 + ii2)
        | Concat (s1, s2) -> let (Str ss1) = eval s1
                             let (Str ss2) = eval s2
                             Str (ss1 + ss2)
        | Len e -> let (Str s) = eval e
                   Int (String.length s)

let e1 = Plus (Int 1, Int 2)
let e2 = Concat (Str "Hello", Str " there")
let e3 = Len e2


// write a type checker
// t := INT | STRING
type ty = INT | STR

let rec tpck exp =
   match exp with
       | Int _ -> Some INT
       | Str _ -> Some STR
       | Plus (e1, e2) -> match (tpck e1, tpck e2) with
                              | (Some INT, Some INT) -> Some INT
                              | _ -> None
       | Concat (e1, e2) -> match (tpck e1, tpck e2) with
                                | (Some STR, Some STR) -> Some STR
                                | _ -> None
       | Len e -> match tpck e with
                      | Some STR -> Some INT
                      | _ -> None
