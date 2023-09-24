package main

import (
	"fmt"
)

func main() {
	fmt.Println(stringAnagrams("ab"))
	fmt.Println(arrayAnagrams([]string{"a", "b"}))
}

func stringAnagrams(s string) []string {
	if s == "" {
		return []string{""}
	}

	var anagrams []string
	for i := range s {
		others := s[:i] + s[i+1:]
		permutations := stringAnagrams(others)
		for p := range permutations {
			anagrams = append(anagrams, string(s[i])+permutations[p])
		}
	}
	return anagrams
}

func arrayAnagrams(s []string) [][]string {
	if len(s) == 0 {
		return [][]string{[]string{}}
	}

	var anagrams [][]string
	for i := range s {
		others := append([]string{}, s[:i]...)
		others = append(others, s[i+1:]...)
		permutations := arrayAnagrams(others)
		for p := range permutations {
			anagrams = append(anagrams, append(permutations[p], s[i]))
		}
	}
	return anagrams
}
