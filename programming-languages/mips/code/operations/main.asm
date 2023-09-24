# ask user for operands and operator
# show the result

	.data
msgoperand1: .asciiz "Please enter first operand: "
msgoperand2: .asciiz "Please enter second operand: "
msgoperator: .asciiz "Please enter operator: "
msgresult:   .asciiz "Result is: "
space:	     .asciiz " "

	.text
	.globl main
	
main:
	# read first operand
	li $v0 4
	la $a0 msgoperand1
	syscall
	li $v0 5
	syscall
	move $t0 $v0
		
	# read second operand
	li $v0 4
	la $a0 msgoperand2
	syscall
	li $v0 5
	syscall
	move $t1 $v0
	
	# read operator
	li $v0 4
	la $a0 msgoperator
	syscall
	li $v0 5
	syscall
	move $a2 $v0
	
	move $a0 $t0
	move $a1 $t1
	jal Operations
	
	# show results
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 space
	syscall
	move $a0 $v1
	li $v0 1
	syscall
	
	# exit
	li $v0 10
	syscall
	