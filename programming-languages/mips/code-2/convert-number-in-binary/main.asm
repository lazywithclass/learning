	.data
	.text
	.globl main
	
main:
	li $a0 4
	jal convert
	
	move $t0 $v0
	
print:
	lb $t1 0($t0)
	beqz $t1 end
	
	subi $t1 $t1 48
	li $v0 1
	move $a0 $t1
	syscall
	
	addi $t0 $t0 4
	
	j print
	
end:
	li $v0 10
	syscall
	
	
