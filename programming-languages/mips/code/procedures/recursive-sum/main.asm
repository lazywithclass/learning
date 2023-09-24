
	.data
	INSERT_N: .asciiz "Insert the number: "
	
	.text
	.globl main
	
main:
	li $v0 4
	la $a0 INSERT_N
	syscall
	
	li $v0 5
	syscall
	
	move $a0 $v0
	jal recursive_sum
	
	move $a0 $v0
	li $v0 1
	syscall
	
	j exit
	
exit:
	li $v0 10
	syscall