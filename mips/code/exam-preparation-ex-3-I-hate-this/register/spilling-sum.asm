# suppose we can only use $s0 and $t0
# sum 1 2 3 each of these multiplied by 3
# we can't use mul

	.text
	.globl main

main:
	# first
	li $t0 1
	li $s0 3
	mult $t0 $s0
	mflo $t0
	addi $sp $sp -4
	sw $t0 0($sp) 
	
	# second
	li $t0 2
	li $s0 3
	mult $t0 $s0
	mflo $t0
	addi $sp $sp -4
	sw $t0 0($sp) 
	
	# third
	li $t0 3
	li $s0 3
	mult $t0 $s0
	mflo $t0
	
	lw $s0 0($sp)
	add $t0 $t0 $s0
	addi $sp $sp 4
	lw $s0 0($sp)
	add $t0 $t0 $s0
