package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Split(bufio.ScanLines)
	prev := 0
	for scanner.Scan() {
		token := scanner.Text()
		curr, _ := strconv.Atoi(token)

		if curr == 0 {
			return
		}

		if prev == curr {
			fmt.Println("last two were equal")
		}

		prev = curr
	}
}
