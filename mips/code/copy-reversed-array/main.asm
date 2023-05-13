	.data
	source: .word 1,2
	destination: .word 0,0
	
	.text
	.globl main
	
main:

	la $a0 source
	la $a1 destination
	li $a2 2
	
	jal reverse
	
	# exit
	li $v0 10
	syscall