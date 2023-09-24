type ty = NAT | STR | BOOL
type exp =
    | Var    of string
    | Zero
    | Succ   of exp
    | Pred   of exp
    | IsZero of exp
    | Str    of string
    | Cat    of (exp * exp)
    | Len    of exp

type value =
    | Z
    | Su of value
    | St of string

let rec eval env exp =
    match exp with
        | Var v -> Map.find v env
        | Str s -> St s
        | Cat (e1, e2) -> match (eval env e1, eval env e2) with
                              | (St s1, St s2) -> St (s1 + s2)
        | Len e -> match eval env e with
                       | St s -> let rec expand acc = function
                                   | 0 -> acc
                                   | n -> expand (Su acc) (n - 1)
                                 expand Z (String.length s)
        | Zero -> Z
        | Pred e -> match e with
                        | Zero -> Z
                        | Succ e -> eval env e
        | Succ e -> Su (eval env e)

let e1 = Succ (Pred (Succ Zero))
eval Map.empty e1
let e2 = Succ (Pred Zero)
eval Map.empty e2
let e3 = Succ (Succ (Pred Zero))
eval Map.empty e3
let e4 = Cat (Str "Hello", Cat (Str ", ", Str "there"))
eval Map.empty e4
let e5 = Len e4
eval Map.empty e5

let rec tyck tenv exp =
    match exp with
        | Var s | Str s -> match Map.tryFind exp tenv with
                               | Some STR -> Some STR
                               | _ -> None
        | Zero -> Some NAT
        | Succ e | Pred e -> match tyck tenv e with
                                 | Some NAT -> Some NAT
                                 | _ -> None
        | IsZero e -> match tyck tenv e with
                          | Some NAT -> Some BOOL
                          | _ -> None
        | Cat (e1, e2) -> match (tyck tenv e1, tyck tenv e2) with
                              | (Some STR, Some STR) -> Some STR
                              | _ -> None
        | Len e -> match tyck tenv e with
                       | Some STR -> Some NAT
                       | _ -> None

// LEZIONE 6
