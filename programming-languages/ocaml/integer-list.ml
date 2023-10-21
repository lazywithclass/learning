module type List = sig
  type 'a t
  val empty : 'a t
  val add   : 'a t -> 'a -> 'a t
end

module GenericList : List = struct
  type 'a t = 'a list
  let empty = []
  let add lst x = x :: lst
end

let _ = GenericList.add GenericList.empty 42.

(*
CANT DO THIS, are we forced to use a functor?

module IntegerList : List = struct
  type 'a t = int list
  let empty = []
  let add lst x = x :: lst
end
*)

(* Is this the only way? *)

module MakeIntModule (M : List) = struct
  type t = int
  let empty = M.empty
  let add = M.add
end

module MyModule = MakeIntModule(GenericList)
let _ = MyModule.add (MyModule.empty) 5



