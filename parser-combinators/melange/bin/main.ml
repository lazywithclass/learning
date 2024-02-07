let () = print_endline "Hello, World!"


let string_to_list s = String.to_seq s |> List.of_seq


(* Ok should be able to bring forward more information *)
type 'a result =
  | Ok of 'a
  | Fail of string

type 'a parser = Parser of (char list -> 'a result)

let pchar c =
  let inner = function
    | h :: t when h == c -> Ok t
    | h :: _            -> Fail (Printf.sprintf "'%c' not found, got '%c'." c h)
    | []               -> Fail "empty input"
  in (Parser inner)

let run parser input =
  let (Parser innerFn) = parser in
  innerFn input


let andThen p1 p2 =
  let inner input =
    match run p1 input with
        | Fail msg -> Fail msg
        | Ok rest  -> match run p2 rest with
                         | Fail msg -> Fail msg
                         | Ok rest  -> Ok rest
  in (Parser inner)

let orElse p1 p2 =
  let inner input =
    match run p1 input with
        | Ok rest -> Ok rest
        | Fail _  -> match run p2 input with
                         | Fail msg -> Fail msg
                         | Ok rest  -> Ok rest
  in (Parser inner)


let choice parsers =
  List.fold_left orElse (Parser (Ok [])) parsers


(* ------------------------------ *)
(* Mapping *)
(* ------------------------------ *)

let (-^-) p1 p2 = andThen p1 p2
let (-|-) p1 p2 = orElse p1 p2

(* ------------------------------ *)
(* TESTING *)
(* ------------------------------ *)

let input = string_to_list "hello world"
let parseH = pchar 'h'
let parseE = pchar 'e'

let _ = run parseH input

let parseHThenE = parseH -^- parseE
let _ = run parseHThenE input

let parseHOrE = parseH -|- parseE
let parseEOrH = parseE -|- parseH
let _ = run parseHOrE input
