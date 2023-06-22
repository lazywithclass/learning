	# in $a0 we have the number
	
	.text 
	.globl fibonacci

fibonacci:
	move $t0 $fp
	addi $fp $sp -4
	sw $t0 0($fp)
	sw $sp -4($fp)
	sw $ra -8($fp)
	sw $s0 -12($fp)
	sw $s1 -16($fp)
	addi $sp $fp -16

	move $s0 $a0

	beqz $a0 return0
	add $a0 $a0 -1
	beqz $a0 return1
	
	# F(n-2)
	addi $a0 $s0 -2
	jal fibonacci
	move $s1 $v0
	# F(n-1)
	addi $a0 $s0 -1
	jal fibonacci
	
	add $v0 $s1 $v0
	j return
	
return0: 
	li $v0 0
	j return
return1:
	li $v0 1
	j return
return:
	lw $t0 0($fp)
	lw $sp -4($fp)
	lw $ra -8($fp)
	lw $s0 -12($fp)
	lw $s1 -16($fp)
	move $fp $t0 
	jr $ra