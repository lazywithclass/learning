	# $a0 has the base address of the string
	# $a1 has the char
	
	.text
	.globl charcount
	
charcount:
	move $t0 $fp
	addi $fp $sp -4
	sw $t0 0($fp)
	sw $sp -4($fp)
	sw $s0 -8($fp)
	sw $s1 -12($fp)
	sw $ra -16($fp)
	addi $sp $fp -16
	
	lb $s0 0($a0)
	# arr[0] == 0 ?
	beqz $s0 return0
	
	addi $a0 $a0 1
	jal charcount
	beq $s0 $a1 sum1
	j return
	
sum1:
	addi $v0 $v0 1
	j return
	
return0:
	li $v0 0
	j return

return:
	lw $t0 0($fp)
	lw $sp -4($fp)
	lw $s0 -8($fp)
	lw $s1 -12($fp)
	lw $ra -16($fp)
	move $fp $t0
	jr $ra