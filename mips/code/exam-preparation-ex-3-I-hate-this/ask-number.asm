	.data
	ASK: .asciiz "Insert an integer: "
	SAY: .asciiz "The next integer is: "
	
	.text
	.globl main
	
main:
	la $a0 ASK
	li $v0 4
	syscall

	li $v0 5
	syscall
	
	addi $t0 $v0 1
	
	la $a0 SAY
	li $v0 4
	syscall
	
	move $a0 $t0
	li $v0 1
	syscall