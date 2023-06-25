package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	// fmt.Println(insertionSort([]int{1, 3, 4, 5}, 2))
	// fmt.Println(insertionSort([]int{1, 3, 4, 5}, 0))
	// fmt.Println(insertionSort([]int{1, 3, 4, 5}, 7))

	var arr []int
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Split(bufio.ScanWords)
	for scanner.Scan() {
		n, _ := strconv.Atoi(scanner.Text())
		if n == 0 {
			break
		}
		arr = insertionSort(arr, n)
		fmt.Println("Sorted array:", arr)
	}
}

func insertionSort(arr []int, n int) []int {
	insertionIndex := len(arr)
	for i := 0; i < len(arr); i++ {
		if n <= arr[i] {
			insertionIndex = i
			break
		}
	}
	arr = shift(arr, insertionIndex)
	arr[insertionIndex] = n
	return arr
}

func shift(arr []int, from int) []int {
	newarr := make([]int, len(arr)+1)
	j := len(arr) - 1
	for i := len(newarr) - 1; i >= 0; i-- {
		if i == from {
			continue
		}
		newarr[i] = arr[j]
		j--
	}
	return newarr
}
