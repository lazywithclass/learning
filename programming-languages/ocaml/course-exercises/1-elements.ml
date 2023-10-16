type element = Element of int * string

let elements = [
  Element(4, "beryllium"); Element(12, "magnesium"); Element(20, "calcium");
  Element(38, "strontium"); Element(56, "barium"); Element(88, "radium")
]

let highestAtomicNumber elements =
  List.fold_left (fun hi el ->
      let (Element (hinum, _), Element (elnum, _)) = (hi, el) in
      if elnum > hinum then el else hi) (List.hd elements) elements


let sort elements =
  List.sort (fun a b ->
      if a > b then 1 else if a < b then -1 else 0) elements


let hi = highestAtomicNumber elements;;
let sorted = sort elements;;

let nobleGases = [
  Element(2, "helium"); Element(10, "neon"); Element(18, "argon");
  Element(36, "krypton"); Element(54, "xenon"); Element(86, "radon")
]

(* how to enforce that they're ordered in the signature? functor? *)
let rec merge els1 els2 =
  match (els1, els2) with
  | [], [] -> []
  | els1, [] -> els1
  | [], els2 -> els2
  | head1 :: tail1, head2 :: tail2 ->
    let Element (num1, name1) = head1 in
    let Element (num2, name2) = head2 in
    if num1 <= num2 then head1 :: (merge tail1 els2)
    else head2 :: (merge els1 tail2)

let res = merge elements nobleGases
