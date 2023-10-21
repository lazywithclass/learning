type 'a sequence = Cons of 'a * (unit -> 'a sequence)

let rec from n = Cons (n, fun () -> from (n + 1))

let nats = from 0

(* this works inside the context of a sequence *)
let rec next = function
  | Cons (n, f) ->
    let Cons (m, _) = f () in
    from m

let _ = next nats (* 1 *)
let _ = next (next (next nats)) (* 3 *)


let hd (Cons (h, _)) = h

let tl (Cons (_, t)) = t ()

let rec take n s =
  if n = 0 then [] else hd s :: take (n - 1) (tl s)

let _ = take 5 nats
