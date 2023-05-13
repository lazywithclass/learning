	.data
msg: .asciiz "Enter a number: "
	
	.text
	.globl main
	
main:
	# show the prompt
	la $a0 msg
	li $v0 4
	syscall
	
	# read the number
	li $v0 5
	syscall
	
	move $t0 $v0
	
	# checking whether it is even
	li $t1 2
	div $t0 $t1
	mfhi $t1
	beqz $t1 even
	
	# it's odd
	addi $t0 $t0 1
	j print
	
even:
	addi $t0 $t0 2
	j print

print:
	move $a0 $t0
	li $v0 1
	syscall

	li $v0 10
	syscall