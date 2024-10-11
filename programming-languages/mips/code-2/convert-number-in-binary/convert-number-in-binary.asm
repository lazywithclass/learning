# convert number in binary


	.data
	.text
	.globl convert

# in $a0 we have the number, as one byte	
convert:
	move $t0 $a0 # $t0 has the number

	# allocate memory
	li $v0 9
	li $a0 9 # plus one for the null termination
	syscall 
	
	move $t3 $v0 # base address
	move $t4 $v0 # current address
	
	j convert_inner
	
	
# $t0 number
# $t1 remainder
# $t2 2
# $t3 base address of the array
# $t4 current address of the array
convert_inner:
	beqz $t0 convert_finished
	
	# divide by 2
	li $t2 2
	div $t0 $t2
	
	mflo $t0 # quotient
	mfhi $t1 # remainder
	
	# push the remainder
	addi $t1 $t1 48 # convert from number to ascii
	sb $t1 0($t4)
	addi $t4 $t4 4
	
	j convert_inner
	
convert_finished:
	li $t1 0
	sb $t1 0($t4)

	move $v0 $t3 # return the base of the address
	
	jr $ra