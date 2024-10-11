---
cssclasses:
  - cornell-note
tags:
  - software-engineering
  - italian
---

> TDD e' una metodologia di sviluppo software, non di testing

> Testing shows the presence, not the absence of bugs.
-- Dijkstra

book test: tiro un libro sulla tastiera perche' cosi genero un input non aspettato, il software deve continuare a funzionare

Tecnica di progettazione guidata dai test che guida verso il design piu' semplice.
Non e' test.
BDD Behavioral Driven Development, potrebbe essere una definizione migliore.

Red Green Refactor Red ...

Refactoring - modifiche del codice senza cambiare funzionalita', per modificare qualche qualita' interna
Avviene dopo il Green perche' se ottengo un Red dopo un Refactoring sono nell'incertezza.

TDD = test first + baby steps

Continuare a fare refactoring senza pieta'. Perche' per farlo ci vuole coraggio.

"Scrivo il test e tra 5 10 minuti il test passa"

<aside>problema degli unit test</aside>

![test|400](tdd-titanic.png)

Sempre bene usare test di integrazione per asserire riguardo la correttezza del programma.

<aside>Red green green ... wat?</aside>

A volte puo' succedere che eseguendo un nuovo test (scommentato ad esempio come vediamo a laboratorio), questo passi senza aver seguito il red green refactor red etc.
Vuol dire che magari la mia soluzione probabilmente ha implementato piu' del necessario.
Quindi vuol dire che non ho scritto la soluzione piu' semplice per far passare il test.