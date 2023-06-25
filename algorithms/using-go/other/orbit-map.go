package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

// https://adventofcode.com/2019/day/6
func main() {
	scanner := bufio.NewScanner(os.Stdin)
	var edges []edge
	vertexes := make(map[string]bool)
	for scanner.Scan() {
		line := scanner.Text()
		edge := parseEdge(line)
		edges = append(edges, edge)
		vertexes[edge.from] = true
		vertexes[edge.to] = true
	}

	// TODO fix this when you arrive to graphs
	// This is my horrific implementation, I did not study graphs yet.
	// I believe one could do it with Kruskal's algorithm.

	tot := 0
	for vertex := range vertexes {
		tot += findTotalOrbits(vertex, edges)

	}
	fmt.Println(tot)
}

type edge struct {
	from, to string
}

func findTotalOrbits(vertex string, edges []edge) int {
	directVertexes := findDirectVertexesFrom(vertex, edges)
	if len(directVertexes) == 0 {
		return 0
	}

	tot := len(directVertexes)
	for _, directVertex := range directVertexes {
		tot += findTotalOrbits(directVertex, edges)
	}

	return tot
}

func findDirectVertexesFrom(vertex string, edges []edge) []string {
	var vertexes []string
	for _, e := range edges {
		if e.from == vertex {
			vertexes = append(vertexes, e.to)
		}
	}
	return vertexes
}

func parseEdge(line string) edge {
	split := strings.Split(line, ")")
	return edge{split[0], split[1]}
}
