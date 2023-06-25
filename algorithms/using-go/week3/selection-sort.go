package main

import "fmt"

func main() {
	fmt.Println(selectionSort([]int{15, 96, 44, 22, 54, 28, 83}))
	fmt.Println(selectionSortRec([]int{15, 96, 44, 22, 54, 28, 83}, 6))
}

// this time looking for max
func selectionSortRec(numbers []int, to int) []int {
	if to == 0 {
		return numbers
	}
	maxIndex := 0
	max := numbers[0]
	for i := 0; i <= to; i++ {
		if numbers[i] > max {
			max = numbers[i]
			maxIndex = i
		}
	}
	numbers[maxIndex], numbers[to] = numbers[to], numbers[maxIndex]
	return selectionSortRec(numbers, to-1)
}

func selectionSort(numbers []int) []int {
	for i := 0; i < len(numbers); i++ {
		minIndex := findMinIndex(numbers, i)
		// fmt.Println("SWAP", numbers[i], numbers[minIndex])
		numbers[minIndex], numbers[i] = numbers[i], numbers[minIndex]
	}
	return numbers
}

func findMinIndex(numbers []int, from int) int {
	minIndex := from
	min := numbers[from]
	for i := from; i < len(numbers); i++ {
		if numbers[i] < min {
			min = numbers[i]
			minIndex = i
		}
	}
	return minIndex
}
