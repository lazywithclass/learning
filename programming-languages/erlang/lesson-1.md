# Lesson 1

dynamically typed purely functional programming language

concorrente

grosso vntaggio della programmazione: stateless

BEAM e' una VM sul sistema operativo

processi comunicano tra loro scambiandosi messaggi
In Erlang i thread sono implementati nel linguggio non nel sistema operativo, utilizza l'actor model per la concorrenza


 * concorrenza: stanno sulla stessa macchina, sono in competizione per la risorsa CPU 
 * distribuzione: tra diverse macchine
 * parallelismo: sulla stessa macchina c'e' piu' di una CPU
 
Erlang e' orientato alla concorrenza, scambio di messaggi asincrono
Memoria non condivisa

Gli attori hanno una coda di messaggi da evadere e ognuno di loro ha una sua coda, comunicano tra loro con messaggi

Supporta la distribuzione, fault tolerance, e hot swapping


Modulo `fact` (/1 vuol dire che accetta un argomento)

```
-module(fact).
-export([fact/1]).

fact(0) -> 1;
fact(N) -> N*fact(N-1).
```


```
$ erl
1> c(fact). 

CONTINUA DA SLIDE
```


Atomi sono etichette, servono a etichettare "cose", iniziano con lettere minuscole, senza spazi
possono iniziare con una maiuscola ma allora devono essere quotati con `'`

Tuple con le graffe, possono essere matchate con un patter matching strutturale

Liste con le quadre

Le guardie nei `when` possono essere solo di tipi ben specifici

Il punto `.` alla fine di una funzione delimita la sua definizione, utile perche' cosi si da una fine
all'elenco dei casi
