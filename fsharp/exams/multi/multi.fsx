module Multi

type multi<'a when 'a : comparison> = M of Map<'a,int>

let empty =
    M Map.empty

let cardEl el (M map) =
    match Map.tryFind el map with
        | None -> 0
        | Some n -> n

let add el (M map) =
    M (Map.add el ((cardEl el (M map)) + 1) map)

let singleton el =
    add el empty

let count (M map) =
    Map.fold (fun s _ v -> s + v) 0 map

let contains el (M map) =
    Map.containsKey el map

let remove el (M map) =
    match cardEl el (M map) with
        | 1 -> M (Map.remove el map)
        | n when n > 1 -> M (Map.add el (n - 1) map)
        | _ -> M map

let ofList xs =
    List.fold (fun m x -> add x m) empty xs

let toList (M map) =
    let rec expand char acc n =
        match n with
            | 0 -> acc
            | _ -> expand char (char :: acc) (n - 1)
    Map.fold (fun l k v -> (expand k [] v) @ l) List.empty map

// ---------------------
// Tests

let i = add 'a' empty |> add 'a' |> cardEl 'a'
// i = 2

let ms = ofList ['b'; 'a'; 'c'; 'b'; 'b'; 'a']

let c1 = cardEl 'b' ms
// c1 = 3

let c2 =  cardEl 'z' ms
// c2 = 0

let b1 = contains 'b' ms
// b1 = true

let b2 =  contains 'z' ms
// b2 = false

let n = count ms
// n = 6

let ms1 = add 'a' ms
let l1 = toList ms1
// l1  = ['a'; 'a'; 'a'; 'b'; 'b'; 'b'; 'c']

let n1 = cardEl 'a' ms1
// n1 = 3
let k1 =  count ms1
// k1 =  7

let ms2 =  remove 'a' ms1
let l2 = toList ms2
// l2 = ['a'; 'a'; 'b'; 'b'; 'b'; 'c']

let n2 = cardEl 'a' ms2
// n2 = 2

let k2 =  count ms2
// k2 =  6

let ms3 =  remove 'c' ms2
let l3 = toList ms3
// l3 = ['a'; 'a'; 'b'; 'b'; 'b']


let ms4 =  remove 'c' ms3
let l4 = toList ms4
// l4 = ['a'; 'a'; 'b'; 'b'; 'b']

let n4 = cardEl 'c' ms4
// n4 = 0

let k4 =  count ms4
// k4 = 5
