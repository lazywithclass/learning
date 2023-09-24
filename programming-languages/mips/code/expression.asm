	.text
	.globl main
	
main:
	li $s1 1
	li $s2 2
	li $s3 3 
	li $s4 4
	
	add $s3 $s3 $s4
	sub $s2 $s2 $s3
	add $s0 $s1 $s2
	