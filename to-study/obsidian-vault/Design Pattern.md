---
cssclasses:
  - cornell-note
tags:
  - italian
  - design-patterns
---

TODO PRENDERE INFO EXTRA DA
* test driven development by example di Beck
* e dal GOF

All pages are from [GoF's](https://en.wikipedia.org/wiki/Design_Patterns).

# Abstract factory

La dependency injection e' basata sull'Abstract Factory.

ESPANDERE con abstract factory

# Adapter - page 139

> Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.

## Class Adapter

Adatto una singola classe, staticamente, quindi proprio "quella classe".
"Un unico oggetto che puo' essere usato contemporaneamente con le due interfacce diverse (vecchia e nuova)".
<u>Utile se devo adattare solo 1 metodo su 10</u>: gli altri 9 mi vengono gratis, devo fare fatica solo su 1.

![class adapter|300](class-adapter.png)

## Object Adapter

Adatto un oggetto compatibile con una interfaccia, una gerarchia; perche' posso settare diversi adapter, in base all'esigenza.
Due oggetti diversi.
Il nuovo non puo' piu' essere usato con interfaccia vecchia.

![object-adapter|300](object-adapter.png)

Si differenzia perche' sfrutta la composizione.
Posso aggregare piu' classi diversi, quindi c'e' maggiore flessibilita'.

# Builder

## Telescoping constructor

Si hanno multipli costruttori in overloading.
Di difficile manutenibilita' nel momento in cui voglio diverse possibilita', perche' aumentano esponenzialmente i costruttori da dichiarare.

## Setter (o fluent interface)

Problemi di concorrenza e sono obbligato ad accettare la mutabilita' dell'oggetto, perche' ho dei setter, quindi espongo lo stato.

## Static method factory (non e' Factory method)

Ho un costruttore privato con tutti i parametri, e una serie di metodi statici pubblici che al loro interno costruiscono l'oggetto in base a cio' che mi serve.
Posso dare nomi significativi a ogni metodo, per spiegare come crea l'oggetto, cosa che con il costruttore chiaramente non posso fare.

## Builder pattern

![builder-pattern|300](builder-pattern.png)

Classe Builder interna, con costruttore con soli parametri obbligatori. Si hanno poi variabili d'istanza con valori di default, sovrascrivibili tramite metodo, usando fluent interface.

```java linenos:1
public class Xxx { 
  private final TO optionalField1; 
  private final T1 mandatoryField; 
  private final T2 optionalField2; 
  
  private Xxx(Builder builder) { 
    mandatoryField = builder.mandatoryField; 
    optionalField1 = builder.optionalField1; 
    optionalField2 = builder.optionalField2;
  }
     
  public static class Builder { 
    private TO optionalField1 = defaultValue1;
    private T1 mandatoryField; 
    private T2 optionalField2 = defaultValue2;
    public Builder(T1 mf) { 
      mandatoryField = mf;
    } 
    public Builder withOptionalField1(TO of) {
       optionalFieldl = of; 
       return this;
    }
    // etc...
```

Usage

```java linenos:1
Xxx b = Xxx.Builder(mandatoryField)
            .withOptionalField1(optionalField)
            .build();
```

# Chain of responsibility

Disaccoppia chi genera la richiesta da chi puo' soddisfarla.

"Permette di definire una catena di potenziali gestori di una richiesta, non sappiamo a priori chi sara' in grado di gestirla effettivamente"

Evita l'antipattern dello switch case o degli if else

Ogni valutatore guarda se sa rispondere alla domanda, altrimenti manda avanti la domanda al prossimo: c'e' uno specifico ordine in cui le risposte sono ordinate.

<aside>open close principle</aside>

Rimuovo la responsabilita' dalla classe client dell'identificazione di una certa situazione, la responsabilita' non e' sua. Il client e' chiuso rispetto alle modifiche ma aperto alle estensioni, perche' se devo cambiare la logica di gestione delle responsabilita' lui non viene toccato.

ESPANDERE
Analogia con il dynamic binding e il suo albero di navigazione per cercare l'implementazione del metodo.

Ottimo anche quando sviluppato in TDD, in quanto ogni nodo puo' essere sviluppato in isolamento.

# Composite

![composite-pattern|300](pattern-composite.png)

Gestire uniformemente foglie e sotto-alberi.
"Non mi devo accorgere se sto interagendo con una foglia o un sotto-albero".

# Decorator

![pattern-decorator|300](pattern-decorator.png)

Aggiungere nuove funzionalita' o caratteristiche dinamicamente.
Distribuisce le responsabilita' lasciando ogni decorazione molto semplice.

Simile come struttura al [Decorator](Design%20Pattern.md#Decorator), il composite mette assieme tante "cose", il Decorator decora una "cosa".
Notare l'`1` nello schema, vicino a Component.

Possono esserci piu' `ConcreteDecorator`.

L'elemento finale di un Decorator pattern e' cio' che sto decorando, che puo' arrivare anche dopo una sequenza di decorazioni.

I vari oggetti decorati sono estranei gli uni agli altri.

Un esempio in Java sono gli `InputStream`.

Esempio di Decorator dal prof

![esempio-decorator|500](esempio-decorator.png)

# Iterator

[Implementation example](https://github.com/lazywithclass/learning/tree/master/software-engineering/DesignPatterns/src/iterator)

> Fornisce un modo di accedere agli elementi di un oggetto aggregatore in maniera sequenziale senza esporre la rappresentazione interna

![iterator|300](pattern-iterator.png)

Notare come `condimentCost` non dipende da chi sto decorando, dipende da `ConcreteCondiment`.

# Facade

Fornisce una interfaccia unificata e semplificata a un insieme di interfacce separate.

# Factory method

Definisce un'interfaccia per creare un oggetto ma lascia alle sottoclassi la scelta su cosa creare.

![factory-pattern|300](factory-method.png)

# Model View Controller

C'e' una circolarita' di comunicazione.

## Model

Un unico modello che rappresenta lo stato dell'applicazione

## View

La View si aggiorna ogni volta che il Model cambia stato.
Tra Model e View c'e' una relazione tipo Observer.

## Controller

Ascolta gli eventi dell'interfaccia, a fronte del quale eventualmente puo' richiedere una modifica dello stato.
Implementa quindi la Strategy di gestione di un evento. A seconda del Controller che associo ho un comportamento diverso.

# Model View Presenter

![mvc-mvp|400](mvc-mvp.png)

Viene rimossa la dipendenza circolare.
Molto piu' facile da testare.

# NullObject

> Vogliamo creare un oggetto che corrisponda al concetto "nessun valore" o "valore neutro"

```java linenos:1 
public interface CardSource {
    Card draw();
    boolean isEmpty();

    enum NULL implements CardSource {
        INSTANCE;
        public boolean isEmpty() {
            return true;
        }
        public Card draw() {
            assert !isEmpty();
            return null;
        }
    }
}
```

# Observer

Diversi modi di presentare una informazione, esempio
![observer|300](observer-example.png)

...il problema e' che ogni vista e' accoppiata alle altre perche' devono reagire al cambiamento
![observer|300](observer-pattern-problem.png)

Per evitare questo estraiamo la parte comune: lo stato, e lo mettiamo in un oggetto a parte (Subject), osservato dagli altri (Observer)

![observer-pattern|300](observer-pattern.png)

Vogliamo rendere osservabile questo stato

```java linenos:1
public class State {
    private double temp;
    
    public State(double temp) {
        this.temp = temp;
    }
}
```

# Singleton

> Sfrutta il fatto che in Java i campi degli `enum` sono realizzati tramite degli oggetti costanti creati al momento del loro primo uso

```java linenos:1
public enum Singleton {
    INSTANCE;
    public void op() { ... }
}

Singleton.INSTANCE.op();
```

# State

![pattern-state|300](state-pattern.png)

Nei diversi momenti ci sono diversi stati da poter utilizzare (state machine).

Meta stato (nell'esempio `State`)
Stato astratto $\rightarrow$ classe (nell'esempio `ConcreteState`)
Stato concreto $\rightarrow$ le informazioni di contesto

Gli stati non devono conoscersi a vicenda, e' il `Context` che puo' conoscerli.

# Strategy

![pattern-strategy|300](pattern-strategy.png)

> Definisce una famiglia di algoritmi, e li rende (tramite encapsulation) tra loro intercambiabili.

Ad esempio usato nel [sorting](https://github.com/lazywithclass/learning/tree/master/software-engineering/DesignPatterns/src/strategy/sort).

# Template

TODO ESPANDERE
