#r "multi.dll"
open Multi

let i = add 'a' empty |> add 'a' |> cardEl 'a'
// i = 2

let j = singleton 'z' |> add 'z' |> cardEl 'z'
// j = 2

let ms =  ofList ['b'; 'a'; 'c'; 'b'; 'b'; 'a']
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
// l2 : char list = ['a'; 'a'; 'b'; 'b'; 'b'; 'c']
let n2 = cardEl 'a' ms2
// n2 = 2
let k2 =  count ms2
// k2 =  6

let ms3 =  remove 'c' ms2
let l3 = toList ms3
// l3 : char list = ['a'; 'a'; 'b'; 'b'; 'b']
let n3 = cardEl 'c' ms3
// n3 = 0
let k3 =  count ms3
// k3 =  5

let ms4 =  remove 'c' ms3
let l4 = toList ms4
// l4 : char list = ['a'; 'a'; 'b'; 'b'; 'b']
let n4 = cardEl 'c'  ms4
// n4 = 0
let k4 =  count ms4
// k4 =  5


(*
#r "FsCheck"
open FsCheck
*)
