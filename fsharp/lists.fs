#r "nuget:FsCheck"
open FsCheck

module Lists =


    let rec rmEven xs =
        match xs with
            | [] -> []
            | x::ys when x % 2 = 0 -> rmEven ys
            | x::ys -> x :: rmEven ys

    let rec rmOddPos xs =
        match List.length xs with
            | 0 -> []
            | 1 -> xs
            | 2 -> [List.head xs]
            | _ -> List.head xs :: rmOddPos (List.tail (List.tail xs))

    let rec rmOddPosBetter xs =
        match xs with
            | [] -> []
            | [head] -> [head]
            | first :: _ :: rest -> first :: rmOddPosBetter rest

    let rec splitEvenOdd xs =
        match List.length xs with
            | 0 -> [], []
            | 1 -> xs, []
            | 2 -> [List.head xs], List.tail xs
            | _ -> let even, odd = splitEvenOdd (List.tail (List.tail xs))
                   List.head xs :: even, List.head (List.tail xs) :: odd

    let rec splitEvenOddBetter xs =
        match xs with
            | [] -> [], []
            | [head] -> [head], []
            | first :: second :: rest -> let (even, odd) = splitEvenOddBetter rest
                                         (first :: even), (second :: odd)

    let rec splitNew = function
        | [] -> [], []
        | o::[] -> [o], []
        | o::e::xs -> (o::fst (splitNew xs)), (e::snd(splitNew xs))

    let rec splitCPS xs cont =
        match xs with
            | [] -> cont ([], [])
            | o::[] -> cont ([o], [])
            | o::e::ys -> splitCPS ys (fun accOdd ->
                            splitCPS ys (fun accEven ->
                              cont (o::(fst accOdd), e::(snd accEven))))

    let s = splitCPS [1..9] id

    let rec splitCPS2 xs cont =
        match xs with
            | [] -> cont ([], [])
            | o::[] -> cont ([o], [])
            | o::e::ys -> splitCPS2 ys (fun acc ->
                                        cont ((o::(fst acc)), e::(snd acc)))

    // shouldn't use List.length
    let rec cmpLength xs ys =
        match xs, ys with
            | [], [] -> 0
            | [], ys -> -1
            | xs, [] -> 1
            | _::xs, _::ys -> cmpLength xs ys

    let rec remove r xs =
        match xs with
            | [] -> []
            | x::ys when x = r -> remove r ys
            | _ -> List.head xs :: remove r (List.tail xs)

    let rec removeDup xs =
        match xs with
            | [] -> []
            | x::ys -> x :: removeDup (remove x ys)

    let rec downto0 n =
        match n with
            | 0 -> [0]
            | _ -> n :: downto0 (n - 1)

    let rec upto n =
        match n with
            | 0 -> [0]
            | _ -> upto (n - 1) @ [n]

    let bigRed xs =
        match xs with
            | [] -> false
            | x::_ -> x = "bigred"

// supposing I cant use more than a single parameter
    let twoOrFour xs =
        match List.toArray xs with
            | [| first ; second |] -> true
            | [| first ; second ; third ; fourth |] -> true
            | _ -> false

    let firstTwoEqual xs =
        match xs with
            | [] -> false
            | [_] -> false
            | first :: second :: _ -> first = second

    let rec take n xs =
        match xs with
            | [] -> []
            | x::xs -> match n with
                           | 0 -> []
                           | _ -> x :: take (n - 1) xs

    let rec takeBetter n xs =
        match n, xs with
            | _, [] | 0, _ -> []
            | _, x::xs -> x :: takeBetter (n - 1) xs

    let rec drop n xs =
        match xs with
            | [] -> []
            | x::xs -> match n with
                           | 1 -> xs
                           | _ -> drop (n - 1) xs

    let rec dropBetter n xs =
        match n, xs with
            | _, [] | 0, _ -> xs
            | _, _::xs -> dropBetter (n - 1) xs

    exception EmptyListException

    // tail recurse
    // accumulator is the first element
    let reduce r xs =
        if List.isEmpty xs then raise EmptyListException
        let rec loop acc ys =
            match ys with
                | [] -> acc
                | y::ys -> loop (r acc y) ys
        loop (List.head xs) (List.tail xs)

    // head recurse
    // accumulator is the last element
    let reduceBack r xs =
        if List.isEmpty xs then raise EmptyListException
        let rec loop acc ys =
            match ys with
                | [] -> acc
                | y::ys -> r y (loop acc ys)
        loop (List.last xs) (List.take ((List.length xs) - 1) xs)

    let rec fold r state xs =
        match xs with
            | [] -> state
            | y::ys -> fold r (r state y) ys

    let rec foldBack r state xs =
        match xs with
            | [] -> state
            | y::ys -> r y (foldBack r state ys)

    let foldProp r state xs =
        foldBack r state xs = List.foldBack r xs state

    let reduceProp r xs =
        try
            reduce r xs = List.reduce r xs
        with EmptyListException -> true

    let prefixC p xs =
        let rec loop xs k =
            match xs with
                | [] -> None
                | y :: ys ->
                    if p y then loop ys (fun r -> k (y ::r)) else (k  [])
        loop xs (fun r -> Some r)

//             torre di hanoi
//             risoluzione ricorsiva

//             (a b c) -> 8 elementi
//             (b c) -> dato per assodato
//                     mi rimane da mettere a nel risultato dell'assunzione
//                     () -> a
//                     (b) -> etc
//                     map append head

// - write -> a function powerset : int list -> int list list

// that takes a set S represented as a list without repetitions and returns the set of all subsets of S.
// The order of subsets in the powerset and the order of elements in the subsets do not matter.

// Hint: Consider the recursive structure of this problem. Suppose you already have p, such that p = powerset s.
// How could you use p to compute powerset (x :: s)?
