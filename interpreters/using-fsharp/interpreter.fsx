type exp =
    | Var of string
    | Int of int
    | Add of (exp * exp)
    | Sub of (exp * exp)

let rec eval e env =
    match e with
        | Var s -> Int (Map.find s env)
        | Int int -> Int int
        | Add (e1, e2) -> match (eval e1 env, eval e2 env) with
                              | (Int i1, Int i2) -> Int (i1 + i2)
        | Sub (e1, e2) -> match (eval e1 env, eval e2 env) with
                              | (Int i1, Int i2) -> Int (i1 - i2)

let e1 = Add (Int 1, Int 41)
eval e1 Map.empty

let e2 = Add (Int 1, (Sub (Int 42, Int 1)))
eval e2 Map.empty

let e3 = Add (Var "x", Var "y")
let env = Map.add "y" 2 (Map.add "x" 1 Map.empty)
eval e3 env
