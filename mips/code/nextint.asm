# ask an int
# calculate next int
# sum 1 to it

	.data
	msg1: .asciiz "Insert an integer: "
	msg2: .asciiz "Next integer is: "
	
	.text
	.globl main
	
main:
	li $v0, 4 		# print string
	la $a0, msg1
	syscall
	
	li $v0, 5 		# read integer
	syscall
	move $t0, $v0
	
	addi $t0, $t0, 1 	# next

	li $v0, 4 		# print string
	la $a0, msg2
	syscall

	li $v0, 1
	move $a0, $t0
	syscall
	
	li $v0, 10		# exit
	syscall