	.data
		ARR: .word 1 2 3 4 5 6 7 8 9 10 11 12 13
		ASK: .asciiz "Enter a number: "
		NOT: .asciiz "Command not found"
	.text
	.globl main
	
main:
	li $v0 4
	la $a0 ASK
	syscall
	li $v0 5
	syscall
	move $s0 $v0 # a
	
	li $v0 4
	la $a0 ASK
	syscall
	li $v0 5
	syscall
	move $s1 $v0 # b
	
	li $v0 4
	la $a0 ASK
	syscall
	li $v0 5
	syscall
	move $s2 $v0 # c

	li $t0 0
	beq $s2 $t0 ifc0
	
	li $t0 1
	beqz $s2 ifc1
	
	li $t0 -1
	beqz $s2 ifc_1
	
	j not_recognised

not_recognised:
	li $v0 4
	la $a0 NOT
	syscall
	j exit

ifc_1:
	la $t2 ARR
	li $t0 4
	mul $s0 $s0 $t0 
	add $s0 $s0 $t2 # a index
	mul $s1 $s1 $t0 
	add $s1 $s1 $t2 # b index
	lw $t1 0($s1)
	sw $t1 0($s0) # overwrite
	j exit

ifc1:
	la $t2 ARR
	li $t0 4
	mul $s0 $s0 $t0 
	add $s0 $s0 $t2 # a index
	mul $s1 $s1 $t0 
	add $s1 $s1 $t2 # b index
	lw $t0 0($s0)
	sw $t0 0($s1) # overwrite
	j exit

ifc0:
	# swap value with index a with value with index b
	# calculate index a
	la $t2 ARR
	li $t0 4
	mul $s0 $s0 $t0 
	add $s0 $s0 $t2 # a index
	mul $s1 $s1 $t0 
	add $s1 $s1 $t2 # b index
	lw $t0 0($s0)
	lw $t1 0($s1)
	# swap
	sw $t0 0($s1)
	sw $t1 0($s0)
	j exit

exit:
	li $v0 10
	syscall

	
	
	
	
	
	
	
	
	