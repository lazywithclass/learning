(* 26. Generate the combinations of K distinct objects chosen from the N elements of a list. (medium) *)

(* I will ALWAYS have problems with permutations :( *)


(* copied from the solution *)
let rec extract k list =
  if k <= 0 then [[]]
  else match list with
    | [] -> []
    | h :: tl ->
      let with_h = List.map (fun l -> h :: l) (extract (k - 1) tl) in
      let without_h = extract k tl in
      with_h @ without_h;
