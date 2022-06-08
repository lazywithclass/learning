package main

import (
	"fmt"
	"os"
	"bufio"
	"strings"
	"strconv"
	"regexp"
)

func main() {
	file, err := os.Open("8.input")
	if err != nil {
		fmt.Println("File reading error", err)
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		lines = append(lines, line)
	}

	acc := 0
	visited := make(map[int]bool)
	for i := 0; i < len(lines); i++ {
		if visited[i] {
			break
		} else {
			visited[i] = true
		}

		line := lines[i]
		split := strings.Split(line, " ")
		command := split[0]
		parameter := split[1]
		switch command {
		case "acc":
			operator, number := extractOperators(parameter)
			if operator == "+" {
				acc += number
			} else {
				acc -= number
			}
		case "jmp":
			operator, number := extractOperators(parameter)
			if operator == "+" {
				i += (number - 1)
			} else {
				i -= (number + 1)
			}
		}
	}
	fmt.Println(acc)
}


func extractOperators(parameter string) (operator string, number int) {
	regexp := regexp.MustCompile(`(\+|-)(\d+)`)
	fields := regexp.FindStringSubmatch(parameter)
	operator = fields[1]
	value := fields[2]
	number, _ = strconv.Atoi(value)
	return
}
