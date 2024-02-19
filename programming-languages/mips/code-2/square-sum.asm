	.data
	ASK: .asciiz "Insert a number: "
	.text
	.globl main
	
main:
	li $v0 4
	la $a0 ASK
	syscall
	
	li $v0 5
	syscall
	move $s0 $v0 # V is in $s0
	
	li $v0 4
	la $a0 ASK
	syscall
	
	li $v0 5
	syscall
	move $s1 $v0 # N is in $s1
	
	move $s2 $s0 # Sum is in $s2
	
	li $t0 1 # i
	
loop:
	bge $t0 $s1 exit_loop
	
	# Sum = Sum + (i * i)
	mul $t1 $t0 $t0
	add $s2 $s2 $t1
	
	addi $t0 $t0 1
	j loop
 
exit_loop:
	li $v0 1
	move $a0 $s2
	syscall
	j exit	

exit:
	li $v0 10
	syscall
	