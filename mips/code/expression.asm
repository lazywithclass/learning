	.text
	.globl main
	
main:
	# A = B + C - (D + E)
	# using registers from $s0 to $s4
	# assuming 1,2,3,4 as initial values
	
	li $s1, 1 # B
	li $s2, 2 # C
	li $s3, 3 # D
	li $s4, 4 # E
	
	add $t0, $s1, $s2
	add $t1, $s3, $s4

	sub $s0, $t0, $t1