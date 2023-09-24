package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	data, err := ioutil.ReadFile("3.input")
	if err != nil {
		fmt.Println("File reading error", err)
		return
	}

	area := strings.Split(string(data), "\n")
	totalTrees := countTrees(1, 1, area)
	totalTrees *= countTrees(3, 1, area)
	totalTrees *= countTrees(5, 1, area)
	totalTrees *= countTrees(7, 1, area)
	totalTrees *= countTrees(1, 2, area)
	fmt.Print(totalTrees)
}

func countTrees(xSpeed int, ySpeed int, area []string) (treesFound int) {
	treesFound = 0
	i := 0
	lineLength := len(area[0])
	for j := xSpeed; i < len(area) - 1; j = j + xSpeed {
		i += ySpeed
		line := area[i]
		if j >= lineLength {
			j = j - len(line)
		}
		
		char := string(line[j])
		if char == "#" {
			treesFound++
		}
	}
	return
}
