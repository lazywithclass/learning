	.text
	.globl main
	
main:
	li $t0 1
	
loop:
	addi $t0 $t0 1
	j loop			# infinite loop