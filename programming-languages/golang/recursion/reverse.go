package main

import "fmt"

func main() {
	fmt.Println(reverse("reverse"))
	fmt.Println(reverse("pagliaccio"))
}

func reverse(s string) string {
	if len(s) <= 1 {
		return s
	}

	return string(s[len(s)-1]) + reverse(string(s[:len(s)-1]))
}
