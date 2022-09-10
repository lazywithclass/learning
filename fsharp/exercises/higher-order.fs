#r "nuget:FsCheck"

open FsCheck


// implement map recursively
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

let checkFilter f xs = List.filter f xs = filter f xs

do Check.Quick checkFilter
