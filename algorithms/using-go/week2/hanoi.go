package main

import "fmt"

func main() {
	from := []int{3, 2, 1}
	hanoiDetail(len(from), &from, &[]int{}, &[]int{})

	from = []int{8, 7, 6, 5, 4, 3, 2, 1}
	fmt.Println("Moves:", hanoiCount(len(from), &from, &[]int{}, &[]int{}, 0))
}

func hanoiDetail(n int, from, temp, to *[]int) {
	if n > 0 {
		hanoiDetail(n-1, from, to, temp)

		fmt.Println(from, temp, to)
		*to = append(*to, (*from)[len(*from)-1])
		*from = (*from)[:len(*from)-1]
		fmt.Println(from, temp, to)

		hanoiDetail(n-1, temp, from, to)
	}
}

func hanoiCount(n int, from, temp, to *[]int, count int) int {
	if n > 0 {
		count = hanoiCount(n-1, from, to, temp, count)

		*to = append(*to, (*from)[len(*from)-1])
		*from = (*from)[:len(*from)-1]

		count = hanoiCount(n-1, temp, from, to, count+1)
	}

	return count
}
