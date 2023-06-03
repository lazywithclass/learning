	.data
	ASK_LENGTH: .asciiz "Enter the length of the array: "
	ASK_NUMBER: .asciiz "Enter a number: "
	ASK_K: .asciiz "Enter k: "
	.align 2
	ARR: .space 200
	
	.text
	.globl main
	
main:
	la $a0 ASK_LENGTH
	li $v0 4
	syscall
	li $v0 5
	syscall
	move $a2 $v0
	
	la $a0 ASK_K
	li $v0 4
	syscall
	li $v0 5
	syscall
	move $a1 $v0

	# use $t0 as loop index
	move $t0 $a2
	# use $t2 as array base address, save it in $t3
	la $t1 ARR
	move $t2 $t1
	j loop
	
loop:
	beqz $t0 endLoop
	
	la $a0 ASK_NUMBER
	li $v0 4
	syscall
	li $v0 5
	syscall
	
	sw $v0 0($t1)
	addi $t1 $t1 4

	subi $t0 $t0 1
	j loop

endLoop:
	move $a0 $t2
	jal selectivesum
	move $a0 $v0
	li $v0 1
	syscall
	j exit

exit:
	li $v0 10
	syscall