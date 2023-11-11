open SequenceI

module Sequence : SequenceI =
struct
  let sumSequences ns =
    let rec aux zero sum acc  = function
      | [] -> List.rev acc
      | 0 :: ns ->
        if zero
        then aux true 0 acc ns
        else aux true 0 (sum :: acc) ns
      | n :: ns ->
        aux false (n + sum) acc ns
    in aux false 0 [] ns
end
