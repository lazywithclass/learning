package main

import (
	"fmt"
	"strconv"
	"os"
	"bufio"
	"sort"
)

func main() {

	file, err := os.Open("9.input")
	if err != nil {
		fmt.Println("File reading error", err)
	}

	const INVALID_NUMBER = 36845998
	var numbers []int

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		number, _ := strconv.Atoi(line)
		numbers = append(numbers, number)
	}

	for i := 0; i < len(numbers); i++ {
		var testingNumbers []int
		testingNumbers = append(testingNumbers, numbers[i])
		sum := numbers[i]
		for j := i + 1; j < len(numbers); j++ {
			testingNumbers = append(testingNumbers, numbers[j])
			sum += numbers[j]
			if sum == INVALID_NUMBER {
				sort.Ints(testingNumbers)
				fmt.Println(testingNumbers[0] + testingNumbers[len(testingNumbers) - 1])
				return
			}

			if sum > INVALID_NUMBER {
				break
			}
		}
	}
}

