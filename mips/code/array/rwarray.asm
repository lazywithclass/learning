	.data
	ARRAY: .word 1 2 3 4 5 6 7 8 9 10 11 12 13
	# needed?
	#.align 2
	INSERT_A: .asciiz "Insert a: "
	INSERT_B: .asciiz "Insert b: "
	INSERT_C: .asciiz "Insert c: "
	
	.text
	.globl main
	
main:
	# a $s0
	li $v0 4
	la $a0 INSERT_A
	syscall
	li $v0 5
	syscall
	move $s0 $v0
	
	# b $s1
	li $v0 4
	la $a0 INSERT_B
	syscall
	li $v0 5
	syscall
	move $s1 $v0
	
	# c $s2
	li $v0 4
	la $a0 INSERT_C
	syscall
	li $v0 5
	syscall
	move $s2 $v0
	
	li $t4 4
	
	# c == 0 swap array[a] array[b]
	beqz $s2 swapAB
	
	# c == 1 array[b] = array[a] 
	li $t0 1
	beq $s2 $t0 overwriteBA
	
	# c == 1 array[b] = array[a] 
	li $t0 -1
	beq $s2 $t0 overwriteAB
	
overwriteAB:
	la $t0 ARRAY
	# $t1 contains base address for a, remember we need to mul by 4
	mul $t1 $s0 $t4
	add $t1 $t0 $t1
	
	# $t2 contains base address for b
	mul $t2 $s1 $t4
	add $t2 $t0 $t2
	
	lw $t0 0($t2)
	sw $t0 0($t1)
	
	j exit
	
overwriteBA:
	la $t0 ARRAY
	# $t1 contains base address for a, remember we need to mul by 4
	mul $t1 $s0 $t4
	add $t1 $t0 $t1
	
	# $t2 contains base address for b
	mul $t2 $s1 $t4
	add $t2 $t0 $t2
	
	lw $t0 0($t1)
	sw $t0 0($t2)
	
	j exit
	
swapAB:
	la $t0 ARRAY
	# $t1 contains base address for a, remember we need to mul by 4
	mul $t1 $s0 $t4
	add $t1 $t0 $t1
	
	# $t2 contains base address for b
	mul $t2 $s1 $t4
	add $t2 $t0 $t2
	
	# save $t1 in $t3
	move $t3 $t1
	
	# swap values
	lw $t5 0($t1)
	lw $t6 0($t2)
	
	sw $t6 0($t1)
	sw $t5 0($t2)
	
	j exit
	
exit:
	li $v0 10
	syscall
	
	
	
	