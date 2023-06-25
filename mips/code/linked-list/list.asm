	.data
	# at the base address we have the length of the linked list
	# after the base address we have next_pointer
	# further in the data structure
	# at the next two words we have the value, and next_pointer
	LIST: .word 0 0
	
	SIZE_STR: .asciiz "Size: "
	NEWLINE: .asciiz "\n"
	SPACE: .asciiz " "
	
	.text
	.globl insert print
	
#############################
# PUSH
#############################
	
insert:
	# in $a0 we have the new value
	# in $s0 we have the new value
	move $s0 $a0

	# increase the size by 1
	la $t0 LIST
	lw $t1 0($t0)
	addi $t1 $t1 1
	sw $t1 0($t0)

	# request new space with segment break for 2 words	
	li $v0 9
	li $a0 8
	syscall
	
	# in $s1 we have the address for the new memory location
	move $s1 $v0

	j find_last

find_last:
	# get the next
	addi $t0 $t0 4
	lw $t1 0($t0)
	# if it's zero we've found it
	beqz $t1 set_next
	
	# else we proceed further
	move $t0 $t1
	
	j find_last

set_next:
	# set the next address in the previous to last element
	sw $s1 0($t0)
	# set the value in the last element
	sw $s0 0($s1)
	# set the next address to zero in the last element
	sw $zero 4($s1)
	j return
	
#############################
# PRINT
#############################

print:
	la $t0 LIST
	lw $t1 0($t0)
	
	# print size
	li $v0 4
	la $a0 SIZE_STR
	syscall
	li $v0 1
	lw $a0 0($t0)
	syscall
	li $v0 4
	la $a0 NEWLINE
	syscall
	
	addi $t0 $t0 4
	lw $t0 0($t0)
	addi $t0 $t0 4
	
	j print_loop
	
print_loop:
	lw $t1 0($t0)
	beqz $t1 return
	
	# print the value
	addi $t0 $t0 -4
	li $v0 1
	lw $a0 0($t0)
	syscall
	
	li $v0 4
	la $a0 SPACE
	syscall
	
	addi $t0 $t0 4
	
	j print_loop
	
return:
	jr $ra
	
	
