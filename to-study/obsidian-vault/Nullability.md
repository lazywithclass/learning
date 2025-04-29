---
cssclasses: []
tags:
  - software-engineering
  - sweng
  - nullability
  - italian
---
Nullability e' la possibilità di assegnare un valore speciale `null` ad una variabile che indica un riferimento ad un oggetto.  
Il tentativo di de-referenziare un `null` porta una `NullPointerException`.
  
`null` e' assegnabile a qualsiasi variabile di tipo riferimento, ne e' sottotipo. Quindi questo vuole dire che se non si interviene in qualche modo il compilatore non reagirà in alcun modo, e a runtime ci sarà una eccezione non gestita, con il conseguente halt dell'applicazione.  
  
`null` per se non e' il problema, il problema e' il fatto che e' assegnabile a qualsiasi tipo riferimento. 
## Mitigare
  
Per mitigare questa situazione ci sono diverse soluzioni:

* NullObject pattern - da usare quando c'e' il concetto di "nessun valore" o "valore neutro", quindi posso implementare dei metodi che rispondano a questa situazione  
* assertion - per esprimere il design by contract spiegato da Meyer, a runtime non ci sono più  
* annotazione `@NotNull` e `@Nullable` - per esprimere il design by contract spiegato da Meyer, a runtime non ci sono più, inoltre sono gestibili da tool di analisi statica che possono usarle per fornire indicazioni utili durante lo sviluppo  
  
>"[...] But I couldn't resist the temptation to put in a null reference, simply because it was so easy to implement."  
-- Sir Tony Hoare

## Gestione

Si può gestire in due modi

 * contract based 
 * programmazione difensiva

> Un codice non dovrebbe far uso di `null` nelle parti visibili, di interfaccia 

Fail fast: `Objects.requireNonNull` esplicita che non accetto qualcosa come `null`, gestendolo sollevando una `NullPointerException`, pero' viene eseguito sempre, anche in produzione, può avere senso ai bordi di un contratto

```java linenos:1
public Card(Rank rank, Suit suit) {
    this.rank = Object.requireNonNull(rank);
    this.suit = Object.requireNonNull(suit);
  }
}
```

Difensiva: gestione esplicita dei `null`, "sono capace di gestire `null`", programmazione difensiva, pero' viene eseguito sempre, anche in produzione, può avere senso ai bordi di un contratto

```java linenos:1
public Card(Rank rank, Suit suit) {
  if (rank == null || suit == null) {
    throw new IllegalArgumentException();
  }
  
  this.rank = rank;
  this.suit = suit;
}
```

Mi assicuro che in sviluppo non mi arrivi un `null`, ma non e' compito mio:

```java linenos:1
public Card(Rank rank, Suit suit) {
  assert rank != null && suit != null;
  this.rank = rank;
  this.suit = suit;
}
```

Intellij può generare `if` in questo caso, inoltre lascia aperta la porta a tool che potrebbero fare analisi statica grazie alla mia dichiarazione di intento:

```java linenos:1
private final @NotNull Rank rank;
private final @NotNull Suit suit;

public Card(@NotNull Rank rank, @NotNull Suit suit) {
  this.rank = rank;
  this.suit = suit;
}
```

`Optional` e' pensato per essere utilizzato nei tipi di ritorno, non troppo diverso da avere la necessita' di testare per `null`, sebbene sia espressa dal tipo la necessita' di gestire la situazione

Il seguente codice invece e' chiaramente completamente sbagliato, perché viene comunque creato un oggetto con le variabili d'istanza a `null`

```java linenos:1
public Card(Rank rank, Suit suit) {
  if (rank != null && suit != null) {
    this.rank = rank;
    this.suit = suit;
  }
}
```
