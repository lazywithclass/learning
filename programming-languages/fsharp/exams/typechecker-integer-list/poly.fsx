// [5; 1; 10] represents 5 + x + (10 . x^2)
//
// [2; 0; 0; 5] represents 2 + (5 . x^3)
// [10] represents 10
//
// [5; 1; 10] [5; 1; 10; 0] [5; 1; 10; 0; 0] all represent 5 + x + (10 . x^2),
// so we require the last number to be different from 0



// removes the trailing 0s
// not sure why List.foldBack doesnt work as expected
let normalize cs =
    let rec folder f state = function
        | [] -> state
        | c::cs -> f c (folder f state cs)
    folder (fun c acc -> if (c = 0 && (List.isEmpty acc)) then []
                         else c :: acc) [] cs

normalize [1;2;0;0;1;0]
normalize [0;0;0;0;0;0]

type poly = P of int list

let ofList cs =
    P (normalize cs)

let toList (P cs) =
    cs

let notNullPoly (P cs) =
    not (List.isEmpty cs)

let multxc (P cs) c =
    P (List.map (fun c' -> c * c') cs)

let multxx (P cs) =
    P (List.foldBack (fun c cs' -> if c = 0 then 0 :: cs'
                                   else 0 :: c :: cs') [0] cs)

// 2 + (5. x^3) -> -2 + (-5. x^3)
let opposite (P cs) =
    P (List.map (fun c -> -c) cs)

let sum (P cs1) (P cs2) =
    P (List.map2 (fun c1 c2 -> c1 + c2) cs1 cs2)

let diff (P cs1) (P cs2) =
    P (normalize (List.map2 (fun c1 c2 -> c1 - c2) cs1 cs2))

let degree (P cs) =
    (List.length cs) - 1

// Tests

let p1 = ofList [2; 0; 0; 1]  // p1 = 2 + x^3

let p2 = ofList [9; 5; 0; -7 ; 0; 0] // p2 = 9 + 5x + (-7)x^3

let l2 = toList p2 // [9; 5; 0; -7]

let p1x4 = multxc p1 4 |> toList // [8; 0; 0; 4]

let p2x5 = multxc p2 5 |> toList // [45; 25; 0; -35]

let p1xx = multxx p1 |> toList // [0; 2; 0; 0; 1]

let p2xx = multxx p2 |> toList // [0; 9; 5; 0; -7]

let s = sum p1 p2 |> toList // [11; 5; 0; -6]

let o = sum p1 p2 |> opposite |> toList //  [-11; -5; 0; 6]

let dd = diff p1 p1|> toList // []

let g = degree p1 // 3
