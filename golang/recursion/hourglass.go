package main

import (
  "fmt"
  )

func main() {
  clessidra(3)
}

func clessidra(n int) {

  if n == 0 {
    return
  }

  for i := 1; i <= n; i++ {
    fmt.Print("*")  
  }
  fmt.Println()
  
  clessidra(n - 1)

  for i := 1; i <= n; i++ {
    fmt.Print("*")  
  }
  fmt.Println()
}
