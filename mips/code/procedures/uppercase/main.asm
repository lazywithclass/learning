	.data
	ASK_STRING: .asciiz "Enter the string you want to uppercase: "

	.text
	.globl main

main:
	li $v0 4
	la $a0 ASK_STRING
	# max length of the string
	li $a1 20
	syscall
	li $v0 8
	syscall
	
	# $a0 has the base address of the string 
	# we need to find the length of the string
	# $a1 holds the length of the string
	li $a1 -1
	# $t0 holds the changing base address of the string
	move $t0 $a0
countLength:
	lb $t1 0($t0)
	# the string is 0 terminated
	beqz $t1 callUppercase
	
	addi $t0 $t0 1
	addi $a1 $a1 1
	j countLength
	
callUppercase:
	# $a0 has the base address of the string 
	# $a1 has the length of the string
	jal uppercaseword 
	# print 
	li $v0 4
	syscall

exit: 
	li $v0 10
	syscall
