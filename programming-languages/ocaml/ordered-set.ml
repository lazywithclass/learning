module type Set =
sig
  type 'a t
  val empty : 'a t
  val add : 'a t -> 'a -> 'a t
  val contains : 'a t -> 'a -> bool
end

module StringSet : Set =
struct
  type 'a t = 'a list
  let empty = []
  let add set x  = x :: set
  let contains set x = List.mem x set
end

module OrderedSetFunctor (M : Set) =
struct
  type 'a t = 'a list

  let empty = []

  let add set x =
    if List.mem x set then set
    else List.sort_uniq compare (x :: set)
  let contains set x = List.mem x set

  let to_list set = set
end

module OrderedStringSet = OrderedSetFunctor(StringSet)
let orderedStringSet = OrderedStringSet.empty

(* perche' qui da []? *)
let ordered_set = OrderedStringSet.empty

let ordered_set = OrderedStringSet.add ordered_set "A"

(* perche' qui da abstract? E' per proteggere la rappresentazione? *)
let sec_set = StringSet.empty

(* perche' da errore qua? non posso "costruire dall'esterno"?  *)
let sec_set = StringSet.add sec_set "B"

List.iter (fun x -> Printf.printf "%s\n" x) sec_set
