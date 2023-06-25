package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	list := linkedList{nil}
	for scanner.Scan() {
		line := scanner.Text()
		var command string
		var arg int
		if strings.Contains(line, " ") {
			arr := strings.Split(line, " ")
			command = arr[0]
			arg, _ = strconv.Atoi(arr[1])
		} else {
			command = line
		}
		switch command {
		case "+":
			contained, _ := searchList(list, arg)
			if !contained {
				addNewNode(&list, arg)
			}
		case "-":
			contained, _ := searchList(list, arg)
			if contained {
				removeItem(&list, arg)
			}
		case "?":
			contained, _ := searchList(list, arg)
			if contained {
				fmt.Println(arg, "is contained")
			} else {
				fmt.Println(arg, "is not contained")
			}
		case "c":
			fmt.Println(count(list))
		case "p":
			printList(list)
		case "o":
			printReverseList(list)
		case "d":
			deleteAll(&list)
		case "f":
			return
		}
	}
}

type listNode struct {
	item int
	next *listNode
}

type linkedList struct {
	head *listNode
}

func newNode(item int) *listNode {
	return &listNode{item, nil}
}

// add item as new node at the beginning
func addNewNode(list *linkedList, item int) {
	node := newNode(item)
	node.next = list.head
	list.head = node
}

func printList(list linkedList) {
	listString := ""
	separator := ""
	curr := list.head
	for curr != nil {
		listString = listString + separator + strconv.Itoa(curr.item)
		curr = curr.next
		separator = " -> "
	}
	fmt.Println(listString)
}

func printReverseList(list linkedList) {
	var arr []int
	curr := list.head
	for curr != nil {
		arr = append(arr, curr.item)
		curr = curr.next
	}

	listReversedString := ""
	separator := ""
	for i := len(arr) - 1; i >= 0; i-- {
		listReversedString += separator + strconv.Itoa(arr[i])
		separator = " "
	}

	fmt.Println(listReversedString)
}

func searchList(list linkedList, n int) (bool, *listNode) {
	curr := list.head
	for curr != nil {
		if curr.item == n {
			return true, curr
		}
		curr = curr.next
	}
	return false, nil
}

func removeItem(list *linkedList, n int) bool {
	if list.head == nil {
		return false
	}

	curr := list.head
	var prev *listNode
	for curr != nil {
		if curr.item == n {
			if prev != nil {
				if curr.next != nil {
					prev.next = curr.next
				}
			} else {
				list.head = curr.next
			}
			return true
		}

		prev = curr
		curr = curr.next
	}
	return false
}

func count(list linkedList) int {
	tot := 0
	curr := list.head
	for curr != nil {
		tot++
		curr = curr.next
	}
	return tot
}

func deleteAll(list *linkedList) {
	list.head = nil
}
