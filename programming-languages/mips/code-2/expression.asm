	.data
	.text
	.globl main

main:
	# A = B+C-(D+E)
	li $s0 1 # B
	li $s1 2 # C
	li $s2 3 # D
	li $s3 4 # E
	
	add $t0 $s0 $s1
	add $t1 $s2 $s3
	
	sub $t2 $t0 $t1