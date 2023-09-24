package main

import "fmt"

// data una slice di int, ritornare i numeri pari

func main() {
  fmt.Println(extractEven([]int{1,2,3,4,5,6}))
}

func extractEven(numbers []int) []int {
  if len(numbers) == 0 {
    return []int{}
  }
  
  if numbers[0] % 2 == 0 {
    return append(extractEven(numbers[1:]), numbers[0])
  }
  
  return extractEven(numbers[1:])
}
