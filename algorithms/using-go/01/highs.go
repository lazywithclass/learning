// Given a long sequence of N distinct integers representing the heights of a mountain range, print the number of ascents from left to right (an ascent is an increasing sequence of 2 or more integers that starts at a minimum point and ends at a maximum point). Example: in the sequence 9 1 3 5 2 0 8 6, there are two ascents: 1 3 5 and 0 8 (1 3 and 3 5 are not ascents because the first does not end at a maximum point, and the second does not start at a minimum point).

package main

import (
	"fmt"
	"math"
	// "testing/quick"
)

func highs(a []int) int {
	var curr, prev, succ int
	prev = math.MaxInt
	curr = math.MaxInt
	count := 0

	for i := 0; i < len(a); i++ {
		succ = a[i]
		if curr < prev && curr < succ {
			// local min
			count++
		}
		prev = curr
		curr = succ
	}
	return count
}

func main() {
	fmt.Printf("%d\n", highs([]int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}))
	fmt.Printf("%d\n", highs([]int{1, 2, 3, 2, 5, 2, 7, 8, 7, 10}))
	fmt.Printf("%d\n", highs([]int{9, 1, 3, 5, 2, 0, 8, 6}))
	fmt.Printf("%d\n", highs([]int{9, 1, 3, 4, 5, 2, 0, 8, 6}))
}
