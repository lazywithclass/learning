	.data
sentence: .asciiz "That is not dead which can eternal lie, And with strange aeons even death may die."
	.text
	.globl main

main:
	la $a0 sentence
	jal count
	
	move $a0 $v0
	li $v0 1
	syscall
	
	# exit
	li $v0 10
	syscall