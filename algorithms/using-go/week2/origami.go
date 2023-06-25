package main

import (
	"bufio"
	"fmt"
	"os"
	"regexp"
	"strconv"
	"strings"
)

// https://adventofcode.com/2021/day/13
func main() {
	scanner := bufio.NewScanner(os.Stdin)
	dots := make(map[dot]bool)
	var folds []fold
	for scanner.Scan() {
		line := scanner.Text()
		if line == "" {
			continue
		}
		if strings.HasPrefix(line, "fold") {
			fold := parseFold(line)
			folds = append(folds, fold)
		} else {
			dot := toDots(line)
			dots[dot] = true
		}
	}

	for _, f := range folds {
		foldPaper(dots, f)
	}

	print(dots)

}

func foldPaper(dots map[dot]bool, f fold) {
	for d := range dots {
		var newd dot
		if f.direction == "x" && d.x > f.position {
			newd = dot{f.position - (d.x - f.position), d.y}
			dots[newd] = true
			delete(dots, d)
		}
		if f.direction == "y" && d.y > f.position {
			newd = dot{d.x, f.position - (d.y - f.position)}
			dots[newd] = true
			delete(dots, d)
		}
	}
}

func print(dots map[dot]bool) {
	var width int
	var height int

	for dot := range dots {
		if dot.x > width {
			width = dot.x
		}
	}

	for dot := range dots {
		if dot.y > height {
			height = dot.y
		}
	}

	for i := 0; i <= height; i++ {
		for j := 0; j <= width; j++ {
			if dots[dot{j, i}] {
				fmt.Print("#")
			} else {
				fmt.Print(".")
			}
		}
		fmt.Println()
	}

}

type dot struct {
	x, y int
}

func toDots(line string) dot {
	split := strings.Split(line, ",")
	x, _ := strconv.Atoi(split[0])
	y, _ := strconv.Atoi(split[1])
	return dot{x, y}
}

type fold struct {
	direction string
	position  int
}

func parseFold(line string) fold {
	re, _ := regexp.Compile(`fold along ([x|y])=(\d+)`)
	found := re.FindStringSubmatch(line)
	position := found[1]
	amount, _ := strconv.Atoi(found[2])
	return fold{position, amount}
}
