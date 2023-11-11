
(* let f n b = b *)

(* (\* this is ok *\) *)
(* let _ = f 0 "abc" *)
(* let _ = f 0 1.2 *)

(* (\* *)
(* f' prende una weakly polymorphic type variable e restituisce un type weak *)
(* https://v2.ocaml.org/manual/polymorphism.html#ss:weak-types *)
(* *\) *)
(* let f' = f 0 *)

(* let f'' = f 0 *)

(* let _ = f'' "abc" *)
(* let _ = f'' 1.2 *)





let f n b = b

(* this is ok *)
let _ = f 0 "abc"
let _ = f 0 1.2

(*
f' prende una weakly polymorphic type variable e restituisce un type weak
https://v2.ocaml.org/manual/polymorphism.html#ss:weak-types
*)
let f' : 'a -> 'b = f 0

let f'' : 'c -> 'd = f 0

let _ = f'' "abc"
let _ = f'' 1.2
