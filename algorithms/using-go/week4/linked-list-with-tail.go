package main

import (
	"fmt"
	"strconv"
)

type listNode struct {
	item int
	next *listNode
}

type linkedListWithTail struct {
	head, tail *listNode
}

func main() {
	var list linkedListWithTail
	addNewNodeAtEnd(&list, 1)
	printList(list)
	addNewNodeAtEnd(&list, 2)
	printList(list)
	addNewNodeAtEnd(&list, 3)
	printList(list)
	addNewNodeAtEnd(&list, 4)
	printList(list)
}

func newNode(val int) *listNode {
	return &listNode{val, nil}
}

func addNewNodeAtEnd(l *linkedListWithTail, val int) {
	if l.tail == nil {
		l.tail = newNode(val)
		l.head = l.tail
	} else {
		l.tail.next = newNode(val)
		l.tail = l.tail.next
	}
}

func printList(list linkedListWithTail) {
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
