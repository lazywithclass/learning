	.text
	.globl main
	
main:
	addi $s1, $zero, 5
	addi $s2, $zero, 7
	add $s0, $s1, $s2
	
	# or
	
	li $s1, 5
	li $s2, 7
	add $s0, $s1, $s2