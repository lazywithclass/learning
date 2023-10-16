(*
"Programmazione Dichiarativa e' una settimana del mio corso"
-- Cazzola

functional programming

composizione di funzioni - first order citizens
ricorsione per iterare
dati sono organizzati in liste
no effetti collaterali
    la maggior parte di errori nei programmi sono assegnamenti
    la propagazione dell'errore e' "vicina" rispetto a dove si e' generata
no stato condiviso

piu' facili da dimostrare formalmente

0 imperativo (for, if, ...)


due costrutti
astrazione - dichiarazione di funzione
applicazione - invocazione

accento sul corpo di una funzione, non sul nome

costanti variabili lambda parentesi

ML discende direttamente dal lambda calcolo, non e' puro
first class functions
eager evaluated
call by value
pattern matching
parametric polymorphism
static type inference
algebraic data types
exception handling

interprete e compiler

OCaML
Object CambridgeMetaLanguage
 *)

let main() = print_string("Hello world");;
main();;


let succ = fun x -> x + 1;;
let succ x = x + 1;;

(* l'ultima espressione valutta e' quella ritornata *)

succ 2;;
(fun x -> x + 1) 2;;

(*
l'associativita' e' da sx verso dx, quindi sto passando la funzione
meno alla funzione succ, il che da errore
*)
(* succ -1;; *)

succ (- 1);;

(*
sono nomi non sono variabili, non sono locazioni di memoria
che puntano a qualcosa, servono solo a semplificarci cio' che scriviamo
*)

(*
riuso di un nome nasconde il nome precedente
static binding! una funzione prende il valore di eventuali variabili libere
quando viene definita
*)

let compose f g x = f (g x);;
let compose' (f, g) x = f (g x);; (* si passa una tupla *)

(*
pattern matching strutturale
_ e' un catch all
*)

let invert x =
  match x with
  | true -> false
  | false -> true

let invert = function
    true -> false | false -> true
