
	# in $a0 we have the number
	
	.text
	.globl recursive_sum
	
recursive_sum:
	move $s0 $a0

	move $t0 $fp
	addi $fp $sp -4
	sw $t0 0($fp)
	sw $s0 -4($fp)
	sw $sp -8($fp)
	sw $ra -12($fp)
	addi $sp $fp -12
	
	# F(0) = 0
	beqz $a0 return
	
	# F(n) = n + F(n - 1)
	addi $a0 $a0 -1
	jal recursive_sum
	
	add $v0 $s0 $v0
	j return

return:
	lw $t0 0($fp)
	lw $s0 -4($fp)
	lw $sp -8($fp)
	lw $ra -12($fp)
	move $fp $t0
	
	jr $ra