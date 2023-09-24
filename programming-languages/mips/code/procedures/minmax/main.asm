	.data
	ASK_LENGTH: .asciiz "Insert the length of the array: "
	ASK_NUMBER: .asciiz "Insert a number: "
	ARR: .word 9
	
	.text
	.globl main

main:
	li $v0 4
	la $a0 ASK_LENGTH
	syscall
	li $v0 5
	syscall

	# $a0 has n
	move $a0 $v0
	move $t0 $a0
	# $a1 has the arr
	la $a1 ARR
	move $t1 $a1

	jal loopAskNumber
	
	# we jump back here with the array filled
	# once loopAskNumber finishes
	
	move $a0 $t0
	move $a1 $t1
	jal min
	
	# $a0 has the min of the array
	move $a0 $v0
	li $v0 1
	syscall 
	
	j exit

loopAskNumber:
	beqz $a0 endLoopAskNumber
	
	# save $a0 as it will be overwritten...
	move $t2 $a0
	
	li $v0 4
	la $a0 ASK_NUMBER
	syscall
	li $v0 5
	syscall
	
	# put a number in the array
	sw $v0 0($a1)
	addi $a1 $a1 4
	
	# ...and put it back
	move $a0 $t2
	
	subi $a0 $a0 1
	j loopAskNumber
	
endLoopAskNumber:
	jr $ra

exit:
	li $v0 10
	syscall
