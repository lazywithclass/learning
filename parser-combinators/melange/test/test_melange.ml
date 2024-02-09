open Melange
open Batteries


let string_to_list s = String.to_seq s |> List.of_seq

let test_char_match () =
  let result = run (pchar 'h') (string_to_list "hello world")
  in match result with
  | Ok (c, _) -> Alcotest.(check char) "" 'h' c
  | Fail msg  -> failwith msg

let () =
  let open Alcotest in
  run "Melange" [
    "matchers", [
      test_case "String mashing" `Quick test_char_match
    ];
    "combinators", [

    ];
  ]


(* let _ =  *)

(* let parseHThenE = parseH -^- parseE *)
(* let _ = run parseHThenE input *)

(* let parseHOrE = parseH -|- parseE *)
(* let parseEOrH = parseE -|- parseH *)
(* let _ = run parseHOrE input *)

(* let parseLowercaseVowel = anyOf ['a'; 'e'; 'i'; 'o'; 'u'] *)
(* let _ = run parseLowercaseVowel input *)

(* let parseLetters = anyOf ['h'; 'e'; 'l'; 'l'; 'o'] *)
(* let _ = run parseLetters input *)
