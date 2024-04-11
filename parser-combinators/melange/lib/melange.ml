type 'a result =
  | Ok of 'a
  | Fail of string

type 'a parser = Parser of (char list -> ('a * char list) result)

let pchar c =
  let inner = function
    | h :: t when h == c -> Ok (h, t)
    | h :: _            -> Fail (Printf.sprintf "'%c' not found, got '%c'." c h)
    | []               -> Fail "empty input"
  in (Parser inner)

let run parser input =
  let (Parser innerFn) = parser in
  innerFn input

let andThen p1 p2 =
  let inner input =
    match run p1 input with
        | Fail msg      -> Fail msg
        | Ok (c1, rest) -> match run p2 rest with
                         | Fail msg      -> Fail msg
                         | Ok (c2, rest) -> Ok ((c1, c2), rest)
  in (Parser inner)

let orElse p1 p2 =
  let inner input =
    match run p1 input with
        | Ok (c, rest) -> Ok (c, rest)
        | Fail _       -> match run p2 input with
                             | Fail msg -> Fail msg
                             | ok       -> ok
  in (Parser inner)

let choice parsers =
  List.fold_left orElse (List.nth parsers 0) (List.tl parsers)

let anyOf listOfChars =
  listOfChars |> List.map pchar |> choice

let pmap f p =
  let inner input =
    match run p input with
    | Ok (c, rest) -> let res = f c in Ok (res, rest)
    | Fail msg     -> Fail msg
  in (Parser inner)

let preturn c =
  let inner input = Ok (c, input)
  in (Parser inner)

let papply fp p =
  andThen fp p |> pmap (fun (f, x) -> f x)

let lift2 f p1 p2 =
   (papply (papply (preturn f) p1) p2)

let lift3 f p1 p2 p3 =
   (papply (papply (papply (preturn f) p1) p2) p3)

let rec seq ps =
  let cons h t = h :: t in
  let pcons = lift2 cons in
  match ps with
      | []    -> preturn []
      | h :: t -> pcons h (seq t)

let pstring str =
  let charListToStr cs = String.of_seq (List.to_seq cs) in
  str |> String.to_seq |> List.of_seq |> List.map pchar |> seq |> pmap charListToStr

let rec zeroOrMore p input =
    match run p input with
        | Fail _       -> ([], input)
        | Ok (c, rest) -> let (c', rest') = zeroOrMore p rest in
                         (c :: c', rest')

let pZeroOrMore p =
  let inner input = Ok (zeroOrMore p input)
  in (Parser inner)

let pOneOrMore p =
  let inner input =
    match run p input with
        | Fail msg -> Fail msg
        | Ok (c, rest) -> let (c', rest') = zeroOrMore p rest in
                         Ok (c :: c', rest')
  in (Parser inner)

(* TODO applying pOneOrMore to digit did not work, further investigate *)
let pint =
  let to_int c = int_of_char c - int_of_char '0' in
  let digit = anyOf ['0'; '1'; '2'; '3'; '4'; '5'; '6'; '7'; '8'; '9'] in
  digit |> pmap to_int

let opt p =
  (* TODO why do I have to write this? *)
  let some = pmap (fun a -> Some a) p in
  let none = preturn None in
  orElse none some

(* ------------------------------ *)
(* Mapping *)
(* ------------------------------ *)

module Melange =
  struct
    let pchar c = pchar c
    let pstring s = pstring s
    let pint = pint
    let pZeroOrMore p = pZeroOrMore p
    let pOneOrMore p = pOneOrMore p
    let opt p = opt p
    let run parser input = run parser input

    (* TODO use the same symbols as in the text *)
    let (|>>) p mapper = pmap mapper p
    let (-&-) p1 p2 = andThen p1 p2
    let (-|-) p1 p2 = orElse p1 p2
    let (-*-) fp p = papply fp p
  end
