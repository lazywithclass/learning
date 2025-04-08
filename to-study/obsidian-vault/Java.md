---
cssclasses:
  - cornell-note
tags:
  - java
  - static-binding
  - dynamic-binding
---
# Comparable

```language-java
private static final Comparator<TimeOfRun> COMPARATOR = Comparator
    .comparingDouble(TimeOfRun::time)
    .reversed() // refers to evertything until this point
    .thenComparing(tr -> tr.name)
    .reversed() // refers to evertything until this point
```

Bene disambiguare su tutti i campi. Perche' alcune classe che assumono l'implementazione di `Comparable<T>` potrebbero usare `compareTo` per vedere ad esempio se la chiave e' gia' esistente in una struttura dati.

# Functional interface

TODO

# Exception

Difference between checked and unchecked 

Unchecked: se avvengono il programma non e' recoverable, quindi non ha senso fare try catch. Per lo piu' sono errori del programmatore.

# try with resources

```language-java
// ci assicura che lo scanner venga chiuso
try (Scanner sc = new Scanner(input)) {
  // code
}
```

# ParameterizedTest

https://www.baeldung.com/parameterized-tests-junit-5#6-methods

Un ottimo modo per evitare duplicazione all'interno dei test. Anche i test sono codice.

# Dynamic binding

Si tratta del modo con cui viene selezionato il metodo a partire da un tipo:

* collegamento statico: viene usato il tipo dichiarato dell'identificatore e quindi selezionato il metodo basato su questo tipo.
  Questo metodo e' efficiente perche' l'indirizzo del metodo da chiamare e' determinato a compile time; non tiene conto pero' di ereditarieta' o overriding
* collegamento dinamico: il metodo viene chiamato sul tipo effettivo, non sul tipo apparente

