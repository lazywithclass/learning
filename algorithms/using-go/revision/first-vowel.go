package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Split(bufio.ScanWords)
	for scanner.Scan() {
		word := scanner.Text()
		found := false
		for _, letter := range word {
			if letter == 'a' || letter == 'e' || letter == 'i' ||
				letter == 'o' || letter == 'u' {
				fmt.Println(string(letter))
				found = true
				break
			}
		}
		if !found {
			fmt.Println("word doesnt contain vowels")
		}
	}
}
