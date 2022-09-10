(*
Language that has

t ::= INT | LSTINT
e ::= k | e1 + e2
*)


(*
1)

Write a type checker with the following rules
 * a constant has integer type
 * a sum has has integer type, if the two numbers are int
 * empty list has type LSTINT
 * cons(e1, e2) has type LSTINT if e1 has type INT and e2 has type LSTINT
 * (hd e) has type int if e has type LSTINT
 * (tl e) has type LSTINT if e has type LSTINT

If we can't assign a type None should be returned
*)

#r "nuget:FsCheck"
open FsCheck


type tp = INT | LSTINT

type exp =
    | K of int
    | Plus of exp * exp
    | Nil
    | Cons of exp * exp
    | Hd of exp
    | Tl of exp

let rec tpck e =
    match e with
        | K _ -> Some INT
        | Plus (e1, e2) -> match tpck e1, tpck e2 with
                               | Some INT, Some INT -> Some INT
                               | _ -> None
        | Nil -> Some LSTINT
        | Cons (e1, e2) -> match tpck e1, tpck e2 with
                               | Some INT, Some LSTINT -> Some LSTINT
                               | _ -> None
        | Hd Nil -> None
        | Hd e ->  match tpck e with
                      | Some LSTINT -> Some INT
                      | _ -> None
        | Tl Nil -> None
        | Tl e -> match tpck e with
                      | Some LSTINT -> Some LSTINT
                      | _ -> None

let test size len =
  let exps = (Gen.sample size len Arb.generate<exp>)
  List.map2 (fun x y -> printf "%A has type %A\n" x y) exps (List.map tpck exps)

(*
tpck (Cons (Plus ((K 3), (K 9)), Nil)) -> Some LSTINT
tpck (Hd (Cons (Plus ((K 3), (K 9)), Nil))) -> Some INT
tpck (Plus ((K 3), Nil)) -> None
*)


(*
2)
Rewrite test so that it outputs "<e> is not typable" if e doesn't have a type
*)

let mapper x y =
    match y with
        | None -> printf "%A is not typable\n" x
        | Some t -> printf "%A\n" t

let test2 size len =
  let exps = (Gen.sample size len Arb.generate<exp>)
  List.map2 mapper exps (List.map tpck exps)


(*
3)
Rewrite the type checker using exceptions
*)


exception PlusEx of (exp * tp)
exception ConsEx of (exp * tp)
exception NotListEx of (exp * tp)

let rec tpckWithEx e =
    match e with
        | K _ -> INT
        | Plus (e1, e2) -> match tpckWithEx e1, tpckWithEx e2 with
                               | INT, INT -> INT
                               | _ -> raise (PlusEx (e, INT))
        | Nil -> LSTINT
        | Cons (e1, e2) -> match tpckWithEx e1, tpckWithEx e2 with
                               | INT, LSTINT -> LSTINT
                               | _ -> raise (ConsEx (e, LSTINT))
        | Hd e ->  match tpckWithEx e with
                       | LSTINT when e <> Nil -> INT
                       | _ -> raise (NotListEx (e, LSTINT))
        | Tl e -> match tpckWithEx e with
                      | LSTINT when e <> Nil -> LSTINT
                      | _ -> raise (NotListEx (e, LSTINT))

let test3 exp =
    try
        printf "%A has type %A\n" exp (tpckWithEx exp)
    with
       | PlusEx(exp, tp) | ConsEx(exp, tp) | NotListEx(exp, tp) -> printf "Expected types %A, inferred types %A\n" exp tp


(*
4)
An expression is in normal formal (or value) iff v is an integer constant,
nil, or a cons of normal expressions

v ::= k | nil | cons(v1, v2)

write a function value : exp -> bool that knows which expressions are values
*)

let rec isValue e =
    match e with
        | K _ -> true
        | Nil -> true
        | Cons (K _, Nil) -> true
        | _ -> false

(*
5)
Write an interpreter such as

eval : exp -> exp
*)

let rec eval e =
    match e with
        | Nil -> Nil
        | K e -> K e
        | Hd (Cons (e, _)) -> eval e
        | Tl e -> match e with
                      | Cons (_, e) -> e
                      | _ -> eval e
        | Plus (e1, e2) -> let (K a1) = eval e1
                           let (K a2) = eval e2
                           K (a1 + a2)
        | Cons (e1, e2) -> let a1 = eval e1
                           let a2 = eval e2
                           Cons (a1, a2)

(*
6)
Write a defensive interpreter, so that it returns None for the scenarios
in which the operation doesnt make sense, for ex Hd 5
*)

let rec evalDef e =
    match e with
        | Nil -> Some Nil
        | K e -> Some (K e)
        | Hd e -> match e with
                      | Tl e -> evalDef e
                      | (Cons (e, _)) -> Some e
                      | _ -> None
        | Tl e -> match e with
                      | Tl (Tl e) -> evalDef e
                      | Cons (_, e) -> evalDef e
                      | _ -> None
        | Plus (e1, e2) -> match evalDef e1, evalDef e2 with
                               | Some (K a1), Some (K a2) -> Some (K (a1 + a2))
                               | _ -> None
        | Cons (e1, e2) -> match evalDef e1, evalDef e2 with
                               | Some (K e), Some Nil -> Some (Cons (K e, Nil))
                               | Some (K _), Some (Cons (e2, e3)) -> Some (Cons (e1, (Cons (e2, e3))))
                               | _ -> None

let propValueSoundness e =
  let genwt = Arb.filter (fun x -> tpck x <> None) Arb.from<exp>
  Prop.forAll genwt (fun e ->
                     match (evalDef e) with
                     | None -> true
                     | Some v -> isValue v)

let config = { Config.Quick with MaxTest = 500; StartSize = 10; EndSize = 100 }
do Check.One (config, propValueSoundness)


(*
7)
Use type preservation
*)

let propTypePreservation exp =
    let t = tpck exp
    t <> None ==> (lazy (t = (tpck (eval exp))))

do Check.One (config, propTypePreservation)

evalDef (Cons (K 3, (Cons (Plus (K 3, K 3), Nil))))
