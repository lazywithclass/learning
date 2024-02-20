open Melange


let string_to_list s = s |> String.to_seq |> List.of_seq

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
      | Fail msg   -> failwith msg

let test_syntax () =
  let open Melange in
  let combinator = (
   (fun ((c1, c2), c3) -> [c1; c2; c3])
    ->>-
   ((anyOf ['1';'2';'3']) -&- (anyOf ['1';'2';'3']) -&- (anyOf ['1';'2';'3']))
  ) in
  let result = run combinator (string_to_list "312") in
  match result with
      | Ok (cs, _) -> Alcotest.(check (list char)) "" ['3'; '1'; '2'] cs
      | Fail msg   -> failwith msg

let test_pstring () =
  let open Melange in
  let result = run (pstring "ABC") (string_to_list "ABCDE") in
  match result with
      | Ok (ss, rest) -> let _ = Alcotest.(check string) "" "ABC" ss in
                        Alcotest.(check (list char)) "" ['D'; 'E'] rest
      | Fail msg      -> failwith msg

let test_zeroOrMore_not_matching () =
  let open Melange in
  let parseDigit = anyOf ['1'; '2'; '3'] in
  let result = run (pZeroOrMore parseDigit) (string_to_list "456")  in
  match result with
      | Ok (cs, rest) -> let _ = Alcotest.(check (list char)) "" [] cs in
                        Alcotest.(check (list char)) "" ['4'; '5'; '6'] rest
      | Fail msg      -> failwith msg

let test_zeroOrMore_matching () =
  let open Melange in
  let parseDigit = anyOf ['1'; '2'; '3'] in
  let result = run (pZeroOrMore parseDigit) (string_to_list "111")  in
  match result with
      | Ok (cs, rest) -> let _ = Alcotest.(check (list char)) "" ['1'; '1'; '1'] cs in
                        Alcotest.(check (list char)) "" [] rest
      | Fail msg      -> failwith msg

let test_oneOrMore_not_matching () =
  let open Melange in
  let parseDigit = anyOf ['1'; '2'; '3'] in
  let result = run (pOneOrMore parseDigit) (string_to_list "456")  in
  match result with
      | Ok (_, _) -> failwith "Should never happen (TM)"
      | Fail msg  -> Alcotest.(check string) "" "'3' not found, got '4'." msg

let test_oneOrMore_matching () =
  let open Melange in
  let parseDigit = anyOf ['1'; '2'; '3'] in
  let result = run (pZeroOrMore parseDigit) (string_to_list "111")  in
  match result with
      | Ok (cs, rest) -> let _ = Alcotest.(check (list char)) "" ['1'; '1'; '1'] cs in
                        Alcotest.(check (list char)) "" [] rest
      | Fail msg      -> failwith msg


let () =
  let open Alcotest in
  run "Melange" [
    "matchers", [
      test_case "pchar" `Quick test_pchar;
      test_case "pstring" `Quick test_pstring;
      test_case "anyOf" `Quick test_anyof;
      test_case "andThen" `Quick test_andthen;
      test_case "syntax" `Quick test_syntax;
      test_case "zeroOrMore" `Quick test_zeroOrMore_not_matching;
      test_case "zeroOrMore" `Quick test_zeroOrMore_matching;
      test_case "oneOrMore" `Quick test_oneOrMore_not_matching;
      test_case "oneOrMore" `Quick test_oneOrMore_matching;
    ];
    "combinators", [

    ];
  ]
