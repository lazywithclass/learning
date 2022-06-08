package main

import (
  "fmt"
)

func main() {
  fmt.Println(without([]int{1,2,3,4, 3}, 3))
}

// recursive function that returns a list of numbers without a number

func without(numbers []int, number int) []int {
  if len(numbers) == 0 {
    return numbers
  }
  
  if numbers[0] == number {
    return without(numbers[1:], number)
  }
  
  return append(without(numbers[1:], number), numbers[0])
}
