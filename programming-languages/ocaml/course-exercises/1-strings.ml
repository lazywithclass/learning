let isPalindrome s =
  let rec check i j s =
    if i > j then true
    else
      let chari = String.get s i in
      let charj = String.get s j in
      if Char.code chari < 97 || Char.code chari > 122 then
        check (i + 1) j s
      else if Char.code charj < 97 || Char.code charj > 122 then
        check i (j - 1) s
      else
        chari = charj && check (i + 1) (j - 1) s
  in
  check 0 ((String.length s) - 1) (String.lowercase_ascii s)

let _ = isPalindrome "detartrated"
let _ = isPalindrome "Do geese see God?"
let _ = isPalindrome "Rise to vote, sir."
let _ = isPalindrome "Hello"

let (-) source replace =
  let rec search i =
    if i = String.length source then ""
    else
      match String.index_opt replace (String.get source i) with
      | None -> (Char.escaped (String.get source i)) ^ search (i + 1)
      | Some _ -> search (i + 1)
  in
  search 0

let _ = "Walter Cazzola"-"abcwxyz"

(* feeling lazy *)
let anagram possibleAnagram dict =
  let sort s =
    String.lowercase_ascii s |> String.to_seq |> List.of_seq |> List.sort Char.compare
  in
  let isAnagram s1 s2 = sort s1 = sort s2
  in
  List.exists (fun d -> isAnagram possibleAnagram d) dict

let _ = anagram "ciao" ["oiac!"; "cia"; "oia"]
let _ = anagram "ciao" ["oiac"; "cia"; "oia"]
