package main

import (
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	riga := os.Args[1]
	evoluzioni, _ := strconv.Atoi(os.Args[2])
	split := strings.Split(riga, "")

	var prossimaEvoluzione []string = make([]string, len(split))
	for i := 0; i < evoluzioni; i++ {
		for indice := range split {
			if NumeroBinormiche(split, indice) == 2 {
				prossimaEvoluzione[indice] = "*"
			} else if NumeroBinormiche(split, indice) == 3 {
				prossimaEvoluzione[indice] = "-"
			} else {
				prossimaEvoluzione[indice] = split[indice]
			}
		}
		fmt.Println(strings.Join(prossimaEvoluzione, ""))
		copy(split, prossimaEvoluzione)
	}
}

func NumeroBinormiche(riga []string, indice int) int {
	numeroBinormiche := 0
	if HaBinormica(riga, indice-1) {
		numeroBinormiche++
	}
	if HaBinormica(riga, indice) {
		numeroBinormiche++
	}
	if HaBinormica(riga, indice+1) {
		numeroBinormiche++
	}

	return numeroBinormiche
}

func HaBinormica(riga []string, indice int) bool {
	if indice-1 < 0 {
		indice = len(riga) - 1
	} else if indice+1 > len(riga)-1 {
		indice = 0
	}
	return riga[indice] == "*"
}
