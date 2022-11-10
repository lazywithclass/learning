package main

import "fmt"

type person struct {
	id   int
	name string
}

func main() {
	people := []person{
		person{6, "Francesco"},
		person{1, "Andrea"},
		person{5, "Elisa"},
		person{2, "Beatrice"},
		person{3, "Carlo"},
		person{4, "Dino"},
		person{7, "Giorgia"},
		person{9, "Irene"},
		person{8, "Henry"},
	}
	fmt.Println(sort(people))
}

func sort(people []person) []person {
	sorted := make([]person, len(people))
	for _, person := range people {
		sorted[len(people)-person.id] = person
	}
	return sorted
}
