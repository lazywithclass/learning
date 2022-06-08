ackage main

import (
  "fmt"
  "strings"
)

func main() {
  fmt.Println(x("federico", "volpe"))
}

func x(s1, s2 string) string {
  result := s1
  for _, character := range s2 {
    if !strings.Contains(s1, string(character)) {
      result += string(character)
    }
  }
  return result
}

// Prese due stringhe la funzione restituisce una stringa che e’ la prima stringa, 
// a cui sono stati aggiunti i caratteri della seconda che non compaiono nella prima: “abc” e “adc” diventa “abcd” 
