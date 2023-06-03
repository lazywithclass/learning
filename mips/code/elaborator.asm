	.data
	ASK_FIRST: .asciiz "Please enter the first number: "
	ASK_SECOND: .asciiz "Please enter the second number: "
	ASK_OP: .asciiz "Please enter the operation 1 sum, 2 sub, 3 mul, 4 div: "
	
	.text
	.globl main
	
main:
	# first
	li $v0 4
	la $a0 ASK_FIRST
	syscall
	li $v0 5
	syscall
	move $t0 $v0
	
	# second
	li $v0 4
	la $a0 ASK_SECOND
	syscall
	li $v0 5
	syscall
	move $t1 $v0
	
	# op
	li $v0 4
	la $a0 ASK_OP
	syscall
	li $v0 5
	syscall
	move $t2 $v0
	
	move $a0 $t0
	move $a1 $t1
	move $a2 $t2
			
	jal elaborator
	
	move $a0 $v0
	li $v0 1
	syscall
	
	
exit:
	li $v0 10
	syscall
			
# elaborator procedure

elaborator:
	li $t1 1
	li $t2 2
	li $t3 3
	li $t4 4
	
	beq $a2 $t1 add
	beq $a2 $t2 sub
	beq $a2 $t3 mul
	beq $a2 $t4 div
	
add:
	add $v0 $a0 $a1
	j end

sub:
	sub $v0 $a0 $a1
	j end

mul:
	mul $v0 $a0 $a1
	j end

div:
	div $v0 $a0 $a1
	j end

end:
	jr $ra
		
			
	
			
			
			
			
			
			
	