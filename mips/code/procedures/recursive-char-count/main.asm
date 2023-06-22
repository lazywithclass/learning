	.data
	STR: .asciiz "this is the string"
	CHA: .asciiz "t"
	
	.text
	.globl main
	
main:
	la $a0 STR
	la $t0 CHA
	lb $a1 0($t0)
	jal charcount
	
	# $v0 has the count
	move $a0 $v0
	li $v0 1
	syscall
	
	j exit
	
exit:
	li $v0 10
	syscall