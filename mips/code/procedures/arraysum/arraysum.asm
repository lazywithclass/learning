	# in $a0 we have the array's base address
	# in $a1 we have the array's length
	
	.text
	.globl arraysum

arraysum:
	move $t0 $fp
	addi $fp $sp -4
	sw $t0 0($fp)
	sw $sp -4($fp)
	sw $ra -8($fp)
	sw $s0 -12($fp)
	sw $s1 -16($fp)
	addi $sp $fp -16

	beqz $a1 return0
	
	# arraysum(arr, dim-1) + arr[dim-1]
	
	# dim-1
	addi $s0 $a1 -1
	li $t4 4
	mul $t1 $s0 $t4
	# last item's address
	add $t1 $a0 $t1
	lw $s1 0($t1)

	addi $a1 $a1 -1
	jal arraysum
	
	add $v0 $v0 $s1
	j return
	
	
return0:
	li $v0 0
	j return

return:
	lw $t0 0($fp)
	lw $sp -4($fp)
	lw $ra -8($fp)
	lw $s0 -12($fp)
	lw $s1 -16($fp)
	move $fp $t0 
	jr $ra