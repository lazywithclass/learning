# push inserts a new word in the next address which is $sp - 4
addi $sp $sp -4 # make space
sw $reg 0($sp)

# pop removes the topmost word and $sp moves like $sp + 4
lw $reg 0($sp)
addi $sp $sp 4


