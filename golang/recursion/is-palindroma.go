/*
Palindromo
==========

Scrivere un programma palindromo.go, dotato di una funzione
ricorsiva

isPalindromo(s string) bool

che stabilisca se una stringa e` palindroma,

e di una funzione main() che, data una stringa come argomento sulla
linea di comando, emetta nel flusso d'uscita il messaggio
"s e' palindroma", se s
e` palindroma, e "s non e' palindroma" altrimenti.

La stringa vuota e le stringhe di lunghezza 1 sono palindrome.
*/

package main

import (
	"fmt"
)

func main() {
	fmt.Println(isPalindromo("anna"))
	fmt.Println(isPalindromo("annetta"))
	fmt.Println(isPalindromo("432234"))
}

func isPalindromo(s string) bool {
	if len(s) <= 1 {
		return true
	}

	if s[0] != s[len(s)-1] {
		return false
	}

	return isPalindromo(s[1 : len(s)-1])
}
