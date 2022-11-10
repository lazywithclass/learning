package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println("write 10 strings")
	count := 10
	startingWithA := 0
	startingWithB := 0
	for count > 0 {
		var str string
		fmt.Scan(&str)
		if strings.HasPrefix(str, "a") {
			startingWithA++
		}
		if strings.HasPrefix(str, "b") {
			startingWithB++
		}
		count--
	}

	fmt.Println("Starting with a: ", startingWithA)
	fmt.Println("Starting with b: ", startingWithB)
}
