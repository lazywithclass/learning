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
    | Node of 'a  * 'a binTree * 'a binTree    // Node(root, left, right)

let t1 =
    Node(10,
        Node(7, Leaf, Node(5,Leaf,Leaf)),
        Node(8,
            Node(4,Leaf,Node(3,Leaf,Leaf)),
            Node(12,Node(18,Leaf,Leaf),Leaf)
        )
    )

// Implement count : pred:('a -> bool) -> tr:'a binTree -> int)
let rec count pred tree =
    match tree with
        | Leaf -> 0
        | Node (v, l, r) -> let lTot = count pred l
                            let rTot = count pred r
                            let result = if pred v then 1  else 0
                            lTot + rTot + result

// another possibile implementation
let rec count2 p tree =
    match tree with
        | Leaf -> 0
        | Node (v, l, r) -> (count2 p l) + (count2 p r) + if p v then 1 else 0

let mult3 n = n % 3 = 0

// should return true
let b1 =
    List.map (count mult3) [ Node(5,Leaf, Leaf) ; t1 ]  = [0 ; 3]


// Implement first : pred:('a -> bool) -> tr:'a binTree -> 'a option')
let rec first pred tree =
    match tree with
        | Leaf -> None
        | Node (v, l, r) -> match pred v with
                                | true -> Some v
                                | _ -> match first pred l with
                                           | Some v -> Some v
                                           | _ -> match first pred r with
                                                      | Some v -> Some v
                                                      | _ -> None

// another possible implementation
let rec first2 pred tree =
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
let rec first3 pred tree =
    match tree with
        | Leaf -> []
        | Node (v, l, r) -> let left = first3 pred l
                            let right = first3 pred r
                            if pred v then v :: left @ right
                            else left @ right

let b2 =
    List.map (first2 mult3) [ Node(5,Leaf, Leaf) ; t1 ] = [None; Some 3]

let firstCPS pred tree cont =
    let rec loop tree cont =
        match tree with
            | Leaf -> cont None
            | Node (v, l, r) -> printf "ZIOCARO\n"
                                loop l (fun lacc ->
                                  printf "MAPPORC\n"
                                  loop r (fun racc ->
                                    if pred v then cont v
                                    else if pred lacc then cont (Some lacc)
                                    else if pred racc then cont (Some racc)
                                    else cont None))
    loop tree cont

// stop at the first element that matches the pred
let findInListCPS pred xs cont =
    let rec loop xs cont =
        match xs with
            | [] -> cont None
            | y::ys -> printf "%A\n" y
                       if pred y then Some y else loop ys cont
    loop xs cont
