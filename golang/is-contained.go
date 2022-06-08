package main

import (
  "fmt"
)

func main() {
  fmt.Println(isContained("alberto", "alb"))
  fmt.Println(isContained("alberto", "ber"))
  fmt.Println(isContained("alberto", "bet"))
  fmt.Println(isContained("alberto", "to"))
  fmt.Println(isContained("alberto", "ta"))
  fmt.Println(isContained("àlberto", "àl"))
  fmt.Println(isContained("àlberto", "al"))
}

func isContained(container, contained string) bool {
  for i := range container {
    if i + len(contained) > len(container) {
      return false
    }    
    
    if container[i:i+len(contained)] == contained {
            return true
         }
  }
  return false
}
