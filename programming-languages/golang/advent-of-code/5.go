package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	data, err := ioutil.ReadFile("5.input")
	if err != nil {
		fmt.Println("File reading error", err)
		return
	}

	highestSeatId := 0
	lines := strings.Split(string(data), "\n")
	for i := 0; i < len(lines); i++ {
		line := lines[i]
		row := binarySearch(makeRange(127), strings.Split(line[:7], "")) 
		column := binarySearch(makeRange(7), strings.Split(line[7:], "")) 
		seatId := (row * 8) + column
		if seatId > highestSeatId {
			highestSeatId = seatId
		}
		fmt.Println(seatId)
	}
	fmt.Println(highestSeatId)
}

// to find my seat I've used diff <(sort -n out) <(seq 68 970)


func binarySearch(arr []int, directions []string) int {
	if len(directions) == 1 {
		if directions[0] == "B" || directions[0] == "R" {
			return arr[1]
		} else {
			return arr[0]
		}
	}

	pivot := len(arr) / 2
	direction := directions[0]
	if direction == "B" || direction == "R" {
		return binarySearch(arr[pivot:len(arr)], directions[1:])
	} else {
		return binarySearch(arr[0:pivot], directions[1:])
	}
}

func makeRange(n int) []int {
    a := make([]int, n + 1)
    for i := range a {
        a[i] = i
    }
    return a
}
