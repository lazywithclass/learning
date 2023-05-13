	.data
	c: .word 2
	i: .word 3
	a: .space 400
	b: .space 400

	.text
	.globl main
	
	
main:
	la $s0, a
	la $s1, b
	la $s2, c
	la $s3, i
	
	# initialise b[i] = 10
	li $t0, 4
	lw $t1, 0($s3)
	mul $t0, $t1, $t0	 # i * 4
	add $t1, $s1, $t0 	 # b[i]'s address
	li $t2, 10
	sw $t2, 0($t1) 	         # $t1 is not the base address but exactly b[i], which
		      		 # si why we don't add an offset the usual way
	
	# a[99] = 5 + b[i] + c
	lw $t0, 0($t1)	 	 # b[i]
	lw $t1, 0($s2)		 # c
	add $t0, $t0, $t1
	addi $t0, $t0, 5         # 5 + b[i] + c
	sw $t0, 396($s0)	 # assignment to a[99]
	
