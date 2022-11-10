package main

import (
	"fmt"
	"os"
	"strconv"
)

// write a program that reads a number from cli
// then it will read a sequence of positive integers from stdin
// it reads as long as the running total is greater than 0

func main() {
	runningTotal, _ := strconv.Atoi(os.Args[1])
	for runningTotal > 0 {
		var next int
		fmt.Scan(&next)
		runningTotal += next
	}

	fmt.Println(runningTotal)
}
