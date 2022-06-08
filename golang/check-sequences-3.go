package main

import "fmt"

func main() {
  var v1 []int = []int{1, 2, 3}
  var v2 []int = []int{2, 1, 2}
  fmt.Println(check(v1, v2))
}

func check(v1, v2 []int) bool {
  if len(v1) < len(v2) {
    return false
  }
  
  if len(v2) == 0 {
    return true
  }
  
  if v1[0] == v2[0] {
    return check(v1[1:], v2[1:])
  }
  
  return check(v1[1:], v2)
}
