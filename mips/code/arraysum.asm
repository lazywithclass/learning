	.data
	h: .word 10 # this is in memory, not in the CPU
	a: .word 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
	
	.text
	.globl main

main:
	# a[12] = h + a[8]
	
	la $s1, h
	la $s2, a
	
	lw $t0, 0($s1) # load h's value
	lw $t1, 32($s2) # load a[8]'s value
	add $t0, $t0, $t1 # h + a[8]
	sw $t0, 48($s2) # write the result in a[12]
	
	
	