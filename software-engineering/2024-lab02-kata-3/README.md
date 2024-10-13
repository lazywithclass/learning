# CORSO INGEGNERIA DEL SOFTWARE A.A. 2024/25

## LABORATORIO 2

* TEAMMATE 1: <Cognome> <Nome> <matricola> 
* TEAMMATE 2: <Cognome> <Nome> <matricola> 

Ogni coppia di studenti procede a effettuare il **fork** di questo repository.
L'utente che ha effettuato il fork modifica questo README inserendo le opportune **informazioni sui
membri del team** seguendo lo schema sopra riportato.
Inoltre, concede i permessi di scrittura al proprio compagno di team e i *
*permessi di lettura** (≥ `reporter`) ai
docenti (`carlo.bellettini` e `mattia.monga`).

### Processo

Una volta effettuato il **clone** del repository, il gruppo implementa secondo la *metodologia TDD* 
le specifiche riportate di seguito; in maggior dettaglio, ripete i passi seguenti fino ad aver implementato tutte le funzionalità richieste:

* scommenta/implementa un test per le funzionalità richieste **procedendo nell'ordine** in cui sono specificate,
* verifica che **il codice compili correttamente**, ma l'**esecuzione del test
  fallisca**; solo a questo punto effettua un *commit* (usando `IntelliJ` o
  `git add` e `git commit`) iniziando il messaggio di commit con la stringa
  `ROSSO:`,
* aggiunge la minima implementazione necessaria a realizzare la funzionalità, in modo che **il test esegua con successo**; solo a questo punto
  effettua un *commit* (usando `IntelliJ` o `git add` e `git commit`) iniziando il messaggio di commit con la stringa `VERDE:`,
* procede, se necessario, al **refactoring** del codice, accertandosi che le modifiche non
  comportino il fallimento di alcun test; solo in questo caso fa seguire a ogni
  passo di refactoring un *commit* (usando `IntelliJ` o `git add` e `git commit`) iniziando il messaggio di commit con la stringa `REFACTORING:`,
* (eccezionalmente può capitare che un test risulti "verde" appena scommentato:
  in questi esercizi iniziali può succedere più frequentemente che nella realtà
  perché i test non sono scritti dalla coppia programmatrice, ma da chi propone
  l'esercizio; quando succede aggiungere un commit `VERDE IMMEDIATO:`)
* effettua un *push* dei passi svolti su gitlab.di.unimi.it con `IntelliJ` o`git push origin master`.

Al termine del laboratorio effettua un ultimo *push* e **verifica su gitlab.di.unimi.it** che ci sia la completa traccia di *commit* effettuati.
Si suggerisce di eseguire i test non soltanto con `IntelliJ`, ma anche eseguendo
il comando `gradle test` da riga di comando.


### Specifica dei requisiti

Scopo dell'esercitazione è creare un programma Java per il calcolo del punteggio di una partita
di [bowling](https://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring).
Le specifiche funzionali vengono fornite attraverso una serie di test di unità
presenti nel
file [BowlingTest.java](src/test/java/it/unimi/di/sweng/lab02/BowlingTest.java).
Il gruppo procede considerando un singolo test alla volta, come descritto nella
sezione [*Processo*](#processo).

Il software implementato dovrà essere **corretto** rispetto alla specifica
funzionale, e **manutenibile**.
In questo senso il gruppo dovrà adottare uno stile di programmazione orientato agli oggetti usando
le principali convenzioni della programmazione Java.
Inoltre, prestare attenzione a evitare *codice duplicato* e *poco leggibile*.

La *leggibilità* e la *manutenibilità* del software sono attributi di qualità di cui il gruppo deve occuparsi esplicitamente durante la fase di *refactoring*.

([Original Kata](http://www.butunclebob.com/files/downloads/Bowling%20Game%20Kata.ppt) by Robert
Martin aka Uncle Bob)
