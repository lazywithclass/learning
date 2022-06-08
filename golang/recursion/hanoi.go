package main

import (
	"fmt"
)

func main() {
	Hanoi(3, "sx", "centro", "destra")
}

func Hanoi(n int, origine, appoggio, destinazione string) {
	if n == 1 {
		fmt.Println("Sposta un disco da", origine, "a", destinazione)
		return
	}

	Hanoi(n-1, origine, destinazione, appoggio)
	fmt.Println("Sposta un disco da", origine, "a", destinazione)
	Hanoi(n-1, appoggio, origine, destinazione)
}
