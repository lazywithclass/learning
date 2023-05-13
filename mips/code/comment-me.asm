	.data
v:	.byte 2,0,0,0,4,0,0,0
array:	.byte 2,0,0,0,3,0,0,0,5,0,0,0,7,0,0,0,11,0,0,0,13,0,0,0,17,0,0,0,19,0,0,0

	.text
	.globl main
main:
	# load arrays in registers
	la $s1 array
	la $s2 v

	lw $t0 0($s2)		# $t0 = v[0]
	addi $t0 $t0 -1		# $t0-- (1)
	mul $t0 $t0 4		# 1 * 4 
	add $t1 $s1 $t0		# sum 4 to array's address
	lw $t2 0($t1)		# let c be the content of the newly found address
	addi $t2 $t2 1		# c++

	lw $t0 4($s2)		# $t0 = v[1]
	addi $t0 $t0 -1		# $t0-- (-1)
	mul $t0 $t0 4		# -1 * 4
	add $t3 $s1 $t0		# sum -4 to arrays's address
	lw $t4 0($t3)		# let d be the content of the newly found address
	addi $t4 $t4 -1		# d--

	sw $t2 0($t3)		# $t2 now contains v[1]
	sw $t4 0($t1)		# $t4 now contains v[1]'s address

	li $v0 10		
	syscall			# exit
