package main

import (
	"fmt"
)

// without running it, what does it print?
func main() {
	slice := make([]string, 1, 3)

	func(slice []string) {
		slice = slice[1:3]      // len(slice) == 2
		slice[0] = "b"          // ["b"]
		slice[1] = "b"          // ["b", "b"]
		fmt.Println(len(slice)) // 2
		fmt.Println(slice)      // ["b", "b"]
	}(slice)

	fmt.Println(len(slice)) // 2
	fmt.Println(slice)      // ["b", "b"]

	copying()
	copying2()
}

func copying() {
	source := [...]int{1, 2, 3}
	destination := source

	source[0] = 42
	// or also (it's the same)
	// destination[0] = 42
	fmt.Println(source)
	fmt.Println(destination)

	anotherSource := []int{1, 2, 3}
	anotherDestination := &anotherSource
	(*anotherDestination)[0] = 42
	fmt.Println(anotherSource)
	fmt.Println(anotherDestination)
}

func copying2() {
	var array []int = []int{1, 2, 3}
	var slice []int = array[:]

	var change func(o []int)
	change = func(o []int) {
		o[0] = 42
	}

	change(array)
	change(slice)
	fmt.Println(array[0])
	fmt.Println(slice[0])
}
