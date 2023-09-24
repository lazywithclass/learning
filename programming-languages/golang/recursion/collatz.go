/*
 Scrivere una funzione ricorsiva f(n) che restituisce la successione di Collatz
generata a partire da n>0, cioè la sequenza di numeri generati secondo la funzione f:
f(1) =   1
f(n) =   n/2         se n è pari
n*3+1     se n è dispari

Versione 1 (collatzRicorsivoV1.go): la funzione ricorsiva chiama ricorsivamente
se stessa a partire da n (n>0)  fino a raggiungere il caso base 
*/

package main

import "fmt"

func main() {
	fmt.Print("Inserire un numero: ")
	var number int
	fmt.Scan(&number)
	fmt.Println(collatz(number))
}

func collatz(n int) []int {
	if n == 1 {
		return []int{n}
	}
	if n % 2 == 0 {
		return append([]int{n}, collatz(n / 2)...)
	}
	return append([]int{n}, collatz((n * 3) + 1)...)
}
