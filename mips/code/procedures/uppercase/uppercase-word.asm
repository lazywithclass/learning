	.globl uppercaseword
	
# $a0 has the base address of the string
# $a1 has the length of the string

# to uppercase a letter sum -32 to it
uppercaseword:
	
	# here we need to call for uppercaseletter
	# so we need to save $fp $sp $ra
	move $t0 $fp
	addi $fp $sp -4
	sw $t0 0($fp)
	sw $sp -4($fp)
	sw $ra -8($fp)
	sw $a0 -12($fp)
	add $sp $fp -12
	
	# loop on all letters
	# base address
	move $t2 $a0
	# index
	li $t1 0
	j loopLetters
	
loopLetters:
	beq $t1 $a1 return
	move $a0 $t2
	jal uppercaseletter
	addi $t1 $t1 1
	addi $t2 $t2 1
	j loopLetters

return:
	lw $t0 0($fp)
	lw $sp -4($fp)
	lw $ra -8($fp)
	lw $a0 -12($fp)
	move $fp $t0
	jr $ra
