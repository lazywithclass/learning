	.data
	.text
	.globl insert_last

# add
# $a0 number
# $a1 pointer to head
# $a2 pointer to next	
insert_last:
	move $t0 $a0

	li $v0 9
	li $a0 8 # 4 byte integer, 4 byte next
	syscall
	
	sw $t0 0($v0)   # number
	sw $zero 4($v0) # next
	
	beq $a1 $a2 last_is_head
	
	sw $v0 4($a2)
	
	j update_len
	
last_is_head:
	sw $v0 8($a1)
	j update_len

update_len:
	lw $t0 0($a1)
	addi $t0 $t0 1
	sw $t0 0($a1)
	
	move $t0 $v0
	move $v0 $a1
	move $v1 $t0 # pointer to this
	
	jr $ra