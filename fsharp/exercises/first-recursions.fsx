module FirstRecursions

let daysOfMonth = function
    | 2 -> 28
    | 4|6|9|11 -> 30
    | _ -> 31

// define the recursive function
// make_sum_str int -> int * string
// so that make_sum_str n computes the tuple (sum, str) where
// * sum is the sum of the first n numbers (0 + 1 + 2 + ... + n)
// * str is the string "0 1 2 ... n"
//
// constraint: do it with a single recursive call
//
// example: mk_sum_str 5
// val it : int * string = (15, "0 1 2 3 4 5")

let rec make_sum_str n =
    match n with
        | 0 -> 0, "0"
        | _ -> let (m, s) = make_sum_str (n - 1)
               (m + n, s + " " + (string n))

// write a function that splits an array in half
// this is not quite what I wanted as it inverts the second list
let rec split (listA, listB) =
    let diff = List.length listA - List.length listB
    match diff with
        | 0 | 1 -> listA, listB
        |  _ -> split (List.tail listA, List.head listA :: listB)

let rec exp n e =
    match e with
        | 0 -> 1.0
        | _ -> n * (exp n (e - 1))

let rec make_str n =
    match n with
        | 0 -> "0"
        | _ -> make_str (n - 1) + " " + string n

let rec make_sum_str1 n =
    match n with
        | 0 -> 0, "0"
        | _ -> let (m, s) = make_sum_str1 (n - 1)
               (m + n, s + " + " + string n)

let rec sum_n n =
    let sum, str = make_sum_str1 n
    str + " = " + (string sum)

// declare a function sum: int * int -> int where
// sum(m, n) = m + (m+1) + (m+2) + ... (m+(n-1)) + (m+n)
let rec sumUntil (m, n) =
    match n with
        | 0 -> m
        | _ -> (m + n) + sumUntil (m, n - 1)

let sumj (m, n) =
    let rec loop m n acc =
        match (m, n) with
            | i, 0 -> acc + m
            | _ -> loop (m + 1) (n - 1) (m + acc)
    loop m n 0

// declare a continuation-based version of the factorial function
let rec fact = function
    | 0 | 1 -> 1
    | n -> n * (fact (n - 1))

let rec factCont k = function
    | 0 | 1 -> k 1
    | n -> factCont (fun m -> k (n * m)) (n - 1)
