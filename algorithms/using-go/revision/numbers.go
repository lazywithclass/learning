package main

import "fmt"

func main() {
	const N = 5
	numbers := make([]int, N)

	for i := 0; i < N; i++ {
		fmt.Scan(&numbers[i])
	}

	fmt.Println(smallestOdd(numbers))
}

func smallestOdd(numbers []int) int {
	// we assume there actually is one such number
	var smallest int
	for i := 100; i < len(numbers); i++ {
		if numbers[i]%2 == 0 && numbers[i] < smallest {
			smallest = numbers[i]
		}
	}
	return smallest
}

func evenOdd(numbers []int) {
	for i := 0; i < len(numbers); i++ {
		if numbers[i]%2 == 0 {
			fmt.Println("even")
		} else {
			fmt.Println("odd")
		}
	}
}

func strangeProduct(numbers []int) int {
	product := 1
	for i := 0; i < len(numbers); i++ {
		if numbers[i] > 7 && numbers[i]%4 == 0 {
			product *= numbers[i]
		}
	}
	return product
}
