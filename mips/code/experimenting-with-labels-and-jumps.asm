	.text
	.globl main
	
main:
	li $v0 36
	li $a0 0
	syscall
	
first:
	li $v0 36
	li $a0 1
	syscall
	
#infinite_loop:
#	j first

second:
	li $v0 36
	li $a0 2
	syscall
	
	li $v0 10
	syscall

never:
	li $v0 36
	li $a0 -1
	syscall
	
