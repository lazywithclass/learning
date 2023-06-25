	.data
	SPACE: .asciiz " "
	NEWLINE: .asciiz "\n"
	
	# the first position in the array is the number of elements contained
	# the second position in the array is its capacity 
	# from the third position onwards there are its elements
	# each position holds the address at which the value could be found
	ARR: .word 9 10 1 2 3 4 5 5 5
	
	.text
	.globl push_back print_all
	
push_back:
	# in $a0 we have the element
	move $t2 $a0

	# TODO
	# if this is the last element we can add
	#   make space
	#   which means to
	#   call smbr with double the space
	#   copy all elements from this array to the new array
	#   return the new base address

	# add this element at the back
	
	la $t0 ARR		
	lw $t1 0($t0)

	sw $t1 0($t0)
	# calculate the address of the new element and store it
	mul $t1 $t1 4
	add $t1 $t0 $t1
	sw $t2 0($t1)
	# store new size
	lw $t1 0($t0)
	addi $t1 $t1 1
	sw $t1 0($t0)
	
	jr $ra
	
print_all:
	# start from the base address + 2
	# loop till the elements contained
	#	print each
	la $t0 ARR
	# size
	lw $t1 0($t0)
	# index starts from position 2
	li $t2 2
	# actual index starts from base address + index * 4
	addi $t3 $t0 8
	j loop_print_all
	
loop_print_all:
	beq $t2 $t1 return_print_all
	
	# print
	lw $t4 0($t3)
	move $a0 $t4
	li $v0 1
	syscall
	
	la $a0 SPACE
	li $v0 4
	syscall
	
	addi $t2 $t2 1
	addi $t3 $t3 4
	j loop_print_all

return_print_all:
	la $a0 NEWLINE
	li $v0 4
	syscall
	
	jr $ra
