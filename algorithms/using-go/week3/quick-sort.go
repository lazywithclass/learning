package main

import "fmt"

func main() {
	fmt.Println(quickSort([]int{5, 1, 7, 4, 3}, 0, 4))
}

func quickSort(arr []int, from, to int) []int {
	if to-from <= 1 {
		return arr
	}

	pivot := partition(arr, from, to)
	quickSort(arr, from, pivot)
	quickSort(arr, pivot, to)
	return arr
}

func partition(arr []int, from, to int) int {
	pivot := arr[from]
	dx := to
	sx := from
	for sx < dx {
		for ; arr[dx] > pivot && dx > sx; dx-- {
		}
		for ; arr[sx] <= pivot && sx < dx; sx++ {
		}
		arr[sx], arr[dx] = arr[dx], arr[sx]
	}

	arr[from], arr[dx] = arr[dx], arr[from]

	return dx
}
