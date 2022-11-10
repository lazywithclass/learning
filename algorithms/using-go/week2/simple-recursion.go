package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(stones(3))
}

func multiply(a, b int) int {
	if b == 1 {
		return a
	}

	return a + multiply(a, b-1)
}

func largest(xs []float64) float64 {
	if len(xs) == 1 {
		return xs[0]
	}

	return math.Max(
		xs[len(xs)-1],
		largest(xs[:len(xs)-1]),
	)
}

// a pyramid of stones, at the top there's a
// single stone, 4 below it, 9 below it, and so on
func stones(height int) int {
	if height == 1 {
		return 1
	}

	return height*height + stones(height-1)
}
