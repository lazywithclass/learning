	.text
	.globl count
	
count:
	# $a0 contains the sentence
	# $t0 contains the total count of words
	# $t1 contains the current character
	li $t0 1
	j loop
	
loop:
	lb $t1 0($a0)
	beqz $t1 exit
	addi $a0 $a0 1
	beq $t1 32 update
	j loop
	
update:
	addi $t0 $t0 1
	j loop
	
exit:
	move $v0 $t0 
	jr $ra