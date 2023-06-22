	.data
	ARRAY: .word 1 4 5 7 9
	
	.text 
	.globl main
	
main:
	la $a0 ARRAY
	li $a1 5
	jal arraysum
	
	# print
	move $a0 $v0
	li $v0 1
	syscall

exit:
	li $v0 10
	syscall