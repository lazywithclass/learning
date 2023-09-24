	.data
	ENTER_NUMBER: .asciiz "Enter the number: "
	.align 2
	
	.text
	.globl main
	
main:
	la $a0 ENTER_NUMBER
	li $v0 4
	syscall
	li $v0 5
	syscall 
	
	move $a0 $v0
	jal fibonacci
	
	# expect the result in $v0
	move $a0 $v0
	li $v0 1
	syscall
	
	j exit
	
exit:
	li $v0 10
	syscall