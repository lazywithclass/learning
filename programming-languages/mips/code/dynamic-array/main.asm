	.text
	.globl main
	
main:
	
	#jal print_all
	
	li $a0 6
	jal push_back
	
	jal print_all
	
	j exit
	
exit:
	li $v0 10
	syscall
