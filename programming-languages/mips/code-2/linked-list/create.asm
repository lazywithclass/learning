	.data
	.text
	.globl create
	
# $a0 contains the integer we want to store
create:
	move $t0 $a0

	li $v0 9
	li $a0 12 # 4 byte total, 4 byte integer, 4 byte next
	syscall
	
	# in $v0 we have the base address
	
	li $t1 1
	sw $t1 0($v0)   # total
	sw $t0 4($v0)   # number
	sw $zero 8($v0) # next (empty for now)
	
	# returns head pointer, head pointer (next)
	move $v1 $v0
	
	jr $ra