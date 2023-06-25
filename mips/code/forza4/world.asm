	.data
	NEW_LINE: .asciiz "\n"
	SPACE: .asciiz " "
	
	# legend:
	# 3 means border
	# 0 means empty
	# 1 means player 1 placed one token there
	# 2 means player 2 placed one token there
	.globl WORLD
	WORLD: 	.word 3 0 0 0 0 0 0 0 3
	      	.word 3 0 0 0 0 0 0 0 3
		.word 3 0 0 0 0 0 0 0 3
		.word 3 0 0 0 0 0 0 0 3
		.word 3 0 0 0 0 0 0 0 3
		.word 3 0 0 0 0 0 0 0 3
		.word 3 3 3 3 3 3 3 3 3

	.text
	.globl init_world print_world

init_world:
	la $v0 WORLD
	jr $ra
	
print_world:

	# column length
	li $t1 9

	# row index
	li $t2 0
	# row length
	li $t3 7
	
	j print_world_loop

print_world_loop:
	beq $t2 $t3 print_return
	# column index
	li $t0 0
	
	j print_line 
	
	j print_world_loop
	
print_line:
	beq $t0 $t1 print_finish_line

	la $t4 WORLD
	# calculate the offset
	mul $t5 $t2 $t1
	add $t5 $t5 $t0
	mul $t5 $t5 4
	
	# print cell
	add $t6 $t4 $t5
	lw $a0 0($t6)
	li $v0 1
	syscall
	la $a0 SPACE
	li $v0 4
	syscall
	
	addi $t0 $t0 1
	j print_line

print_finish_line:
	la $a0 NEW_LINE
	li $v0 4
	syscall
	addi $t2 $t2 1
	j print_world_loop
	
print_return:
	jr $ra
	