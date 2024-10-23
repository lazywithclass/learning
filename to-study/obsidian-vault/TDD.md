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

"Given enough eyeballs, all bugs are shallow"
-- Eric S. Raymond

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


Chiarirsi il giro RED GREEN REFACTOR e cosa succede negli edge case
Cercare online una guida in merito


Smell: 
Non ci dovrebbero essere commit non etichettati 
Non ci può essere un refactoring se non si parte da verde 
Nei refactoring non si possono modificare funzionalità 
Nei verdi non si possono modificare i test 
Nei rossi non si dovrebbe modificare codice produzione (solo minimo per fare compilare)


Refactoring
Migliorare codice senza cambiare funzionalita'!
Preparare il design per un funzionalita' che non si integra bene con il design attuale
Eliminare debolezze (debito tecnico)

Code smell - refactoring.guru/refactoring/smells, luzkan.github.io/smells
codice duplicato
metodo troppo lungo
troppi livelli di indentazione (2 massimo 3)
troppi attributi o responsabilita'
lunghe sequenze di if
lista di parametri troppo lunga (che non vuol dire metterli in un oggetto)
numeri magici
commenti
nomi oscuri o inconsistenti
codice morto, che non verra' mai eseguito
getter e setter
test simili

Gitflow
una feature e' una use story
* autocontenuta
* significativa
* consegnabile
* piccola ma non dell'ordine dei 10 minuti

VEDI la slide 

Usare @ParameterizedTest e @ValueSource(ints = {1,2,3})
Per testare un metodo in tre casi, per evitare duplicazione.
Infatti raggruppando piu' esecuzioni e controlli diminuiscono le info che il test mi da.




Vedi kata calistenici per forzare i livelli di indentazione
