	.data
	h: .word 10
	A: .word 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
	
	.text
	.globl main
	
main:
	la $s1 h
	la $s2 A
	
	# load h's value
	lw $t0 0($s1)
	
	# load A[8]'s value
	# we can't do lw $t1 8($s2) because the offset is in bytes
	# we have to use 32 (8 * 4)
	lw $t1 32($s2)
	
	# A[12] = h + A[8]
	add $t0 $t0 $t1
	# 48 because it's 12 * 4
	sw $t0 48($s2)
	
