package main

import "fmt"


func main() {
  fmt.Println(factorial(4))
}


// 4! = 4 * 3!
func factorial(n int) int {
  if n <= 1 {
    return 1
  }

  if n == 2 {
    return 2
  }
  
  return n * factorial(n - 1)
}
