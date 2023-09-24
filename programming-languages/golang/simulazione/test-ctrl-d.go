package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	names := make([]string, 0)
	scanner := bufio.NewScanner(os.Stdin)
	for {
		fmt.Print("Enter name: ")
		scanner.Scan()
		text := scanner.Text()
		if len(text) != 0 {
			fmt.Println(text)
			names = append(names, text)
		} else {
			break
		}
	}

	fmt.Println(names)
}
