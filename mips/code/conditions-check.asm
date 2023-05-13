# a entered by the user
# b entered by the user
# c entered by the user
# if (a >= b && c != 0) {
#     z = c * (a + b)
#     print z
# } else {
#     print "Error"
# }

	.data
msga:   .asciiz "Enter a: "
msgb:   .asciiz "Enter b: "
msgc:   .asciiz "Enter c: "
msgerr: .asciiz "Error"

	.text
	.globl main
	
main:
	# reading numbers
	la $a0 msga
	li $v0 4
	syscall
	li $v0 5
	syscall
	move $s0 $v0
	
	la $a0 msgb
	li $v0 4
	syscall
	li $v0 5
	syscall
	move $s1 $v0
	
	la $a0 msgc
	li $v0 4
	syscall
	li $v0 5
	syscall
	move $s2 $v0
	
	# && check
	beqz $s2 error
	sge $t0 $s0 $s1
	beqz $t0 error

	add $t0 $s0 $s1
	mul $t0 $t0 $s2
	
	move $a0 $t0
	li $v0 1
	syscall
	j exit
	
error:
	la $a0 msgerr
	li $v0 4
	syscall
	j exit
	
exit:
	li $v0 10
	syscall
	