package main

import (
	"fmt"
	"os"
)

func main() {
	PrintNumber(1)
	PrintNumber(10)

	dimension := os.Args[1]

	var numbers [dimension][dimension]int = make([dimension][dimension]int, dimension)
	for
}

func PrintNumber(n int) {
	fmt.Printf("%2d\n", n)
}
