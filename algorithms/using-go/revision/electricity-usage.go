package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	line := scanner.Text()

	// assume there are at least 3
	split := strings.Split(line, " ")
	prev, _ := strconv.Atoi(split[0])
	curr, _ := strconv.Atoi(split[1])
	for i := 2; i < len(split); i++ {
		next, _ := strconv.Atoi(split[i])
		fmt.Println(split[i], next)
		fmt.Println(prev, curr, next)
		if curr < prev && curr < next {
			fmt.Println(curr, "less!")
		}
		prev = curr
		curr = next
	}
}
