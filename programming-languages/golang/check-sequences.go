package main

import (
  "fmt"
)

// prende due slice di interi v1 e v1
// dice se gli elementi di v2 compaiono nello stesso ordine in v1

// v1: 1 2 3 4
// v2: 2 4
// true
func main() {
  fmt.Println(check([]int{1,2,3,4}, []int{2,3,4}))
  fmt.Println(check([]int{1,2,3,4}, []int{4,2,3}))
  fmt.Println(check([]int{1,2,3,4}, []int{1,2,3,4}))
  fmt.Println(check([]int{1,2,3,4}, []int{1,2,3,4,5}))
}

func check(v1, v2 []int) bool {
  var occurrencies map[int]bool = make(map[int]bool)
  index := 0
  for _, n := range v2 {
    for j := index; j < len(v1); j++ {
      if n == v1[j] {
        index = j
        occurrencies[n] = true
      }
    }
  }

  return len(occurrencies) == len(v2)
}
