(* 46 & 47. Truth tables for logical expressions (2 variables). (medium) *)

type bool_expr =
  | Var of string
  | Not of bool_expr
  | And of bool_expr * bool_expr
  | Or of bool_expr * bool_expr

let table2 a b e =

  let rec eval valA valB = function
    | Var v        -> if v = a then valA else valB
    | Not e        -> not (eval valA valB e)
    | And (e1, e2) -> (eval valA valB e1) && (eval valA valB e2)
    | Or (e1, e2)  -> (eval valA valB e1) || (eval valA valB e2)
  in
  [
    (true, true, (eval true true e));
    (true, false, (eval true false e));
    (false, true, (eval false true e));
    (false, false, (eval false false e))
  ]

let _ = table2 "a" "b" (And (Var "a", Or (Var "a", Var "b")))
