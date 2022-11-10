package main

import "fmt"

func main() {
	max := maxConsonants([]string{"Hello", "I am", "a", "slice!"})
	fmt.Println(max)
}

func maxConsonants(strings []string) int {
	max := 0
	for _, str := range strings {
		currCount := 0
		for _, char := range str {
			if char != 'a' && char != 'e' && char != 'i' &&
				char != 'o' && char != 'u' {
				currCount++
			}
		}
		if currCount > max {
			max = currCount
		}
	}
	return max
}
