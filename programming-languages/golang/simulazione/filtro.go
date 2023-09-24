package main

import (
	"fmt"
)

func main() {
	var input string
	fmt.Scanf("%s", &input)

	var last rune
	for _, character := range input {
		if last == 0 {
			last = character
			continue
		}

		if character == last {
			fmt.Print(string(character))
		}

		last = character
	}
}
