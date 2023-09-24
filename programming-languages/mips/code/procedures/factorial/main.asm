	.data
	ENTER_NUMBER: .asciiz "Enter the number: "
	
	.text
	.globl main
	
main:
	la $a0 ENTER_NUMBER
	li $v0 4
	syscall
	la $v0 5
	syscall
	
	# $a0 has the input
	move $a0 $v0
	jal factorial
	
	# $v0 has the output, print it
	move $a0 $v0
	li $v0 1
	syscall
	j exit
	
exit:
	li $v0 10
	syscall
	