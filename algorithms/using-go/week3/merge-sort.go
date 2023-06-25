package main

import "fmt"

func main() {
	fmt.Println(mergeSort([]int{15, 96, 44, 22, 54, 28, 83}))
}

func mergeSort(arr []int) []int {
	if len(arr) == 0 || len(arr) == 1 {
		return arr
	}

	mid := len(arr) / 2
	left := mergeSort(arr[0:mid])
	right := mergeSort(arr[mid:])
	return merge(left, right)
}

func merge(left, right []int) []int {
	merged := make([]int, len(left)+len(right))
	i := 0
	l := 0
	r := 0
	for l < len(left) && r < len(right) {
		if left[l] <= right[r] {
			merged[i] = left[l]
			l++
		} else {
			merged[i] = right[r]
			r++
		}
		i++
	}

	// add left remains
	for ; l < len(left); l++ {
		merged[i] = left[l]
		i++
	}
	// add right remains
	for ; r < len(right); r++ {
		merged[i] = right[r]
		i++
	}

	return merged
}
