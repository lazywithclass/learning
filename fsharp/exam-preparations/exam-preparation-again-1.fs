// as soon as we find an element that does not match
// the predicate we stop accumulating elements

let rec takeWhile pred = function
    | [] -> []
    | x :: xs -> if pred x then x :: (takeWhile pred xs) else []

// code dropWhile in terms of takeWhile
let rec dropWhile pred xs =
    takeWhile (fun x -> not (pred x)) xs

// tail recursive version
let rec takeWhileTail pred acc = function
    | [] -> List.rev acc
    | x :: xs -> if pred x then takeWhileTail pred (x :: acc) xs else List.rev acc

// CPS version
let rec takeWhileCPS pred k = function
    | [] -> k []
    | x :: xs -> if pred x then takeWhileCPS pred (fun oks -> k (x :: oks)) xs
                 else k []

// given
type 'a sexp =
    | Atom of 'a
    | Pair of ('a sexp * 'a sexp)

// Write a function serialize : 'a list -> string sexp
// that serializes a list into a term of type "string sexp".
// Attention:
// - the empty list is serialized by the string  "nil"
// - cons is serialized by the string "cons"
// - each term x of the list must be converted into a string.
//
// Example
// let s1 =  serialize [1;2]
// Pair
//    (Atom "cons",
//      Pair (Atom "1", Pair (Atom "cons", Pair (Atom "2", Atom "nil"))))

let rec serialize = function
    | [] -> Atom "nil"
    | x::xs-> Pair(Atom "cons", Pair(Atom (sprintf "%A" x), (serialize xs)))

// Write the inverse function unser : string sexp -> string list

let rec unserialize = function
   | Atom "nil" -> []
   | Pair (Atom n, ps) -> if n = "cons" then unserialize ps
                          else n :: (unserialize ps)

// Write a foldBack-like function
// sfoldB : ('a -> 'a -> 'a) -> ('b -> 'a) -> 'b sexp -> 'a
// and use it to compute the number of atoms in a sexp
// count : 'a sexp -> int
