# converte, se necessario, un lettera miniuscola in maiuscolo
# in $a0 mi aspetto il carattere (byte)
# in $v0 mettero' il carattere maiuscolo o inalterato se non sono necessari interventi
	.data
a: .ascii "a"	
z: .ascii "z"
	
	.text
	.globl upper_case_letter
	
upper_case_letter:
	# preambolo
	move $t0 $fp
	addi $fp $sp -4
	sw $t0 0($fp) # salvo fp del chiamante
	sw $sp -4($fp)
	sw $s0 -8($fp)
	addi $sp $fp -8
	
	# codice 
	move $s0 $a0
	la $t0 a
	lb $t0 0($t0)
	la $t1 z
	lb $t1 0($t1)
	
	#$s0 >= a && $s0<=z
	blt $s0 $t0 invariato
	bgt $s0 $t1 invariato
	
	addi $s0 $s0 -32
	
invariato:
	move $v0 $s0  # preparo il valore di ritorno	
	
	
	# epilogo
	lw $t0 0($fp) # salvo fp del chiamante
	lw $sp -4($fp)
	lw $s0 -8($fp)
	move $fp $t0
	
	jr $ra