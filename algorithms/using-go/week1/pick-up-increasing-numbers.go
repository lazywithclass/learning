package main

import "fmt"

func main() {
	// how many times do we have to go back left to pick up numbers
	// so that we arrive to a sorted array?

	numbers := []int{4, 5, 1, 3, 6, 2}
	size := len(numbers)

	timesStartedOver := 0
	pickedUp := make([]int, 0)
	nextToFind := 1
	for i := 0; i < size; i++ {
		if nextToFind == numbers[i] {
			pickedUp = append(pickedUp, nextToFind)
			nextToFind++
		}

		if i == size-1 && len(pickedUp) != size {
			i = -1
			timesStartedOver++
		}
	}

	fmt.Println(timesStartedOver)
}
