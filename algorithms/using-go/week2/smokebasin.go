package main

import "fmt"

// https://adventofcode.com/2021/day/9
func main() {

	points := [][]int{
		[]int{2, 1, 9, 9, 9, 4, 3, 2, 1, 0},
		[]int{3, 9, 8, 7, 8, 9, 4, 9, 2, 1},
		[]int{9, 8, 5, 6, 7, 8, 9, 8, 9, 2},
		[]int{8, 7, 6, 7, 8, 9, 6, 7, 8, 9},
		[]int{9, 8, 9, 9, 9, 6, 5, 6, 7, 8},
	}

	fmt.Println(solve(points, 0, 0, 0))
}

// the iterative solution felt a bit easier to do, but maybe
// I didnt find the best recursive solution
func solve(points [][]int, y int, x int, riskLevel int) int {
	if x == len(points[y]) {
		y++
		x = 0
	}

	if y == len(points) {
		return riskLevel
	}

	curr := points[y][x]
	up := 10
	right := 10
	down := 10
	left := 10
	if valid(y-1, len(points)) {
		up = points[y-1][x]
	}
	if valid(x+1, len(points[y])) {
		right = points[y][x+1]
	}
	if valid(y+1, len(points)) {
		down = points[y+1][x]
	}
	if valid(x-1, len(points[y])) {
		left = points[y][x-1]
	}

	if curr < up && curr < right && curr < down && curr < left {
		// fmt.Println("FOUND", curr, "AT", y, x)
		riskLevel += 1 + curr
	}

	return solve(points, y, x+1, riskLevel)
}

func valid(n, dimension int) bool {
	return n < dimension && n >= 0
}
