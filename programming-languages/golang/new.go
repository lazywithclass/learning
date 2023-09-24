package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {

	b := bufio.NewScanner(os.Stdin)

	for {

		fmt.Println("Inserisci una parola:")
		b.Scan()
		s := b.Text()

		if s == "0" {
			break
		}

		var slice []string
		slice = make([]string, 0)

		for i := 0; i < len(s); i++ {
			if s[i] == s[i+1] {
				slice = append(slice, string(s[i]))
			}
		}

		if len(slice) == 0 {
			fmt.Println("Non ci sono doppie")
		} else {

			fmt.Println(slice)
		}
	}
}
