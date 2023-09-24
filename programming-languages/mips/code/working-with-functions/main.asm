	.text
	.globl main
	
main:
	# invocation
	li $a0 3
	li $a1 2
	jal maxfunc
	
	# get the return value
	move $t0 $v0
	
	# print max
	move $a0 $t0
	li $v0 1
	syscall
	
	li $v0 10
	syscall
	
# we could write the function here, but we wanted to have it modularised in a separated file
# maxfunc:
