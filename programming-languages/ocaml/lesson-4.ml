Polymorphism


let id x = x
polymorph as it depends on the type passed as input

let add x = x + 1
monomorph

ad hoc polymorphism typically found in languages that allow overloading
parametric polymorphism il tipo e' determinato dall'uso che se ne fa nella funzione
subtyping polymorphism

strong typing
for all type 'a this is the type 'a -> 'a

weak typing
exists only a type '_a such that this is the type '_a -> '_a

OCaml accetta argomenti di default
let rec count ?(tot=0) x = etc
e lo riutilizzo cosi ~tot:(tot+1) per dirgli che e' aumentato nel corpo
