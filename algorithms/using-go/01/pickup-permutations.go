package main

import (
	"fmt"
)


// Given a permutation of 1..N, we want to collect the numbers in increasing order starting by analyzing them from the left. Write a program that determines how many times we will need to move back to the left.
// Example: In the permutation 4 5 1 3 6 2, the output is 2, because 1 is found by always moving to the right, then we continue to the right to collect 2, but to collect 3 we need to go back to the left; we need to go back again to collect 4, and after that, 5 and 6 are found in order by continuing to the right.

func pickup(perms []int) int {
	count := 0
	for i := 1; i < len(perms); i++ {
		if perms[i - 1] > perms[i] {
			count++
		}
	}
	return count
}

func main() {
	fmt.Println(pickup([]int{4,5,1,3,6,2})) // 2
	fmt.Println(pickup([]int{1,3,4,5,6,2})) // 1
	fmt.Println(pickup([]int{1,4,3,6,5,2})) // 3
}
