	.data
		A: .word 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		H: .word 6
	.text
	.globl main
	
main:
	# A[12] = h + A[8]
	
	# calculate the index for 8 in $t1
	li $t0 4
	li $t1 8
	mul $t1 $t0 $t1
	
	# calculate the index for 12 in $t2
	li $t2 12
	mul $t2 $t0 $t2
	
	# h   + A[8]
	# $s0   $s1
	la $t0 H
	lw $s0 0($t0)
	la $t0 A
	add $t1 $t0 $t1
	lw $s1 0($t1)
	add $t1 $s0 $s1
	
	# put the result into A[12]
	la $t0 H
	add $t0 $t0 $t2
	sw $t1 0($t0)