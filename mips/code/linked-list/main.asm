

	.text
	.globl main
	
main:
	li $a0 8 
	jal insert
	li $a0 7
	jal insert
	li $a0 6
	jal insert
	
	jal print
	
	j exit
	
exit:
	li $v0 10
	syscall