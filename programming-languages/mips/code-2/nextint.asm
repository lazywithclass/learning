	.data
		MSG: .asciiz "Enter a number: "
	.text
	.globl main
	
	
main:
	# ask a number
	la $a0 MSG
	li $v0 4
	syscall
	
	# read that number
	li $v0 5
	syscall
	
	# calculate the next number
	addi $t0 $v0 1	
	
	# show the result
	move $a0 $t0
	li $v0 1
	syscall