#load "str.cma"

(* ---------------- *)
(* TYPES *)
(* ---------------- *)

type expr =
  | Unary of op * expr
  | Binary of expr * op * expr
  | Value of int
and op = Pls | Min | Mul | Div | Pow

(* ---------------- *)
(* PARSER *)
(* ---------------- *)

(* Feels a bit weird they've coded Stack to have side effects, why is it this way? *)

let expr_of_string str =
  let tokens = Str.split (Str.regexp " ") str in
  let stack = Stack.create () in
  let parse_op = function
    | "+" -> Pls
    | "-" -> Min
    | "*" -> Mul
    | "/" -> Div
  in
  List.iter (fun token ->
      match int_of_string_opt token with
      | Some n -> Stack.push (Value n) stack
      | None ->
        let op = parse_op token in
        let e2 = Stack.pop stack in
        let e1 = Stack.pop stack in
        Stack.push (Binary (e1, op, e2)) stack
    ) tokens;
  Stack.pop stack

(* ---------------- *)
(* EVAL *)
(* ---------------- *)

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

(* ---------------- *)
(* TEST *)
(* ---------------- *)

let _ = Binary ((Value 5), Pls, (Value 1)) |> eval
let _ = Unary (Pls, (Value 4)) |> eval
let _ = Binary (Binary ((Value 4), Mul, (Value 3)), Pls, (Value 30)) |> eval

let _ = expr_of_string "3 4 + 5 *" |> eval
let _ = expr_of_string "3 4 + 5 * 30 -" |> eval
