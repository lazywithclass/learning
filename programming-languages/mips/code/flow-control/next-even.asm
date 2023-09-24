
	.data
	ASK: .asciiz "Please insert a number: "

	.text
	.globl main
	
main:
	
	la $a0 ASK
	li $v0 4
	syscall
	
	li $v0 5
	syscall
	
	move $t0 $v0 # here we have the number entered
	
	li $t1 2
	div $t0 $t1
	mfhi $t1
	beqz $t1 wasEven
	j wasOdd
	
wasEven: 
	addi $t0 $t0 2
	move $a0 $t0
	li $v0 1
	syscall
	j exit

wasOdd:
	addi $t0 $t0 1
	move $a0 $t0
	li $v0 1
	syscall
	j exit

exit:
	li $v0 10
	syscall

	