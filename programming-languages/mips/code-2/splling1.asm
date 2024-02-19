# suppose you could only use $s0 and $t0 registers
# do not use mul

	.data
	
	.text
	.globl main
	
main:
	# multiply by 3 all first three integers (1 2 3)
	# and then sum them all
	
	# 1
	li $t0 1
	li $s0 3
	mult $t0 $s0
	mflo $t0
	addi $sp $sp -4
	sw $t0 0($sp)

	# 2
	li $t0 2
	li $s0 3
	mult $t0 $s0
	mflo $t0
	addi $sp $sp -4
	sw $t0 0($sp)
	
	# 3
	li $t0 3
	li $s0 3
	mult $t0 $s0
	mflo $t0

	# so now we have 3 in $to and then 2 followed by 1 in the stack
	
	lw $s0 0($sp)
	add $t0 $t0 $s0 # now $so has 3 * 3 + 2 * 3
	
	addi $sp $sp 4
	lw $s0 0($sp)
	add $t0 $t0 $s0 # $t0 now holds 18
	
	
	 
	