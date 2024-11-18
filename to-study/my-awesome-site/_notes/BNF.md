---
cssclasses:
  - cornell-note
tags:
  - grammars
  - bnf
---

Set of rules to describe a language grammar. I've found it useful to spend some time writing the grammar and then writing the combinators, which seem to match 1 to 1 with every BNF derivation rule

In [BNF](https://en.wikipedia.org/wiki/Backus%E2%80%93Naur_form) 

```
; the derivation rule for an integer
<integer> ::= <digit>|<integer><digit>
```

there seem to be few rules:
* `<integer>` is nonterminal variable
* `::=` what's on the left must be replaced with what's on the right
* `|` indicates a choice

## Terminal and nonterminal

Terminal symbols are those that cannot be changed.<br />
Nonterminal symbols can change.

[From Wikipedia](https://en.wikipedia.org/wiki/Terminal_and_nonterminal_symbols#Terminal_symbols), if we have 

* the symbol `Ψ` can become `Б``Ψ`
* the symbol `Ψ` can become `Б`

then `Б` is terminal (no rule can change it), `Ψ` is nonterminal.