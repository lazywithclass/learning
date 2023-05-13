	.data
	ASK: .asciiz "Enter an integer: "
	SPACE: .asciiz " "
	
	.align 2 # we have to be sure we are aligned
	
	array: .space 8 # we want to reserve space for two bytes
	
	.text
	.globl main
	
main:
	la $a0 ASK
	li $v0 4
	syscall
	
	li $v0 5
	syscall
	move $t0 $v0
	
	addi $t1 $t0 1
	
	# write both integers in the array and print them
	# one at a time
	
	# first $t0...
	la $s0 array
	sw $t0 0($s0)
	# print
	li $v0 1
	move $a0 $t0
	syscall
	
	# ...print a space...
	li $v0 4
	la $a0 SPACE
	syscall
	
	# ...then $t1
	sw $t1 4($s0)
	# print
	li $v0 1
	move $a0 $t1
	syscall