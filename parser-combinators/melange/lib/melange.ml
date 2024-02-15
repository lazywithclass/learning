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


(* TODO the plan was to use this in the choice combinator, but does not work *)
(* let alwaysOk _ = *)
(*   let inner = function *)
(*     | xs -> Ok (xs, xs) *)
(*   in (Parser inner) *)

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

(* ------------------------------ *)
(* Mapping *)
(* ------------------------------ *)

module Melange =
  struct
    let pchar c = pchar c
    let run parser input = run parser input

    let (-&-) p1 p2 = andThen p1 p2
    let (-|-) p1 p2 = orElse p1 p2
    let (-?-) cs = anyOf cs
    let pmap mapper p = pmap mapper p
  end
