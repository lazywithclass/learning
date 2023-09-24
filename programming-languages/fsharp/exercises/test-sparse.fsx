#r "sparse"
open Sparse

let w0 = empty 1000  0.0 

let  w1 =  update 1  21.3 w0
let  v = update 3  42.2 w1

let l = toList v  // [(1, 21.3); (3, 42.2)]

let v1 =  update 10  100.0  v 
let l1 = toList v1  // [(1, 21.3); (3, 42.2); (10, 100.0)]

let v2 =  update  3  0.0 v1
let l2 = toList v2  // [(1, 21.3); (10, 100.0)]

let a0 =  lookup 1 v2 // 21.3
let a1 =  lookup 3 v2 // 0.0
let a2 =  lookup 10 v2 // 100.0
let a3 =  lookup 300 v2 // 0.0
let _ =
    try
        lookup -3 v2 |> ignore
    with
        _ -> printfn "negativo"


let _ =
    try
        lookup 10000 v2 |> ignore
    with
        _ -> printfn "outofbound"        
