# create an Operations procedure that performs
# sum, subtraction, multiplication, division
# between two integers

# input: the two operands and a parameter to select the operation
# output: the result of the operation, for the division also return the remainder

	.data
errmsg: .asciiz "Please enter a valid (0 - 3) operation"

	.text
	.globl Operations

# sum - 0
# sub - 1
# mul - 2
# div - 3
# $a0 - first operand, $a1 - second operand, $a2 - operation type	
Operations:
	# understand which operations we want to run
	bgt $a2 3 err
	
	beq $a2 0 sum
	beq $a2 1 sub
	beq $a2 2 mul
	beq $a2 3 div
	
sum: 
	add $v0 $a0 $a1
	j return

sub:
	sub $v0 $a0 $a1
	j return

mul:
	mult $a0 $a1
	mflo $v0 
	mfhi $v1
	j return

div:
	div $a0 $a1
	mflo $v0 
	mfhi $v1
	j return

err:
	li $v0 4
	la $a0 errmsg

return: 
	jr $ra