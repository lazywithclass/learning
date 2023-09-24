package main

import "fmt"

func main() {
	anagrammi("", "asd")
}

func anagrammi(s1, s2 string) {
	if len(s2) <= 1 {
		fmt.Println(s1 + s2)
	} else {
		for i := 0; i < len(s2); i++ {
			// wat
			x := s2[i : i+1]
			y := s2[:i]
			z := s2[i+1:]
			anagrammi(string(s1)+string(x), string(y)+string(z))
		}
	}
}
