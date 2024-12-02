---
cssclasses:
  - cornell-note
tags:
  - software-engineering
  - italian
---





TODO!!!!!!!!!!!!!
continuare
https://www.baeldung.com/mockito-series


> TDD e' una metodologia di sviluppo software, non di testing

> TDD is an awareness of the gap between decision and feedback during programming, and techniques to control that gaps
-- Kent Beck

> Testing shows the presence, not the absence of bugs.
-- Dijkstra

> Write tests until fear is transformed into boredom

# Test

Always use `SUT` as variable name for the Subject Under Test, as there could be only one SUT for a single test.

IN test unitari mai avere due classi reali, sempre almeno una SUT che DEVE Essere mockata
si puo' non mockare
* enum
* lambda

# Red green refactor

ESPANDERE CON LA PARTE RELATIVA AL FORZARE UN TEST ROSSO

<aside>failure is progress</aside>

Ask myself: "What set of tests, when passed, will demonstrate the presence of code we are confident will compute as expected?"

The rhythm of TDD:
* quickly add a test
* run <span class="b">all</span> tests and see the new one fail
* make a little change
* run <span class="b">all</span> tests and se them all succeed
* refactor to remove duplication

If in need to have a test go red use the triangulation technique, for example suppose the first expectation was the only one then we add the second:

```java linenos:1
public vid testEquality() {
  assertTrue(new Dollar(5).equals(new Dollar(5)));
  assertTrue(new Dollar(5).equals(new Dollar(6))); // triangulation
}
```

# Test double

[Test double](http://xunitpatterns.com/Test%20Double.html) - controfigura per il testing: si mette al posto di DOC (dependent-on component) per testare in isolamento il SUT (system under test); utile quando DOC:
* non esiste
* fornisce dati non deterministici / non prevedibili
* presentare situazioni difficilmente riproducibili (trasmissione, memoria, ...)
* e' lento
* potrebbe introdurre errori che non voglio considerare mentre testo SUT

ESPANDERE
https://www.baeldung.com/spock-stub-mock-spy
https://martinfowler.com/articles/mocksArentStubs.html

## Mock / Stub

Crea un oggetto. Dichiaro cosa voglio che questo oggetto sappia fare.

Instrumentano il DOC, instrumentati per essere interrogabili in merito a cosa gli e' successo: "chi ti ha chiamato?", "in che ordine?", "quante volte?", ...

```java linenos:1
when(mockedObj.methodName(args)).thenXXX(values); // usare questo
```

Per verificare quante volte un metodo viene chiamato

```java linenos:1
verify(mockedClass, times(1)).methodName(args)
// oppure
ArgumentCaptor<Person> arg = ArgumentCaptor.forClass(Person.class);
verify(mock).doSomething(arg.capture());
assertEquals("John", arg.getValue().getName());
```

Per evitare di "consumare l'iteratore" si puo' utilizzare questo metodo di utilita':

```java linenos:1
public static <T> void whenIterated(Iterable<T> p, T... d) {
    when(p.iterator())
      .thenAnswer((Answer<Iterator<T>>) invocation -> List.of(d).iterator());
}

```

Per mockare un costruttore 

```java linenos:1
// si puo' passare a mockConstruction una serie di parametri per mockare
// ad esempio eventuali altri metodi che vengono usati nel costruttore di Tavolo
try (var mocked = Mockito.mockConstruction(Tavolo.class)) {
  Partita p = new Partita();
  Card c = mock();
  Tavolo t = mocked.constructed().getFirst();
  // ...
}

```

## Spy

Wrappa un oggetto reale. Dichiaro cosa voglio che non sappia fare (svuoto).

Se il soggetto e' l'oggetto under test allora
> stub : spy = riempire : svuotare

Meglio partire dal vuoto, cioe' stub.

# Assertions

Ricordati che 

* `AssertionsForClassTypes` e' per 
* ESPANDERE

# Testare metodi privati

Sono testati indirettamente, a fronte di chiamate dall'esterno.
Voglio poter cambiare i metodi privati senza troppo sforzo, in modo da fare refactor senza incorrere in grossi attriti.

Usare `extracting` quando si vuole testare una proprieta' privata, e `AssertionsForClassTypes.assertThat(player).extracting("personalDeck", as(LIST)).containsExactly(Card.of("1B"), Card.of("2B"));` quando si sta testando una classe che implementa interfacce, perche' altrimenti l'`assertThat` "solito" non riesce a estrarre la proprieta'.

# Esporre variabili d'istanza / metodi

```java linenos:1
var m = Game.class.getDeclaredMethod("distributeInitialCards");  
m.setAccessible(true);  
m.invoke(game);
```

Da farsi solo in test, perche' altrimenti a causa della reflection puo' rompere tutte le astrazioni.


CsVSource(textBlock = """
    '4B,5B',0
    ...
""")
voi testGetPoints(String cards, int expectedPoints) {

}

CsVSource(delimiter="|", textBlock = """
    '4B,5B' | 0
    ...
""")
voi testGetPoints(String cards, int expectedPoints) {

}
Viene molto utile avere un TestUtils.cardsFrom che crea una `List<Card>` da `cards`

Perche' occhio che il codice duplicato e' anche dentro i test, non solo nel sorgente!

# Patterns

## Isolation

Good tests are written in isolation, so if one fails the rest will continue as if nothing happened: the idea is not to pollute a global state from which tests take their fixtures.

One broken test $\rightarrow$ one problem.
Two broken tests $\rightarrow$ two problems.

If tests are written in isolation then the order in which they're run does not matter.

## Test list

Offload your brain list into a written one, on paper, jot down critical issues, pain points, etc... ; you don't want to put the list into tests right away, always follow the "climber rule": out of four among feet and hands always have at least three attached to the wall.

## Assert first

Write the assert first and then work your way upwards through the test.
This approach allows to concentrate on the goal and force the preconditions to come out almost on their own.

## Data

Make use of data to tell a clearer story, make your intentions evident. 
For example split whole numbers in elementary operations to make the reader aware of "where did that 372.68 come from?".


# Gestione situazioni critiche

ESPANDERE DA LEZIONE

## Mi sono dimenticato di aprire una feature con gitflow (senza aver committato)

Posso aprirla senza problemi perche' e' effettivamente la creazione di un branch.

## Mi sono dimenticato di committare ROSSO

Basta committare prima test ROSSO e poi implementazione VERDE

## Committato ROSSO ma mi sono accorto che il test non era scritto giusto

Modifico il test in regime di rosso.

## Ho committato il ROSSO ma mi accorgo che prima era necessario fare un'altra feature (sbagliato ordine)

## Ho committato ROSSO ma mi accorgo che e' necessario fare REFACTORING prima






# Ariadne's thread

Keep a todo list of things that need to be addressed while programming. So that you don't lose yourself in the sea of changes.



SISTEMARE!!!!!!!!!!!!!!!!!!!!!


Per ogni test ci deve essere una sola esecuzione del metodo che sto testando, cosi che quando qualcosa fallisce so esattamente dove andare a guardare, e non ci sono test che "falliscono a meta'".


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




Evitare regressioni facendo andare anche il test globale prima di ogni commit