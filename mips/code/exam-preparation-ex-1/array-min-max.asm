	.text
	.globl min max
	
min:
	# $a0 contains the address of the first element of the array
	# $a1 contains the dimension
	move $t0 $a1
	lw $t1, 0($a0)
	j loopMin
	
loopMin:
	beqz $t0 exit
	addi $t0 $t0 -1
	addi $a0 $a0 4
	lw $t2 0($a0)
	blt $t2 $t1 updateMin
	j loopMin
	
updateMin:
	lw $t1 0($a0)
	j loopMin
	
max:
	move $t0 $a1
	lw $t1, 0($a0)
	j loopMax
	
loopMax:
	beqz $t0 exit
	addi $t0 $t0 -1
	addi $a0 $a0 4
	lw $t2 0($a0)
	bgt $t2 $t1 updateMax
	j loopMax
	
updateMax:
	lw $t1 0($a0)
	j loopMax

exit:
	move $v0 $t1
	jr $ra
