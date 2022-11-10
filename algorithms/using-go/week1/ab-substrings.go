package main

import "fmt"

func main() {
	seq := "ccbaacbabbcbab"

	subseqs := 0
	for i := 0; i < len(seq); i++ {
		if seq[i] == 'a' {
			for j := i + 1; j < len(seq); j++ {
				if seq[j] == 'b' {
					subseqs++
				}
			}
		}
	}
	fmt.Println(subseqs)
}
