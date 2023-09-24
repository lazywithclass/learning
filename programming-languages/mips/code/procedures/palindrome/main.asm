
	.data 
	STRING: .asciiz "annag"
	LENGTH: .word 5
	
	.text
	.globl main
	
main:
	la $a0 STRING
	lw $a1 LENGTH
	jal is_palindrome
	
	move $a0 $v0
	li $v0 1
	syscall
	
exit: 
	li $v0 10
	syscall