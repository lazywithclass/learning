# function that computes the max between two numbers
# func max(a, b int) int {
#     if (a >= b) {
#         return a
#     } else {
#         return b
#     }
# }

	.text
	.globl maxfunc
	
maxfunc:
	# $a0 holds a, $a1 holds b
	# $v0 will hold the max between the two
	
	# assume b is max
	move $t0 $a1
	# if b > a
	bgt $t0 $a0 end
	# if a >= b
	move $t0 $a0

end:	
	move $v0 $t0
	jr $ra
	
