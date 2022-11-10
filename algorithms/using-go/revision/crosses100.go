package main

import (
	"fmt"
)

// write a program that reads from stdin a sequence of positive integers
// terminated by -1, it should print the first number that is greater
// than 100 if present, otherwise it should print "no number"

func main() {
	var firstThatPasses100 int
	found := false
	for true {
		var number int
		fmt.Scan(&number)
		if !found && number > 100 {
			found = true
			firstThatPasses100 = number
		}

		if number == -1 {
			break
		}
	}

	if found {
		fmt.Println(firstThatPasses100)
	} else {
		fmt.Println("No number is greater than 100")
	}
}
