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

All page numbers (i.e. pag.) are from [GoF's](https://en.wikipedia.org/wiki/Design_Patterns).

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

![](mvc.png)

L'MVC e' un set di pattern che collaborano assieme nello stesso design: Composite (view), Strategy (quale Controller iniettare, il Controller e' lo Strategy per la View), Observer (il Model e' observable e View e Controller sono observer).

> The best way to think of MVC is as set of principles including the separation of presentation from domain logic and synchronizing presentation state through events (the observer pattern)
-- Martin Fowler

About "handling the triggering of synchronization between screen state and session state", MVC does it by making updates on the model and then relying of the observer relationship to update the views that are observing that model.

Il problema e' che c'e' una circolarita' di comunicazione, quindi si potrebbe preferire per questo l'MVP.

## Model

Un unico modello che rappresenta lo stato dell'applicazione

## View

La View si aggiorna ogni volta che il Model cambia stato.
Tra Model e View c'e' una relazione tipo Observer.

## Controller

Ascolta gli eventi dell'interfaccia, a fronte del quale eventualmente puo' richiedere una modifica dello stato.
Implementa quindi la Strategy di gestione di un evento. A seconda del Controller che associo ho un comportamento diverso.

# Model View Presenter

https://martinfowler.com/eaaDev/uiArchs.html#Model-view-presentermvp

![mvc-mvp|400](mvc-mvp.png)

Viene rimossa la dipendenza circolare.
Molto piu' facile da testare.

Il Presenter ha un riferimento a View e Model, agendo da middle man.

Per ogni View c'e' un Presenter.
Si puo' intendere quindi 1 classe View e 1 istanza Presenter, 1 classe View e 1 classe Presenter, tenendo presente i principi di buona scrittura del software.

Vedi [Modalita' - pag 298](Design%20Pattern.md#Modalita'%20-%20pag%20298) di Observer.

## Diverse relazioni

### Presenter $\rightarrow$ Model

Il Presenter viene aggiunto come ascoltatore di cambi di stato sul Model.

```java
model.addObserver(presenter)
```

### Presenter $\rightarrow$ View

Il presente viene aggiunto come gestore degli eventi sulle View

```java
view.addHandler(presenter)
```

## Suddivisione tra Model e State

Puo' essere una buona idea operare una divisione di questo tipo:

State: rappresenta lo stato all'interno del pattern.
Model: e' [Class Adapter](Design%20Pattern.md#Class%20Adapter) dello State aggiungendo capacita' di [Observer - pag 293](Design%20Pattern.md#Observer%20-%20pag%20293).

## Interface Segregation sul Presenter

Non sempre voglio che tutti i Presenter implementino lo stesso `Presenter`, ricordarsi di specializzare sfruttando l'eredita' multipla sulle interfacce.

## Da dove si parte nell'implementazione?

Si puo' approcciare da diversi lati, ad esempio con diversi sotto-team che si avvicinano assieme al risultato.

O inizio dal Presenter (top down). Piuttosto che dal Model (bottom up).
In questo senso una buona strategia puo' essere partire dal Model delineando in una interfaccia quali sono i metodi che verranno implementati.

TODO RIMANDO A IMMUTABILITA'
Favorire un design con immutabilita' nel Model, per evitare deep copies.

## Gestione degli errori

Nel Presenter (ma...).
Importante che una volta validato il dato questa validazione non sia fatta, se si usa il contract based allora si puo' costruire un tipo che assume per costruzione la validazione del dato, es:

```java linenos:1
public record TimeOfRun(@NotNull String name, @NotNull Double time) {
  public TimeOfRun {
    if (name.isBlank) throw
    if (time < 0) throw
  }
}
```

E' il Presenter che istruisce la vista sul suo stato di errore.

Ma anche nel Model (...!).
Dipende da dove ha senso, ad esempio se ho un Model che gestisce due manche di sciatori, e mi arriva nel Model un nome di uno sciatore non presente nella prima manche allora questo lo posso gestire solo nel Model.

## Deregistrarsi dagli observer

Puo' avere senso ad un certo stato di avanzamento nell'applicazione per qualcuno di deregistrarsi.

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

# Observer - pag 293

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

## Modalita' - pag 298 

E' possibile avere due modalita' per avvertire gli observer di un cambiamento: pull e push

```java linenos:1
@Override
public void setTemp(double temp) {
    if (this.temp != temp) {
        this.temp = temp;
        notifyObservers();
    }
}

@Override
public void notifyObservers() {
    for (Observer<Double> obs : observers) {
        obs.update(this, temp); // qui si rendono disponibili entrambe le modalita'
    }
}
```

Stato semplice $\rightarrow$ push.
Stato complesso o parzialmente rilevante $\rightarrow$ pull.

### Modalita' pull

Il subject fornisce metodi (getter) per accedere al proprio stato, lasciando agli observer il compito di scegliere cosa consultare.
Vantaggi: Più flessibile, perché ogni observer può decidere cosa gli serve. Ideale per stati complessi.

### Modalita' push

Il subject invia lo stato agli observer.
Vantaggi: semplice da implementare se lo stato è compatto e se gli observer richiedono tutte le informazioni.
Svantaggi: inefficiente se lo stato è complesso e gli observer sono interessati solo a una parte, o se vogliono solo essere notificati del cambiamento senza conoscerne i dettagli.

```java linenos:1
@Override
public void update(@Nullable Observable<Double> subject, @NotNull Double state) {
    view.setValue(String.format("%.2f", strategy.convertFromCelsius(state)));
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
