package main

import "fmt"

// funzione ricorsiva che data una slice di int ne restituisce la somma

func main() {
  fmt.Println(sum([]int{1,2,3,4}))
}

func sum(numbers []int) int {
  if len(numbers) == 0 {
    return 0
  }
  
  return sum(numbers[1:]) + numbers[0]
}
