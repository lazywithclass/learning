# la procedura che converte in maiuscolo una stringa di byte (assumiamo solo caratteri minuscoli)
# conversione inplace -> sovrascrive il vettore in argomento
# $a0 <- l'indirizzo del vettore

	.text
	.globl upper_case_word

upper_case_word:
	#preambolo
	# salviamo il $fp corrente
	move $t0 $fp  # SALVARE !!!!!
	addi $fp $sp -4
	# salviamo sullo stack il frame pointer e lo stack pointer del chiamante
	sw $t0 0($fp)  # frame pointer del chiamante
	sw $sp -4($fp) # stack pointer del chiamante
	# salvare i registri $s* e il $ra
	sw $ra -8($fp)  # return address 
	sw $s0 -12($fp) 
	sw $s1 -16($fp)
	# aggiorniamo lo stack pointer
	addi $sp $fp -16
	
	#addi $sp $sp -20
	
	#codice
	move $s0 $a0   	# in $s0 avro' l'indirizzo dell'elemento corrente del vettore  
	#lb $s1 0($s0)	# in $s1 terro' il valore dell'elemento corrente del vettore
	
loop:	# "abc"  -> abc\0
	lb $s1 0($s0)  	# in $s1 terro' il valore dell'elemento corrente del vettore
	beq $s1 $zero end
	
	move $a0 $s1
	jal upper_case_letter
	move $s1 $v0
	
	#addi $s1 $s1 -32  #invochiamo un'altra funzione/procedura
	sb $s1 0($s0)
	# proseguiamo con il prossimo elemento
	addi $s0 $s0 1 # il prossimo indirizzo
	
	j loop
end:	
	#epilogo
	#lw $s0 4($sp)
	#lw $s1 0($sp)
	#addi $sp $sp 8
	lw $t0 0($fp)  # frame pointer del chiamante
	lw $sp -4($fp) # stack pointer del chiamante
	# ripristinare i registri $s* e il $ra
	lw $ra -8($fp)  # return address 
	lw $s0 -12($fp) 
	lw $s1 -16($fp)
	
	move $fp $t0
	
	jr $ra