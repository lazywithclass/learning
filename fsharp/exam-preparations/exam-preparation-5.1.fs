// 1)

type 'a sexp = Atom of 'a | Pair of ('a sexp * 'a sexp)

// ("this" . (("is" . "a") . ("s" . "expression")))
// ((1 . 5) . 2)

// Write serialize : 'a list -> string sexp'
// that serializes a list into a term of type "string sexp"
// empty list serialized by "nil"
// cons serialized by "cons"
// each term x must be converted into a string

// let s1 =  serialize [1;2]
// Pair
//    (Atom "cons",
//      Pair (Atom "1", Pair (Atom "cons", Pair (Atom "2", Atom "nil"))))


// let rec serialize list =
//     "Pair" + List.map serializeElement list
// and serializeElement el =
//     match el with
//         | x::xs -> "Atom \"cons\"" + (x + "") serialize xs

// 2)

// unser : string sexp -> string list
// let u1 = unser s1
// val u1 : string list = ["1"; "2"]
let unser list =
    List.map (fun x -> x + "") list

// unser [1;2]

// 3)

// 4)

// sfoldB : ('a -> 'a -> 'a) -> ('b -> 'a) -> 'b sexp -> 'a
// and use it to compute the number of atoms in a sexp
// count : 'a sexp -> int)
