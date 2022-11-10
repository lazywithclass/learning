package main

import "fmt"

func main() {
	numbers := []int{9, 1, 3, 5, 2, 0, 8, 6}

	increasing := []int{}
	// assume the array has at least 2 elements
	prev := numbers[0]
	for i := 1; i < len(numbers); i++ {
		curr := numbers[i]
		if curr >= prev {
			increasing = append(increasing, curr)
		} else {
			if len(increasing) >= 2 {
				fmt.Println(increasing)
			}
			increasing = []int{curr}
		}
		prev = curr
	}
}
