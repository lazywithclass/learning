# write a procedura that returns the min e max of a given array of integers

	.data
array: .word 2,4,1,42,7,0,4
dimension: .word 6

	.text
	.globl main
	
main:
	la $a0 array
	lw $a1 dimension

	# jal max
	jal min
	move $a0 $v0
	li $v0 1
	syscall
	
	# exit
	li $v0 10
	syscall
