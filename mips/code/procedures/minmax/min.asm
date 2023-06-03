# $a0 array's length
# $a1 array's base address
# $v0 holds array's min

	.text
	.globl min

min:
	j setMin
	
setMin:
	lw $v0 0($a1)
	j loopMin

loopMin:
	subi $a0 $a0 1
	addi $a1 $a1 4
	
	beqz $a0 endLoopMin

	lw $t0 0($a1)
	blt $t0 $v0 setMin
	
	j loopMin

endLoopMin:
	jr $ra
