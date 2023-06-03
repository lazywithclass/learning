# suppose we could only use $t0 and $t1
# and suppose we have to use two registers to la 
# and lw something from the data 

# we need to calculate x * y

	.data
	x: .word 3
	y: .word 4
	
	.text
	.globl main
	
main:
	# load x's address in $t0 and its value in $t1 and
	# then onto the stack so to free both registers
	la $t0 x
	lw $t1 0($t0)
	addi $sp $sp -4
	sw $t1 0($sp)
	
	# no need to do the same for y, just load it in $t1
	la $t0 y
	lw $t1 0($t0)
	
	# load x from the stack into $t0
	lw $t0 0($sp)
	# restore stack
	addi $sp $sp 4
	
	# mult
	mul $t0 $t0 $t1