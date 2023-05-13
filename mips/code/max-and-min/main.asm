	.data
	length: .word 6
	integers: .word 2 3 1 7 42 14
	
	.text
	.globl main
	
main:
	lw $a0 length 
	la $a1 integers 
	jal max
	# jal min
	
exit:
	move $a0 $v0
	li $v0 1
	syscall

	li $v0 10
	syscall
