# $a0 contains the first number
# $a1 contains the second number
# $a2 contains the third number
# at the first position of the stack we have the second address
# at the second position of the stack we have the first address

# if a0 == a1 && a1 == a2 then jump to the first address
# if all are different then jump to the second address
# else jump back

	.text
	.globl branch3equal
	
branch3equal:
	beq $a0 $a1 a0EQa1
	beq $a1 $a2 else
	j allDifferent
	
allDifferent:
	# the second address is at the top of the stack
	lw $t0 0($sp)
	jr $t0
	
a0EQa1:
	beq $a1 $a2 a0EQa1EQa2
	j else
	
a0EQa1EQa2:
	# the first address is the second of the stack
	addi $sp $sp 4
	lw $t0 0($sp)
	jr $t0

else:
	jr $ra
	
	


