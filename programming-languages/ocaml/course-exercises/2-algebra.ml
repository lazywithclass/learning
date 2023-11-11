(* QUESTION when are Monoids etc useful IN PRACTICE? *)
(* QUESTION coding this with functors would be any better? How? *)


(* ------------------------------------------------------------------- *)
(* MONOID *)
(* ------------------------------------------------------------------- *)

module type Monoid = sig
  type t
  val set : t Seq.t
  val id : t
  val op : t -> t -> t
end

(* we need to say "with type t = bool" so that even for Monoid we *)
(* clarify that t is of type bool, otherwise it would just be a t *)

module Or : Monoid with type t = bool = struct
  type t = bool
  let set = [true;false] |> List.to_seq
  let id = false
  let op = (||)
end

module And : Monoid with type t = bool = struct
  type t = bool
  let set = [true;false] |> List.to_seq
  let id = true
  let op = (&&)
end

(* how to enforce that we are dealing with positive numbers? *)
module Natural : Monoid with type t = int = struct
  type t = int
  let set = Seq.unfold (fun x -> Some (x, x + 1)) 0
  let id = 0
  let op = (+)
end


(* test this using cartesian product *)

(* even though we have Seqs we property test on a finite subset *)
module Tester(M: Monoid) = struct

  (* let rec take seq n = *)
  (*   match (seq, n) with *)
  (*   | (_, 0)      -> [] *)
  (*   | Seq.Cons (x, seq) -> [] *)



  let assoc () =
    let fwd = Seq.fold_left (fun acc x -> M.op x acc) M.id (Seq.take 100 M.set) in
    let rwd = Seq.fold_left (fun acc x -> M.op acc x) M.id M.set in

    if fwd = rwd
    then print_string "ok"
    else print_string "nok"
end


module NaturalTester = Tester(Natural)

let _ = NaturalTester.assoc ()
