package main

import "fmt"

// data una slice di int e un int, restituisce la slice con numeri diverse dal secondo parametro

func main() {
  fmt.Println(different([]int{1,2,3,4}, 3))
}

func different(numbers []int, n int) []int {
  if len(numbers) == 0 {
    return []int{}
  }
  
  if numbers[0] != n {
    return append(different(numbers[1:], n), numbers[0])
  }
  
  return different(numbers[1:], n)
} 


