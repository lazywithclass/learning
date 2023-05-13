# using only registers $s0 and $t0
# calculate 1 * 3 + 2 * 3 + 3 * 3 without using the pseudo-instruction mul

	.text
	.globl main
	
main:
	# at the top of the stack we keep the running total

	# calculate 1 * 3
	li $t0, 3
	addi $sp, $sp, -4
	sw $t0, 0($sp)		# now we have 1 at the top of the stack
	
	# calculate 2 * 3
	li $t0, 2
	li $s0, 3
	mult $t0, $s0
	mflo $t0
	# pop the running total from the stack...
	lw $s0, 0($sp)
	addi $sp, $sp, 4
	# ...and sum it
	add $t0, $t0, $s0
	addi $sp, $sp, -4
	sw $t0, 0($sp)		# now we have 7 at the top of the stack
	
	# calculate 3 * 3
	li $t0, 3
	li $s0, 3
	mult $t0, $s0
	mflo $t0
	# pop the running total from the stack...
	lw $s0, 0($sp)
	addi $sp, $sp, 4
	# ...and sum it
	add $t0, $t0, $s0
	addi $sp, $sp, -4
	sw $t0, 0($sp)		# now we have 18 at the top of the stack