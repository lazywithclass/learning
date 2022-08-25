// M = { a , b , a , c , c ,  d , a , b }

// M contains
// 3 a occurrencies
// 2 b occurrencies
// 2 c occurrencies
// 1 d occurrencies
// for a total of 8 elements
// order does not matter

type multi<'a when 'a : comparison> = M of Map<'a,int>

let empty = Map.empty

let singleton a =
    Map.add a, 1

let cardEl x m =
    match Map.tryFind x m with
        | None -> 0
        | Some n -> n

let count m =
    Map.fold (fun sum k -> sum + (cardEl k m)) 0 m

let contains x =
    Map.containsKey x

let remove x m =
    match Map.tryFind x m with
        | None -> m
        | Some n -> Map.add x (n - 1)

let ofList l =
    List.fold (fun m v -> Map.add v (1 + (cardEl v m))) Map.empty l

let toList m =
    Map.keys m
