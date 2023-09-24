package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
	"regexp"
)


func main() {
	file, err := os.Open("7.input")
	if err != nil {
		fmt.Println("File reading error", err)
	}
	defer file.Close()

	var lines []string
	var bagsContainingGolden = make(map[string]bool)

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		lines = append(lines, line)
		bagsNames := extractBagsNames(line)
		for i, name := range bagsNames {
			if name == "shiny gold" && i != 0 {
				bagsContainingGolden[bagsNames[0]] = true
			}
		}
	}

	for i := 0; i < len(lines); i++ {
		line := lines[i]
		bagsNames := extractBagsNames(line)
		for _, name := range bagsNames {
			if bagsContainingGolden[name] && !bagsContainingGolden[bagsNames[0]] {
				bagsContainingGolden[bagsNames[0]] = true
				i = 0
			}
		}
	}

	fmt.Println(len(bagsContainingGolden))
}

func extractBagsNames(line string) []string {
	split := strings.Split(line, " contain ")
	bag := extractBagName(split[0])
	var bags []string
	bags = append(bags, bag)
	for _, item := range strings.Split(split[1], ", ") {
		bags = append(bags, extractBagName(item))
	}
	return bags
}

func extractBagName(line string) string {
	re := regexp.MustCompile(`\d? ?([\w\s]+) bags?`)
	return re.FindAllStringSubmatch(line, -1)[0][1]
}
