# sum the first n - 1 squares
# if the nth number to add is equal to a number chosen by the user, v, break

	.data
	ASK_N: .asciiz "Enter n: "
	ASK_V: .asciiz "Enter v: "
	BREAK: .asciiz "Break\n"

	.text
	.globl main
	
main:
	# read n ($s0)
	li $v0 4
	la $a0 ASK_N
	syscall
	li $v0 5
	syscall
	move $s0 $v0
	
	# read v ($s1)
	li $v0 4
	la $a0 ASK_V
	syscall
	li $v0 5
	syscall
	move $s1 $v0
	
	# $t0 is our loop index
	li $t0 1
	# $t1 contains the ongoing sum
	li $t1 0
	
	j loop
	
loop:
	bge $t0 $s0 loopend
	
	mul $t2 $t0 $t0
	div $t2 $s1
	mfhi $t3
	beqz $t3 break
	
	add $t1 $t1 $t2
	
	# index++
	addi $t0 $t0 1
	j loop

loopend:
	li $v0 1
	move $a0 $t1 
	syscall
	j exit
	
break:
	li $v0 4
	la $a0 BREAK
	syscall
	j loopend

exit:
	li $v0 10
	syscall