open Melange


let string_to_list s = String.to_seq s |> List.of_seq

let test_pchar () =
  let result = run (pchar 'h') (string_to_list "hello world")
  in match result with
         | Ok (c, _) -> Alcotest.(check char) "" 'h' c
         | Fail msg  -> failwith msg

let test_anyof () =
  (* Not sure why I have to write this, see https://discuss.ocaml.org/t/calling-an-infix-function-without-using-open-or-include/2357/3 *)
  let open Melange in
  let parseDigit = anyOf ['1'; '2'; '3'] in
  let parse3Digits = parseDigit -|- parseDigit -|- parseDigit in
  let result = run parse3Digits (string_to_list "312") in
  match result with
      | Ok (c, _) -> Alcotest.(check char) "" '3' c
      | Fail msg  -> failwith msg

let test_andthen () =
  (* Not sure why I have to write this, see https://discuss.ocaml.org/t/calling-an-infix-function-without-using-open-or-include/2357/3 *)
  let open Melange in
  let parseDigit = anyOf ['1'; '2'; '3'] in
  let parse3Digits = parseDigit -&- parseDigit -&- parseDigit in
  let flattened = pmap (fun ((c1, c2), c3) -> [c1; c2; c3]) parse3Digits in
  let result = run flattened (string_to_list "312") in
  match result with
      | Ok (cs, _) -> Alcotest.(check (list char)) "" ['3'; '1'; '2'] cs
      | Fail msg  -> failwith msg


let () =
  let open Alcotest in
  run "Melange" [
    "matchers", [
      test_case "pchar" `Quick test_pchar;
      test_case "anyOf" `Quick test_anyof;
      test_case "andThen" `Quick test_andthen
    ];
    "combinators", [

    ];
  ]


(* let parseH = pchar 'h' *)
(* let parseE = pchar 'e' *)
(* let parseHThenE = parseH -&- parseE *)
(* let _ = run parseHThenE input *)

(* let parseHOrE = parseH -|- parseE *)
(* let parseEOrH = parseE -|- parseH *)
(* let _ = run parseHOrE input *)

(* let parseLowercaseVowel = anyOf ['a'; 'e'; 'i'; 'o'; 'u'] *)
(* let _ = run parseLowercaseVowel input *)

(* let parseLetters = anyOf ['h'; 'e'; 'l'; 'l'; 'o'] *)
(* let _ = run parseLetters input *)
