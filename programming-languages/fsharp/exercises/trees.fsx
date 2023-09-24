(*

    10
 /       \
7         8
 \      /   \
  5    4     12
        \    /
         3  18

*)

type 'a binTree =
    | Leaf                                     // empty tree
    | Node of 'a * 'a binTree * 'a binTree     // Node(root, left, right)

let t1 =
    Node(10,
        Node(7, Leaf, Node(5,Leaf,Leaf)),
        Node(8,
            Node(4,Leaf,Node(3,Leaf,Leaf)),
            Node(12,Node(18,Leaf,Leaf),Leaf)
        )
    )

// Implement count : pred:('a -> bool) -> tr:'a binTree -> int)
let rec count pred = function
    | Leaf -> 0
    | Node (n, left, right) -> let leftCount = count pred left
                               let rightCount = count pred right
                               let current = if pred n then 1 else 0
                               current + leftCount + rightCount

// another possibile implementation
let rec count2 p = function
    | Leaf -> 0
    | Node (v, l, r) -> (count2 p l) + (count2 p r) + if p v then 1 else 0

let rec countCps pred k = function
    | Leaf -> k 0
    | Node (v, l, r) -> countCps pred (fun left ->
                          countCps pred (fun right ->
                            k (1 + left + right)) l) r

let mult3 n = n % 3 = 0

// should return true
let b1 =
    List.map (count mult3) [ Node(5,Leaf, Leaf) ; t1 ] = [0 ; 3]

// Implement first : pred:('a -> bool) -> tr:'a binTree -> 'a option')
let rec first pred = function
    | Leaf -> None
    | Node (n, left, right) -> if pred n then Some n
                               else
                               let l = first pred left
                               let r = first pred right
                               match (l, r) with
                                   | (Some _, _) -> l
                                   | (_, Some _) -> r
                                   | _ -> None

let rec first2 pred tree =
    match tree with
        | Leaf -> None
        | Node (v, l, r) -> match pred v with
                                | true -> Some v
                                | _ -> match first pred l with
                                           | Some v -> Some v
                                           | _ -> match first pred r with
                                                      | Some v -> Some v
                                                      | _ -> None

let rec first3 pred tree =
    match tree with
        | Leaf -> None
        | Node (v, l, r) -> if pred v then Some v
                            else match first2 pred l with
                                     | Some v -> Some v
                                     | _ -> match first2 pred r with
                                                | Some v -> Some v
                                                | _ -> None

// written by JessiBit
let rec firstJ pred tree =
    match tree with
        | Leaf -> None
        | Node (root, l, r) when pred root -> Some root
        | Node (_, l, r) ->
                match (firstJ pred l) with
                    | Some res -> Some res
                    | _ -> firstJ pred r

// let's try accumulating all Nodes that satisfy the predicate
let rec firstAcc pred tree =
    match tree with
        | Leaf -> []
        | Node (v, l, r) -> let left = firstAcc pred l
                            let right = firstAcc pred r
                            if pred v then v :: left @ right
                            else left @ right

let b2 =
    List.map (first2 mult3) [ Node(5,Leaf, Leaf) ; t1 ] = [None; Some 3]

// first in CPS - Continuation Passing Style
let rec firstCps pred k = function
    | Leaf -> k None
    | Node (v, l, r) -> if pred (Some v) then k (Some v)
                        else
                        firstCps pred (fun left ->
                          if pred left then k left
                          else
                            firstCps pred (fun right ->
                              if pred right then k right
                              else k None) r) l

firstCps (fun o ->
          match o with
              | None -> false
              | Some s -> s > 10) id t1;;

