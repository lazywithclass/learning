	.text
	.globl main
	
main:
	li $t0 100
	li $t1 45
	
	mult $t0 $t1
	mflo $s0
	
	mul $s1 $t0 $t1
	
	div $t0 $t1
	mflo $s2
	
	div $s3 $t0 $t1