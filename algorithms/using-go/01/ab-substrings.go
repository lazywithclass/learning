package main

import (
	"fmt"
)

// Given a string of N characters in the alphabet a, b, c, print the number of substrings that start with 'a' and end with 'b' (such substrings can overlap).
// Example: In the string 'ccbaacbabbcbab', the number of 'a-b' substrings is 15. Note that each of the first two 'a's (i.e., the two on the far left) appear in 5 'a-b' substrings

// alphabet is a b c so looping with a for loop
// with index is safe
func abSubstrings(input string) int {
	total := 0
	for index, char := range input {
		if char == 'a' {
			for i := index; i < len(input); i++ {
				if input[i] == 'b' {
					total++
				}
			}
		}
	}
	return total
}

func abSubstringsBetter(input string) int {
	aCount := 0
	total := 0
	for _, char := range input {
		if char == 'a' {
			aCount++
		}

		if char == 'b' {
			total += aCount
		}
	}
	return total
}

func main() {
	fmt.Printf("%d\n", abSubstrings("ccbaacbabbcbab"))
	fmt.Printf("%d\n", abSubstringsBetter("ccbaacbabbcbab"))
}
