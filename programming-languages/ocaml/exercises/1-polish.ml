let expr_of_string string =
  let parse_single s =
    match int_of_string_opt s with
      | Some _ -> Some s
      | None ->
          if s = "+" || s = "-" || s = "*" || s = "/" || s = "**" then
              Some s
          else
              None
  in
  let rec parse i stack =
    if i = (String.length string) then
        stack
    else
        match parse_single (String.sub string i 1) with
          | None -> parse (i + 1) stack
          | Some s -> (Stack.push s stack); parse (i + 1) stack
  in
  let stack = parse 0 (Stack.create ())
  in
  let buildExpr =

  
let s = expr_of_string "1 + 2"
let _ =

type expr =
    | Unary of op * expr
    | Binary of expr * op * expr
    | Value of int
and op = Pls | Min | Mul | Div | Pow

let rec eval = function
    | Value v             -> v
    | Unary (op, e)       -> (evalo op) (eval e) 0
    | Binary (e1, op, e2) -> (evalo op) (eval e1) (eval e2)
and evalo = function
    | Pls -> (+)
    | Min -> (-)
    | Mul -> ( * )
    | Div -> (/)
(* | Pow -> ( ** ) OVER INTEGER! NO NEED FOR FLOATS *)

let e = Binary ((Value 5), Pls, (Value 1))
let e = Unary (Pls, (Value 4))
let e = Binary (Binary ((Value 4), Mul, (Value 3)), Pls, (Value 30))

let _ = eval e

