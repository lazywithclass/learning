#r "nuget:FsCheck"
open FsCheck

(*
declare a function sum: int * int -> int where
sum(m, n) = m + (m+1) + (m+2) + ... (m+(n-1)) + (m+n)

1, 2
1 + 1+1 + 1+2 = 6

3, 2
3 + 3+1 + 3+2 = 12
*)

let sum (m, n) =
    let rec loop acc i =
        if i <= n then loop (acc + m + i) (i + 1)
        else acc
    loop 0 0

let sumj (m, n) =
    let rec loop m n acc =
        match (m, n) with
            | i, 0 -> acc + m
            | _ -> loop (m + 1) (n - 1) (m + acc)
    loop m n 0

// very bad, dont know how to generate two numbers > 0
let propSum m n =
    if m < 0 || n < 0 then true else sum (m, n) = sumj (m, n)

do Check.Quick propSum


// give iteration declaration of the list function List.length
let length xs =
    let rec loop size xs =
        match xs with
            | [] -> size
            | _::ys -> loop (size + 1) ys
    loop 0 xs

let propLength xs =
    List.length xs = length xs

do Check.Quick propLength


// express the function List.fold in terms of an iterative function itfold
// iterating a function of type 'a list * 'b -> 'a list * 'b

// ?
// I dont understand the text


// declare a continuation-based version of the factorial function and compare
// the run time with the results in 9.4
let fact n =
    let rec loop n k =
        if n > 1 then
            loop (n - 1) (fun acc -> k (acc * n))
        else
            k 1
    loop n id


// develop the following three versions of functions computing Fibonacci numbers
// 1) fibA: int -> int -> int -> int with two accumulating parameters n1 e n2
//    where fibAnn1n2 = Fn, when n1 = Fn-1 and n2 = Fn-2
//
// 1 1 2 3 5 8 13 ...
let rec fibA n n1 n2 =
    if n < 2 then
        fibA (n - 1) (n2) (n - n1 - n2)
    else
        n1 + n2
