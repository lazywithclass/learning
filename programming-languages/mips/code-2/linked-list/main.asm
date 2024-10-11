# linked list for integers

# in the head we have 
# total nodes
# integer
# pointer to next
# pointer to last

# in the nth+1 we have
# integer
# pointer to next

	.data
	.text
	.globl main
	
main:

	# create 
	li $a0 10
	jal create
	
	# add
	# $a0 number
	# $a1 pointer to head
	# $a2 pointer to next
	
	li $a0 20
	move $a1 $v0
	move $a2 $v1
	jal insert_last

	li $a0 30
	move $a1 $v0
	move $a2 $v1
	jal insert_last
			
	j exit
	
exit:
	li $v0 10
	syscall