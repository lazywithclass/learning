package main

import "fmt"

// given a series of positive integers from stdin terminated by 0
// print '+' everytime a number is >=0 than the previous , '-' otherwise

func main() {
	var current int
	fmt.Scan(&current)
	var previous int
	for current > 0 {
		if current >= previous {
			fmt.Println("+")
		} else {
			fmt.Println("-")
		}
		previous = current
		fmt.Scan(&current)
	}
}
