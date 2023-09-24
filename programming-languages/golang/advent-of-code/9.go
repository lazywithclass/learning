package main

import (
	"fmt"
	"strconv"
	"os"
	"bufio"
)

func main() {

	file, err := os.Open("9.input")
	if err != nil {
		fmt.Println("File reading error", err)
	}

	const PREAMBLE = 25
	var summable []int

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		number, _ := strconv.Atoi(line)
		if len(summable) < PREAMBLE {
			summable = append(summable, number)
			continue
		}

		if !checkIfSumOf(number, summable) {
			fmt.Println(number)
			return
		}

		summable = append(summable[1:], number)
	}
}

func checkIfSumOf(number int, numbers []int) bool {
	found := false
	for i := 0; i < len(numbers); i++ {
		for j := 0; j < len(numbers); j++ {
			if (numbers[i] + numbers[j]) == number {
				found = true
			}
		}
	}
	return found
}
