package main

import "fmt"

func main() {
  var v1 []int = []int{1, 2, 3, 4}
  var v2 []int = []int{2, 4}
  fmt.Println(controllaOrdine(v1, v2))
}

func controllaOrdine (v1, v2 []int) bool {
    j := 0
    for i := range v1{
        if j >= len(v2){
            return true
        }
        if v1[i] == v2[j]{
            j++
        }
    }
    return false
}
