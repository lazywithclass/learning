package main

import (
	"fmt"
	"io/ioutil"
	"strings"
	"sort"
)

// Although using a set in the first question was ideal
// continuing down this path in the second question was a
// nightmare
// dont look at this code

func main() {
	data, err := ioutil.ReadFile("6.input")
	if err != nil {
		fmt.Println("File reading error", err)
		return
	}

	var group []map[string]string
	lines := strings.Split(string(data), "\n")
	sum := 0
	for _, line := range lines {
		if line != "" {
			chars := strings.Split(line, "")
			var person map[string]string = make(map[string]string, 0)
			for _, char := range chars {
				person[char] = ""
			}
			group = append(group, person)
			continue
		}
		
		sum += sumCommon(group)
		group = make([]map[string]string, 0)
	}
	// take care of the last line
	sum += sumCommon(group)

	fmt.Println(sum)
}

func sumCommon(group []map[string]string) int {
	var onlyKeys [][]string
	
	for i := 0; i < len(group); i++ {
		person := group[i]
		var keys []string
		for k := range person {
			sort.Strings(keys)
			keys = append(keys, k)
		}
		onlyKeys = append(onlyKeys, keys)
	}

	sum := 0
	person := onlyKeys[len(onlyKeys) - 1]
	for _, key := range person {
		containing := 0
		for i := 0; i < len(onlyKeys); i++ {
			otherPerson := onlyKeys[i]
			if contains(otherPerson, key) {
				containing++
			}	
		}
		if containing == len(onlyKeys) {
			sum++
		}
	}
	
	return sum
}

func contains(a []string, x string) bool {
    for _, n := range a {
        if x == n {
            return true
        }
    }
    return false
}
