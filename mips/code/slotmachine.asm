# ask the user for an integer NUM
# generate a random number R in the [-NUM, NUM] range
# sum NUM + R
# show the user the result

# to ask and show user for information use dialog windows

	.data
	msg1: .asciiz "Enter a number: "
 	msg2: .asciiz "New number is: "
 	.text
 	.globl main
 	
main:
	# show the message to the user
	li $v0, 51
	la $a0, msg1
	syscall 
	move $t0, $a0
	
	# generate a random number
	mul $t1, $t0, 2
	addi $t1, $t1, 1 	# generate 2 * NUM + 1
	li $v0, 42
	li $a0, 1000 		# seed
	move $a1, $t1
	syscall
	
	# show the result to the user
	move $t1, $a0
	sub $t1, $t1, $t0
	add $t2, $t0, $t1
	li $v0, 56
	la $a0, msg2
	move $a1, $t2
	syscall
	
	# exit
	li $v0, 10
	syscall