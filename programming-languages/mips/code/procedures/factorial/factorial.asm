# the number we want to calculate the factorial of is in $a0
	.text
	.globl factorial
	
factorial:
	move $t0 $fp
	addi $fp $sp -4
	sw $t0 0($fp)
	sw $sp -4($fp)
	sw $ra -8($fp)
	sw $s0 -12($fp)
	addi $sp $fp -12
	
	# n is in $s0
	move $s0 $a0
	bne $s0 $zero recursion
	
	# base case
	li $v0 1
	j return
	
recursion:
	# recursive step
	# n * fact (n - 1)
	addi $a0 $s0 -1
	jal factorial
	mul $v0 $v0 $s0
	j return

return: 
	lw $t0 0($fp)
	lw $sp -4($fp)
	lw $ra -8($fp)
	lw $s0 -12($fp)
	move $fp $t0
	jr $ra