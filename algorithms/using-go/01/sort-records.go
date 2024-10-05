package main

import (
	"fmt"
)

// Consider a vector of N structures, each composed of a name and a unique key ranging from 1 to N. The keys are unique, meaning they are not repeated. Design an algorithm that reorders the vector in the following way: in position 0, there should be the element with key N, in position 1 the element with key N−1, and so on, until position N−1, which should contain the element with key 1.

type person struct {
	id   int
	name string
}

func sort(records []person) []person {
	for index, p := range records {
		pos := len(records) - p.id
		if pos == index  {
			// this record is at its position
			continue
		}
		tmp := records[pos]
		records[pos] = p
		records[index] = tmp
	}
	return records
}

func main() {
	records := make([]person, 0)
	records = append(records, person{id: 6, name: "Francesco"})
	records = append(records, person{id: 1, name: "Andrea"})
	records = append(records, person{id: 5, name: "Elisa"})
	records = append(records, person{id: 2, name: "Beatrice"})
	records = append(records, person{id: 3, name: "Carlo"})
	records = append(records, person{id: 4, name: "Dino"})
	records = append(records, person{id: 7, name: "Giorgia"})
	records = append(records, person{id: 9, name: "Irene"})
	records = append(records, person{id: 8, name: "Henry"})
	fmt.Printf("%v\n", sort(records))
	
}
