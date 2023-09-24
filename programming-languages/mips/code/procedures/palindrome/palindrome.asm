	# $a0 has the base address of the string
	# $a1 has the length of the string

	.text
	.globl is_palindrome
	
is_palindrome:
	# start with a pointer from the left
	# and one from the right
	
	# left pointer
	move $t0 $a0
	# right pointer TODO remove 4?
	add $t1 $a0 $a1
	addi $t1 $t1 -4
	
	j loop_palindrome
	
loop_palindrome:
	# continue comparing bytes 
	# as long as left is less than right
	bge $t0 $t1 return_ok
	
	# left byte
	lb $t2 0($t0)
	# right byte
	lb $t3 0($t1)
	
	# exit early with 1 if bytes differ
	bne $t2 $t3 return_nok
	
	j loop_palindrome
	
return_nok:
	li $v0 1
	jr $ra
	
return_ok:
	li $v0 0
	jr $ra