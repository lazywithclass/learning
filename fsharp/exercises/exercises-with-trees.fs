// define a fold on a binay tree, similar to List.fold
// use this new fold to implements exists on trees

#r "nuget:FsCheck"
open FsCheck

type 'a btree =
    | Leaf
    | Node of 'a * 'a btree * 'a btree

let tree1 = Node (1,
                  Node (2, Node (3, Leaf, Leaf), Leaf),
                  Node (4, Node (5, Leaf ,Leaf), Leaf))

let rec fold folder state btree =
    match btree with
        | Leaf -> state
        | Node (v, l, r) -> let lr = fold folder state l
                            let rr = fold folder lr r
                            folder rr v

let foldCPS folder state btree =
    let rec loop btree k =
        match btree with
            | Leaf -> k state
            | Node (v, l, r) -> loop l (fun lacc ->
                                  loop r (fun racc ->
                                    k (folder v lacc racc)))
    loop btree id

let exists pred btree =
    fold (fun found n -> found || (pred n)) false btree

let existsCPS pred btree =
    foldCPS (fun v l r -> pred v || l || r) false btree

existsCPS (fun x -> x > 0) tree1


let rec first pred tree =
    match tree with
        | Leaf -> None
        | Node (root, _, _) when pred root -> Some root
        | Node (_, l, r) ->
                match (first pred l) with
                    | Some res -> Some res
                    | _ -> first pred r

let firstCPS pred tree =
    let rec loop tree cont =
        match tree with
            | Leaf -> cont None
            | Node (root, l, r) ->
                if pred root then Some root
                else
                  loop l (fun accl -> loop r (fun accr ->
                    match accl, accr with
                        | Some acc, _ | _, Some acc ->
                            if pred acc then Some acc else cont None
                        | _ -> cont None))
    loop tree id

(*
      1
  x       1
       0    x
     x   x
*)
firstCPS (fun x -> x = 10) (Node (1, Leaf, Node (1, Node (0, Leaf, Leaf), Leaf)))

let prop_first pred (tree : btree<int>) =
    firstCPS pred tree = first pred tree

do Check.Quick prop_first


type 'a treeFromExam =
    | Node of ('a * 'a treeFromExam list)

(*
                  1
              2,3,4,5,6
      7,8  []   9    []   10,11
*)


let secondLevel = [Node (2, [Node (7, []); Node (8, [])]);
                   Node (3, []);
                   Node (4, [Node (9, [])]);
                   Node (5, []);
                   Node (6, [Node (10, []); Node (11, [])])]
let root = Node (1, secondLevel)

// let's try to implement different functions in as many ways as I can

// ----------------------------------------------------
// count
let rec count = function
    | Node (_, nodes) -> 1 + List.reduce (+) (List.map count nodes)

// without the need for two List functions
let rec count2 = function
    | Node (_, ns) -> List.fold (fun sum n -> sum + (count2 n)) 1  ns

// without fold
let rec count3 = function
    | Node (_, nodes) -> 1 + List.sum (List.map count3 nodes)

let rec countHorizontal = function
    | Node (_, nodes) -> 1 + (countDepth nodes)
and countDepth nodes =
    List.sum (List.map countHorizontal nodes)

// JessiBit's solution, much more elegant
let rec countJ (Node (_, xs)) = 1 + subTreesJ xs
and subTreesJ ys =
    match ys with
        | [] -> 0
        | first :: xs -> countJ first + subTreesJ xs

let countProp nodes =
    try
        count nodes = count2 nodes &&
            count nodes = count3 nodes &&
            count nodes = countHorizontal nodes
    with
        | :? System.ArgumentException -> true

do Check.Quick countProp

// ----------------------------------------------------
// contains
let rec contains num = function
    | Node (n, nodes) -> if n = num then true else isInChildren num nodes
and isInChildren num = function
    | [] -> false
    | n::nodes -> contains num n || isInChildren num nodes

let rec contains2 num = function
    | Node (n, []) -> num = n
    | Node (n, nodes) -> num = n || List.fold (fun found n -> found || (contains2 num n)) false nodes

let containsProp tree n =
    try
        contains tree n = contains2 tree n
    with
        | :? System.ArgumentException -> true

do Check.Quick countProp

// ----------------------------------------------------
// preorder

// could be done withou append using a loop in the and
let rec preorder = function
    | Node (num, nodes) -> num :: children nodes
and children = function
    | [] -> []
    | n::nodes -> preorder n @ children nodes

let rec preorder2 = function
    | Node (num, nodes) -> num :: List.collect (fun node -> preorder2 node) nodes

let propPreorder tree =
    try
        preorder tree = preorder2 tree
    with
        | :? System.ArgumentException -> true

do Check.Quick propPreorder
