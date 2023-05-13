	.text
	.globl main
	
main:
	li $s1 100
	li $s2 45
	
	# ISA instruction set, not a pseudo instruction
	mult $s1, $s2
	mflo $s0
	
	# pseudo instruction
	mul $s3, $s1, $s2