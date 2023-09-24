module Sparse

type 'a sparse when 'a : equality

exception OutOfBound

val empty : int -> 'a -> 'a sparse
val dim : 'a sparse -> int
val deflt : 'a sparse -> 'a
val lookup : int -> 'a sparse -> 'a
val update : int -> 'a  -> 'a sparse -> 'a sparse
val toList :  'a sparse -> (int * 'a) list
val verbosetoList : 'a sparse -> (int * 'a) list
