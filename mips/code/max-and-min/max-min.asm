# given an integer n and an array of integers of length n
# max should output the maximum integer in that array
# min should output the minimum integer in that array

	.text
	.globl max, min
	
max:
	# $a0 contains the length of the array
	# $a1 contains the array

	# index to check that we have to continue looping
	li $t0 1
	# offset of the array
	move $t1 $a1
	# current max
	lw $v0 0($t1)
	j loopmax
	
loopmax:
	beq $a0 $t0 return
	
	# increment indexes
	addi $t0 $t0 1
	addi $t1 $t1 4
	
	lw $t2 0($t1)
	bgt $t2 $v0 setmax
	
	j loopmax

setmax:
	move $v0 $t2
	j loopmax
	
min:
	# $a0 contains the length of the array
	# $a1 contains the array

	# index to check that we have to continue looping
	li $t0 1
	# offset of the array
	move $t1 $a1
	# current min
	lw $v0 0($t1)
	j loopmin
	
loopmin:
	beq $a0 $t0 return
	
	# increment indexes
	addi $t0 $t0 1
	addi $t1 $t1 4
	
	lw $t2 0($t1)
	blt $t2 $v0 setmin
	
	j loopmin
	
setmin:
	move $v0 $t2
	j loopmin

return:
	jr $ra 
	
