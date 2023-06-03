	.text
	.globl uppercaseletter
	
uppercaseletter:
	# $a0 letter's address 
	# to uppercase a letter sum -32
	
	lb $t0 0($a0)
	addi $t0 $t0 -32
	sb $t0 0($a0)
	
	jr $ra