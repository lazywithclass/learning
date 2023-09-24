package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
	"strconv"
	"regexp"
)


func main() {
	file, err := os.Open("7.input")
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

	fmt.Println(findQuantities(Bag{name: "shiny gold", quantity: 1}, lines))
}

func findQuantities(bag Bag, lines []string) int {
	for _, line := range lines {
		if strings.HasSuffix(line, "no other bags.") {
			continue
		}
		if strings.HasPrefix(line, bag.name) {
			bags := parseBags(line)
			sum := 0
			for _, bag := range bags {
				sum += findQuantities(bag, lines)
			}
			if bag.name != "shiny gold" {
				return (sum * bag.quantity) + bag.quantity
			}
			return sum
		}
	}

	return bag.quantity
}

type Bag struct {
	name string
	quantity int
}

func parseBags(line string) []Bag {
	split := strings.Split(line, " contain ")
	var bags []Bag
	for _, item := range strings.Split(split[1], ", ") {
		bags = append(bags, parseBag(item))
	}
	return bags
}

func parseBag(line string) Bag {
	re := regexp.MustCompile(`(\d) ?([\w\s]+) bags?`)
	result := re.FindStringSubmatch(line)
	quantity, _ := strconv.Atoi(result[1])
	fmt.Println(Bag{name: result[2], quantity: quantity})
	return Bag{name: result[2], quantity: quantity}
}
