package main

import "fmt"


func main() {
  fmt.Println(Filter([]int{1,2,3,4}, func(n int) bool {
    return n % 2 == 0
  }))
}

func Filter(numbers []int, predicate func(n int) bool) []int {
  if len(numbers) == 0 {
    return []int{}
  }
  
  if predicate(numbers[0]) {
    return append(Filter(numbers[1:], predicate), numbers[0])
  }
  
  return Filter(numbers[1:], predicate)
}
