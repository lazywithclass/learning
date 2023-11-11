# Lesson 5

Playing with fun

currying and partial application


let compose ~f ~g x = f (g x)

let compose' = compose ~g: (fun x -> x ** 3.)

right fold -> standard recursion


Continuations
let arg x = fun y rest -> rest (op x y)
let stop x = x
let f g = g init

let op = fun x y -> x + y
let init = 0

f (arg 1) stop
f (arg 1) (arg 2) stop
f (arg 1) (arg 2) (arg 3) stop


esercizio
scrivilo come funtore e poi guarda le slide
