// Implement the following
// reduce :  ('a -> 'a -> 'a) ->  'a list -> 'a')

#r "nuget:FsCheck"
open FsCheck


module ExamPreparation =

    exception EmptyListEx

    (*
    reduce [1;2;3] -> 9 + 1 * 9 -> 18
    reduce [2;3] -> 3 + 2 * 3 -> 9
    reduce [3] -> 3
    *)
    let reduce reducer xs =
        let rec reduceInner reducer acc xs =
            match xs with
                | [] -> acc
                | x::xs -> reduceInner reducer (reducer acc x) xs
        reduceInner reducer (List.head xs) (List.tail xs)

    let rec reduceBack reducer xs =
        match xs with
            | [] -> raise EmptyListEx
            | [x] -> x
            | x::xs -> let result = reduceBack reducer xs
                       reducer result x

    let reduceWithFold reducer xs =
        match xs with
            | [] -> raise EmptyListEx
            | x::xs -> List.fold reducer x xs

    let equalProp pred xs =
        if List.length xs < 1 then true else List.reduce pred xs = reduce pred xs
    // do Check.Quick equalProp

    // "This is a life lesson" -- JessiBit
    // implement tryFind with List.reduce
    let tryFindWithReduce pred xs =
        if List.isEmpty xs then None
        else List.reduce
                (fun acc x -> match acc, x with
                                  | Some acc, Some x ->
                                        if pred acc then Some acc
                                        else if pred x then Some x
                                        else None
                                  | _, Some x ->
                                        if pred x then Some x
                                        else None
                                  | _ -> None)
                (List.map (fun x -> Some x) xs)

    // implement tryFind with List.fold
    let tryFindWithFold pred xs =
        List.fold (fun acc x -> if pred x then Some x else acc) None (List.rev xs)

    // TODO using Seq write all even Fibonacci numbers
