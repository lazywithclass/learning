#r "nuget:FsCheck"

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

exception EmptyListException

let rec reduce f = function
    | [] -> raise EmptyListException
    | [x] -> x
    | x :: xs -> f (reduce f xs) x

reduce (fun acc x -> x + acc) [1;2;3;4;5;6]

let rec reduceBack f = function
    | [] -> raise EmptyListException
    | [x] -> x
    | x :: xs -> reduceBack f (f x xs)

reduceBack (fun x acc -> x :: acc) [1;2;3;4;5;6]

let rec fold f acc = function
    | [] -> acc
    | x :: xs -> fold f (f acc x) xs

fold (fun acc x -> x :: acc) [] [1;2;3;4;5;6];;

let rec foldBack f xs acc =
    match xs with
        | [] -> acc
        | x :: xs -> f x (foldBack f xs acc)

foldBack (fun x acc -> x :: acc) [] [1;2;3;4;5;6];;

// implements reduce in terms of fold
let reduceWithFold f xs = function
    | [] -> raise EmptyListException
    | x::xs -> List.fold f x xs

// implement tryFind with reduce
// "This is a life lesson" -- JessiBit
let tryFindWithReduce pred = function
    | [] -> None
    | xs -> List.reduce (fun acc x -> match acc, x with
                                          | Some acc, Some x ->
                                              if pred acc then Some acc
                                              else if pred x then Some x
                                              else None
                                          | _, Some x ->
                                              if pred x then Some x
                                              else None
                                          | _ -> None) (List.map Some xs)

// notice how fold can work with different elements
let tryFindWithFold pred xs =
    List.fold (fun acc x -> if pred x then Some x else acc) None (List.tail xs)
