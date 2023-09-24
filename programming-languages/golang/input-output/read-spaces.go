package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	// configure scanner
	scanner.Split(bufio.ScanWords)
	for scanner.Scan() {
		token := scanner.Text()
		fmt.Println("token:", token)
	}
}
