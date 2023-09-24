/*

*Figure: Righello
================

Descrizione
-----------

Scrivere un programma righello.go, dotato di una funzione
ricorsiva

righello(n int)

che, data la dimensione di un righello, emetta nel flusso d'uscita
un righello della dimensione data e di una funzione main() che, data
la dimensione come argomento sulla linea di comando,
produca il righello corrispondente.

La dimensione puo` essere 0, nel qual caso il programma non emette nulla.

Per la definizione di "righello" si legga con attenzione quanto segue:

- chiamiamo "tacca" di lunghezza N una sequenza di N caratteri "-"
seguiti da "a-capo";
- chiamiamo "righello" di dimensione M > 0 una tacca lunga M
preceduta e seguita da un righello di dimensione M - 1, assumendo per
convenzione che un righello di dimensione 0 non contenga alcuna tacca
(o altro righello).


Ad esempio, un righello di dimensione 1 e`:

	-

ossia e` una tacca (preceduta e seguita da null'altro);

un righello di dimensione 2 e`:

	-
	--
	-

infatti e` dato da un righello di dimensione 1 seguito da una tacca
lunga 2 e quindi da un righello di dimensione 1;

un righello di dimensione 3 e`:

	-
	--
	-
	---
	-
	--
	-

di nuovo, infatti, osserviamo un righello di dimensione 2, seguito da una
tacca di lunghezza 3 e quindi ancora da un righello di dimensione 2.


Esempio
-------

Eseguendo

go run righello.go 4

il programma emette

	-
	--
	-
	---
	-
	--
	-
	----
	-
	--
	-
	---
	-
	--
	-

nel flusso di uscita.
*/

package main

import (
	"fmt"
)

func main() {
	righello(4)
}

func righello(n int) {
	if n == 0 {
		return
	}
	righello(n - 1)
	printMarks(n)
	righello(n - 1)
}

func printMarks(n int) {
	for i := 0; i < n; i++ {
		fmt.Print("-")
	}

}
