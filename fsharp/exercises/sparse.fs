module Sparse

type 'a sparse = { size: int; def: 'a; els: Map<int, 'a> }

exception OutOfBound

let empty s d =
    { size = s; def = d; els = Map [] }

let dim sparse =
    sparse.size

let deflt sparse =
    sparse.def

let lookup key sparse =
    match key with
        | key when key < 0 || key >= sparse.size -> raise OutOfBound
        | key when not (Map.containsKey key sparse.els) -> deflt sparse
        | _ -> Map.find key sparse.els

let update k v s =
    match k with
        | k when k < 0 || k >= s.size -> raise OutOfBound
        | k when v = deflt s -> { s with els = Map.remove k s.els }
        | _ -> { s with els = Map.add k v s.els }

let toList s =
    Map.toList s.els

let verbosetoList s =
    List.map (fun k -> match Map.tryFind k s.els with
                           | None -> k, deflt s
                           | Some e -> k, e) [0 .. s.size - 1]
