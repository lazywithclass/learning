package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

// https://adventofcode.com/2021/day/10
func main() {
	// points := make(map[rune]int)
	// points[')'] = 3
	// points[']'] = 57
	// points['}'] = 1197
	// points['>'] = 25137

	var scores []int
	scanner := bufio.NewScanner(os.Stdin)
	for scanner.Scan() {
		line := scanner.Text()
		corrupted, _, s := isCorrupted(line)
		if corrupted {
			continue
		}

		scores = append(scores, repairScore(line, s))
	}

	sort.Ints(scores)
	fmt.Println(scores[len(scores)/2])
}

func repairScore(line string, s stack) int {
	points := make(map[rune]int)
	points[')'] = 1
	points[']'] = 2
	points['}'] = 3
	points['>'] = 4

	score := 0
	var top rune
	for len(s) > 0 {
		s, top = s.Pop()
		score *= 5
		score += points[closed(top)]
	}

	return score
}

func isCorrupted(line string) (bool, rune, stack) {
	s := make(stack, 0)
	for _, c := range line {

		if c == '(' || c == '[' || c == '{' || c == '<' {
			s = s.Push(c)
			continue
		}
		// s.Print()

		if c == closed('(') && s.Peek() != '(' ||
			c == closed('[') && s.Peek() != '[' ||
			c == closed('{') && s.Peek() != '{' ||
			c == closed('<') && s.Peek() != '<' {
			return len(s) != 0, c, s
		}

		s, _ = s.Pop()
	}

	return false, 0, s
}

func closed(r rune) rune {
	if r == '(' {
		return ')'
	}
	if r == '[' {
		return ']'
	}
	if r == '{' {
		return '}'
	}
	if r == '<' {
		return '>'
	}
	return 0
}

type stack []rune

func (s stack) Push(v rune) stack {
	return append(s, v)
}

func (s stack) Pop() (stack, rune) {
	l := len(s)
	if l == 0 {
		return s, 0
	}
	return s[:l-1], s[l-1]
}

func (s stack) Peek() rune {
	l := len(s)
	if l == 0 {
		return 0
	}
	return s[l-1]
}

func (s stack) Print() {
	for _, c := range s {
		fmt.Print(string(c))
	}
	fmt.Println()
}
