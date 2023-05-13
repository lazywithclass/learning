	.text
	.globl reverse
	
reverse:
	# $a0 is the address of the source array
	# $a1 is the address of the destination array
	# $a2 is the length of the arrays
	
	# get the address of the last element in the destination array
	mul $t0 $a2 4
	add $a1 $a1 $t0
	addi $a1 $a1 -4
	j loop
	
loop:
	beqz $a2 exit
	addi $a2 $a2 -1
	lw $t1 0($a0)
	sw $t1 0($a1)
	addi $a0 $a0 4
	addi $a1 $a1 -4
	j loop
	
exit:
	jr $ra