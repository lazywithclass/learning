	.data
	ASK_FIRST: .asciiz "Please enter the first number: "
	ASK_SECOND: .asciiz "Please enter the second number: "
	ASK_THIRD: .asciiz "Please enter the third number: "
	
	MSG_FIRST_SEGMENT: .asciiz "I'm in the first segment!"
	MSG_SECOND_SEGMENT: .asciiz "I'm in the second segment!"
	MSG_DONE: .asciiz "We are done"
	
	.text
	.globl main

main:
	la $a0 ASK_FIRST
	li $v0 4
	syscall
	la $v0 5
	syscall
	move $t0 $v0
	
	la $a0 ASK_SECOND
	li $v0 4
	syscall
	la $v0 5
	syscall
	move $t1 $v0

	la $a0 ASK_THIRD
	li $v0 4
	syscall
	la $v0 5
	syscall
	move $t2 $v0
	
	# put the two addresses onto the stack
	addi $sp $sp -4
	la $t3 firstSegment
	sw $t3 0($sp)
	addi $sp $sp -4
	la $t3 secondSegment
	sw $t3 0($sp)
	
	move $a0 $t0
	move $a1 $t1
	move $a2 $t2
	
	# call the procedure
	jal branch3equal
	
	la $a0 MSG_DONE
	li $v0 4
	syscall
	j exit

firstSegment:
	la $a0 MSG_FIRST_SEGMENT
	li $v0 4
	syscall
	j exit
	
secondSegment:
	la $a0 MSG_SECOND_SEGMENT
	li $v0 4
	syscall
	j exit

exit:
	li $v0 10
	syscall