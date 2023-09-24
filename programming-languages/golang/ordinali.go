/*
Ordinali di von Neumann
=======================

Un intero di von Neumann i e` definito come segue:

per i = 0, e` l'insieme vuoto {};
per i > 0, e` l'insieme contenente gli interi di von Neumann da 0 a i-1.

0 = {}         = {}
1 = {0}        = {{}}
2 = {0, 1}     = {{}, {{}}}
3 = {0, 1, 2}  = {{}, {{}}, {{}, {{}}}}

Scrivere un programma ordinali.go dotato di una funzione ricorsiva

vonNeumann(n int) []byte

che, dato un numero naturale n, restituisca l'intero di von Neumann n,
e di una funzione main() per testarla.
*/

package main

import (
	"fmt"
)

func main() {
	fmt.Println("A")
	vonNeumann(4)
}

func vonNeumann(n int) []byte {
	var result []byte

	var inner func(int) int
	inner = func(start int) int {
		if start == n {
			return start
		}
		var a int = inner(start)
		result = append(result, byte(a))
		
		return start
	}
	inner(0)
	return result
}
