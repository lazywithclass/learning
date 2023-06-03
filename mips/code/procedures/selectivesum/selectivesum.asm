# $a0 array base address
# $a1 k
# $a2 array length

# $v0 has the sum of the numbers

# if k = 0 sum odd positioned numbers
# if k = 1 sum even positioned numbers

	.text
	.globl selectivesum
	
selectivesum:
	# set $a2 to the last item position
	subi $a2 $a2 1

	li $v0 0
	beqz $a1 sumodd
	j sumeven
	
sumodd:
	li $t0 1
	addi $a0 $a0 4
	j loop
	
sumeven:
	li $t0 0
	j loop

loop:
	bgt $t0 $a2 return
	
	lw $t1 0($a0)
	add $v0 $v0 $t1
	
	addi $t0 $t0 2
	addi $a0 $a0 8
	j loop

return:
	jr $ra