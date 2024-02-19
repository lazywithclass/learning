	.data
		ASK: .asciiz "Enter a number: "
		SAY: .asciiz "Next even is: "
	.text
	.globl main
	
main:
	la $a0 ASK
	li $v0 4
	syscall
	
	li $v0 5
	syscall
	
	li $t0 2
	div $v0 $t0
	move $t0 $v0
	
	# hi has the remainder
	mfhi $t2
	beqz $t2 even
	
	# if we're here it's odd
	addi $t0 $t0 1
	j print
		
even:
	addi $t0 $t0 2

print:
	la $a0 SAY
	li $v0 4
	syscall
	li $v0 1
	move $a0 $t0
	syscall
	
exit:
	li $v0 10
	syscall
	