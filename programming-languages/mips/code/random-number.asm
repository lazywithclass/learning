	.text
	.globl main
	
main:
	# upper bound
    	li $a1 10  
    	li $v0 42
    	syscall
    	
    	li $v0 1
    	syscall

	j exit
	
exit:
	li $v0 10
	syscall
