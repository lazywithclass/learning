type 'a sexp = Atom of 'a | Pair of ('a sexp * 'a sexp)

let rec serialize = function
   | [] -> Atom "nil"
   | x :: xs -> Pair (Atom "cons",
                      (Pair (Atom (sprintf "%A" x), serialize xs)))

let s1 = serialize [1;2]

let rec unser s =
    let loop acc s =
        match s with
            | Atom "nil" | Atom "cons" -> acc
            | Atom a -> a :: acc
            | Pair (p1, p2) -> let (u1, u2) = (unser p1, unser p2)
                               u1 @ u2 @ acc
    loop [] s

let original = unser s1

let rec sfoldB folder transformer sexp =
    match sexp with
        | Atom a -> transformer a
        | Pair (p1, p2) -> folder
                            (sfoldB folder transformer p1)
                            (sfoldB folder transformer p2)

let count sexp =
    sfoldB (fun a b -> a + b) (fun wrapped ->
                               match wrapped with
                                   | "cons" | "nil" -> 0
                                   | _ -> 1) sexp
count (serialize [1;2;3])

// typed part of the exercise

type 'a tsexp = Atom of char * 'a | Pair of ('a tsexp * 'a tsexp)
type ty = INT | FLOAT | STRING

let rec tyck tsexp =
    match tsexp with
        | Atom ('i', _) -> Some INT
        | Atom ('f', _) -> Some FLOAT
        | Atom ('s', _) -> Some STRING
        | Pair (p1, p2) -> let ty1 = tyck p1
                           let ty2 = tyck p2
                           match (ty1, ty2) with
                               | (Some INT, Some INT) -> Some INT
                               | (Some FLOAT, Some FLOAT) -> Some FLOAT
                               | (Some STRING, Some STRING) -> Some STRING
                               | _ -> None

// val t1 : ty option = Some STRING
let t1 = Pair(Atom('s', "is"),Atom('s', "a"))
tyck t1
// val t2 : ty option = Some INT
let t2 = Pair(Atom('i', 1),Atom('i', 2))
tyck t2
// val t3 : ty option = None
let t3 = Pair(Atom('i', "1"),Atom('s', "1"))
tyck t3
