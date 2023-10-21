(* QUESTION when are Monoids etc useful IN PRACTICE? *)
(* QUESTION coding this with functors would be any better? How? *)


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

module Tester(M: Monoid) = struct
  (* for all values in the set the properties of the data *)
  (* structure should hold true *)
  let a = 0

end
