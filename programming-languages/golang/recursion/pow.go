package main

import "fmt"

func main() {
	fmt.Println(pow(2, 3))
	fmt.Println(pow(3, 3))
}

func pow(N, k int) int {
	if k == 1 {
		return N
	}

	return N * pow(N, k-1)
}
