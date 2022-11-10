package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	fmt.Println(areAnagrams())
}

func areAnagrams() bool {
	scanner := bufio.NewReader(os.Stdin)
	word1, _ := scanner.ReadString('\n')
	word2, _ := scanner.ReadString('\n')

	occurrencies1 := map[rune]int{}
	for i := 0; i < len(word1); i++ {
		occurrencies1[rune(word1[i])]++
	}
	occurrencies2 := map[rune]int{}
	for i := 0; i < len(word2); i++ {
		occurrencies2[rune(word2[i])]++
	}

	if len(occurrencies1) != len(occurrencies2) {
		return false
	}

	for k := range occurrencies1 {
		if occurrencies1[k] != occurrencies2[k] {
			return false
		}
	}

	return true
}

func all() {
	scanner := bufio.NewReader(os.Stdin)
	sentence, _ := scanner.ReadString('\n')
	sentence = strings.ToLower(sentence)

	occurrencies := map[rune]int{}
	for i := 0; i < len(sentence); i++ {
		occurrencies[rune(sentence[i])]++
	}

	for l := 'a'; l <= 'z'; l++ {
		if occurrencies[l] > 0 {
			fmt.Println(string(l), strings.Repeat("*", occurrencies[l]))
		}
	}
}

func single() {
	scanner := bufio.NewReader(os.Stdin)
	sentence, _, _ := scanner.ReadLine()
	letter, _, _ := scanner.ReadRune()

	var count int
	for _, letter2 := range sentence {
		if letter == rune(letter2) {
			count++
		}
	}

	fmt.Println(count)
}
