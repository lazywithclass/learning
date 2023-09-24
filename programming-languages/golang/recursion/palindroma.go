package main

import (
	"fmt"
)

func main() {
	fmt.Println(palindroma("abba"))
	fmt.Println(palindroma("abbaa"))
	fmt.Println(palindroma("0123210"))
}

func palindroma(s string) bool {
	if len(s) == 1 {
		return true
	}

	if len(s) == 2 {
		return s[0] == s[1]
	}

	if s[0] == s[len(s)-1] {
		return palindroma(s[1 : len(s)-1])
	}

	return false
}
