	.data
	C: .word 2
	i: .word 3
	A: .space 400 # 100 elements so 4 * 100
	B: .space 400

	.text
	.globl main

main:
	# calculate
	# A[99] = 5 + B[i] + C
	# given that
	# i=3, C=2, B[i]=10
	
	la $s0 A
	la $s1 B
	la $s2 C
	la $s3 i

	# B[i]=10, so we want to do i * 4 
	li $t0 4
	lw $t1 0($s3) # so we have i's value
	mul $t0 $t1 $t0 # i * 4
	
	# we can't do sw $t0 $s3($s4), so
	add $t1 $s1 $t0 # now w have B[i]'s address
	li $t2 10
	sw $t2 0($t1)
	
	lw $t0 0($t1) # B[i]
	lw $t1 0($s2) # C
	add $t0 $t0 $t1
	add $t0, $t0 5
	
	# assign that to A[99]
	sw $t0 396($s0)
	
	