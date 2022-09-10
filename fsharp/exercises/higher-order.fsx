#r "nuget:FsCheck"

open FsCheck


let rec map f xs =
    match xs with
        | [] -> []
        | x::xs -> f x :: map f xs

let rec filter f xs =
    match xs with
        | [] -> []
        | x::xs -> List.append (if f x then [x] else []) (filter f xs)

let rec filter2 f xs =
    match xs with
        | [] -> []
        | x::xs -> if f x then x::(filter2 f xs) else (filter2 f xs)

let rec filter3 f xs =
    match xs with
        | [] -> []
        | x::xs when f x -> x::(filter3 f xs)
        | _::xs -> filter3 f xs

// to use with quickcheck
let checkFilter f (xs: list<int>) =
    List.filter f xs = filter f xs = (filter2 f xs = filter3 f xs)


let rec forall p xs =
    match xs with
        | [] -> true
        | x::xs -> p x && forall p xs

let checkForAll p xs =
    List.forall p xs = forall p xs


// non tail recursive!
let rec foldBack f acc xs =
    match xs with
        | [] -> acc
        | x::xs -> f x (foldBack f xs acc)

let rec fold f acc xs =
    match xs with
        | [] -> acc
        | x::xs -> fold f (f x acc) xs
