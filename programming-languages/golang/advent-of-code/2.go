package main

import (
	"fmt"
	"io/ioutil"
	"strings"
	"strconv"
)

func main() {
	data, err := ioutil.ReadFile("2.input")
	if err != nil {
		fmt.Println("File reading error", err)
		return
	}
	split := strings.Split(string(data), "\n")
	validCount := 0
	for _, line := range split {
		if isValid2(line) {
			validCount++
		}
	}

	fmt.Println("valid count:", validCount)
}

func isValid2(line string) bool {
	split := strings.Split(line, ":")

	rules := split[0]
	rulesSplit := strings.Split(rules, "-")
	first, _ := strconv.Atoi(rulesSplit[0])
	second, _ := strconv.Atoi(rulesSplit[1][:len(rulesSplit[1])-2])
	rulesChar := rules[len(rules)-1:]

	password := split[1][1:]

	firstMatches := string(password[first - 1]) == string(rulesChar)
	secondMatches := string(password[second - 1]) == string(rulesChar)

	return firstMatches && !secondMatches ||
		!firstMatches && secondMatches
}

func isValid(line string) bool {
	split := strings.Split(line, ":")

	rules := split[0]
	rulesSplit := strings.Split(rules, "-")
	min, _ := strconv.Atoi(rulesSplit[0])
	max, _ := strconv.Atoi(rulesSplit[1][:len(rulesSplit[1])-2])
	rulesChar := rules[len(rules)-1:]

	password := split[1][1:]

	occurrences := 0
	for i := 0; i < len(password); i++ {
		char := password[i]
		if string(char) == string(rulesChar) {
			occurrences++
		}
	}
	
	return occurrences >= min && occurrences <= max
}
