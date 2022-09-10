// Reverse Polish Notation

// #load "stack.fs"
#r "testdll.dll"
open Stack

exception MalformedRPN

type operator = Add | Prod | Minus

type token =
    | Op of operator
    | C of int


let rec eval xs stack =
    match xs with
        | [] -> match (size stack) with
                    | 1 -> top stack
                    | _ -> raise MalformedRPN
        | y::ys -> match y with
                       | C i -> eval ys (push i stack)
                       | Op x ->
                           let (n1, stack1) = pop stack
                           let (n2, stack2) = pop stack1
                           match x with
                               | Add -> eval ys (push (n1 + n2) stack2)
                               | Prod -> eval ys (push (n1 * n2) stack2)
                               | Minus -> eval ys (push (n2 - n1) stack2)


let rpn1 = [ C 7; C 5; Op Minus  ]
let rpn2 = [ C 10 ; C 3 ; C 2 ; Op Prod ; Op Add  ]
let rpn3 = [ C 10 ; C 3 ; Op Add ; C 2 ; Op Prod   ]
let rpn4 = [ C 10 ; C 6 ; C 1 ; Op Minus ; Op Prod ; C 4 ; Op Minus ; C 2 ; C 5 ; Op Prod ;  Op Add  ]
eval rpn1 empty
eval rpn2 empty
eval rpn3 empty
eval rpn4 empty
