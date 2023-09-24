package main

import (
	"fmt"
)

func main() {
	var input int
	fmt.Scanf("%d", &input)

	for i := 1; i <= input; i++ {
		divBy2 := i%2 == 0
		divBy3 := i%3 == 0
		divBy5 := i%5 == 0

		if divBy2 {
			fmt.Print("Din")
		}

		if divBy3 {
			fmt.Print("Don")
		}

		if divBy5 {
			fmt.Print("Dan")
		}

		if !(divBy2 || divBy3 || divBy5) {
			fmt.Print(i)
		}

		if i != input {
			fmt.Print(" ")
		}
	}
}
