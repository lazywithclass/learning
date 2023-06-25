	.text
	.globl main

main:
	#jal init_world
	
	jal print_world
	j exit

exit:
	li $v0 10
	syscall