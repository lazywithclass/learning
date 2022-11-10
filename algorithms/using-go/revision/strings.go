package main

import (
	"fmt"
	"strings"
)

func main() {
	const N = 10
	words := make([]string, N)

	for i := 0; i < N; i++ {
		fmt.Scan(&words[i])
	}

	fmt.Println(howManyContainA(words))
}

// returns the len in bytes of the shortest string
func shortest(words []string) int {
	shortest := 0
	for i := 0; i < len(words); i++ {
		if len(words[i]) < shortest {
			shortest = len(words[i])
		}
	}
	return shortest
}

func firstWithA(words []string) string {
	first := ""
	for i := 0; i < len(words); i++ {
		if strings.Contains(words[i], "A") {
			first = words[i]
			break
		}
	}
	return first
}

func howManyContainA(words []string) int {
	count := 0
	for i := 0; i < len(words); i++ {
		if strings.Contains(words[i], "A") {
			count++
		}
	}
	return count
}
