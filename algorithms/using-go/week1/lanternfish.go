package main

import "fmt"

// https://adventofcode.com/2021/day/6
func main() {
	fishes := []int{3, 4, 3, 1, 2}
	days := 80
	for i := 0; i < days; i++ {
		fishes = spawn(fishes)
	}
	fmt.Println(len(fishes))
}

func spawn(fishes []int) []int {
	new := make([]int, len(fishes))
	toAdd := 0
	for i, n := range fishes {
		if n == 0 {
			new[i] = 6
			toAdd++
		} else {
			new[i] = n - 1
		}
	}
	for i := 0; i < toAdd; i++ {
		new = append(new, 8)
	}

	return new
}
