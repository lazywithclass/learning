---
cssclasses:
  - cornell-note
tags:
  - sweng
  - reference-escaping
  - encapsulation
  - information-hiding
  - italian
---

<aside>L8 encapsulation information hiding</aside>

> [!info] Parnas' Law (L8): Solo cio' che e' nascosto puo' essere cambiato liberamente e senza pericoli

* possibilita' di evoluzione: perche' il fatto che nessuno conosca come faccio qualcosa all'interno, a parita' di effetti esterni, mi da liberta' di cambiare scelta.
* facilitare la comprensione del codice: isolo la parte di codice in cui devo cercare, solo certe parti del codice hanno permesso di scrivere in quella variabile (definisco le responsabilita': [Single responsibility principle](SOLID.md#Single%20responsibility%20principle))
* "A class is more reusable when you minimize the assumptions other classes must make to use it"

# Reference escaping

Reference escaping - "pensavo di essere protetto dall'encapsulation, ma invece e' peggio perche' e' difficile da trovare"

Non sempre c'e' reference escaping, dipende se cio' che sto passando e' o meno un segreto, se non e' un segreto o se e' immutabile allora non c'e'.

## Variabili di istanza non private

Normalmente le variabili di istanza devono essere private, chiaramente non vuol dire mettere getter e setter. Vedere lo stato puo' essere corretto, cio' che non e' corretto e' consentire la modifica.

## Tenere un riferimento esterno alla classe

Si prende un riferimento esterno senza farsene una copia. Chiaramente puo' succedere nei punti di contatto con l'esterno come setter o costruttori.

## Immutabilita'

Oggetti immutabili possono essere condivisi.

Cosa vuol dire essere immutabili?
Non vuol dire essere stateless, vuol dire che non c'e' modo di cambiare lo stato dell'oggetto dopo la sua inizializzazione. `String` e' immutabile ma una volta creata non la modifico piu'.

# Encapsulation e astrazioni

Dare un nome ai concetti: type abstraction.

# Tell dont ask

https://martinfowler.com/bliki/TellDontAsk.html

"Non chiedere i dati, ma di cosa vuoi che faccia sui dati"; vuol dire ad esempio minimizzare i getter e creare una funzione che provveda al vero obiettivo per il quale abbiamo creato il getter.

# Open Closed principle

> We expect that the methods of a class are not closed to changes in the member variables of that class. However we do expect that any other class, including subclasses are closed against changes to those variables. We have a name for this expectation, we call it: encapsulation

[Open closed principle](SOLID.md#Open%20closed%20principle)