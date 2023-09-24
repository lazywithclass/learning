/*
Figure: Clessidra
=================

Scrivere un programma clessidra.go, dotato di una funzione
ricorsiva 

clessidra(n, offset int) 

che emetta nel flusso d'uscita una clessidra di base 2*n-1
e una funzione main() per testarla.

Ad esempio, una clessidra di dimensione 1 e`:
*

Una clessidra di dimensione 2 e`:
***
 *
***

cioè una clessidra di dimensione 1, con offset 1, inclusa tra due basi di lughezza ?

Una clessidra di dimensione 3 e`:
*****
 ***
  *
 ***
*****

cioè una clessidra di dimensione 2, con offset 1, inclusa tra due basi di lughezza ?
che è a sua volta ...
*/

package main

import (
	"fmt"
)

func main() {
	clessidra(1, 0)
	clessidra(3, 0)
	clessidra(9, 0)
}

func clessidra(n, offset int) {
	if n == 1 {
		printBlanks(offset)
		printStars(1)
		return
	}

	printBlanks(offset)
	printStars((2 * n) - 1)
	clessidra(n - 1, offset + 1)
	printBlanks(offset)
	printStars((2 * n) - 1)
}

func printBlanks(n int) {
	for i := 0; i < n; i++ {
		fmt.Print(" ")
	}
}

func printStars(n int) {
	for i := 0; i < n; i++ {
		fmt.Print("*")
	}
	fmt.Print("\n")
}
