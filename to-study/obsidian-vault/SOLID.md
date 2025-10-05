---
cssclasses:
  - cornell-note
tags:
  - sweng
  - solid
  - italian
---

TODO!!!!!!!!!!!!!!!!
FINISH extracting
https://condor.depaul.edu/dmumaugh/OOT/Design-Principles/
https://ieeexplore.ieee.org/author/37291880700
https://en.wikipedia.org/wiki/Robert_C._Martin
https://web.archive.org/web/20150906155800/http://www.objectmentor.com/resources/articles/Principles_and_Patterns.pdf
https://www.youtube.com/results?search_query=barbara+liskov
https://www.youtube.com/watch?v=v-2yFMzxqwU
https://duckduckgo.com/?q=Barbara+Liskov%2C+%E2%80%9CData+Abstraction+and+Hierarchy%2C%E2%80%9D+SIGPLAN+Notices%2C+23%2C5+(May%2C+1988).&atb=v340-1&ia=web
https://se.inf.ethz.ch/~meyer/publications/old/dbc_chapter.pdf
https://en.wikipedia.org/wiki/SOLID#cite_note-11
https://vimeo.com/97514630
https://www.youtube.com/watch?v=TMuno5RZNeE
https://www.youtube.com/watch?v=BSaAMQVq01E

http://butunclebob.com/ArticleS.UncleBob.PrinciplesOfOod




```table-of-contents
```


> When considering whether a particular design is appropriate or not, one must not simply view the solution in isolation. One must view it in terms of the reasonable assumptions that will be made by the users of that design.

-- [Validity is not Intrinsic](cpp-report_engineering-notebook_liskov-substitution-principle.pdf)

## Single responsibility principle

[The Single Responsibility Principle - The Clean Code Blog](https://blog.cleancoder.com/uncle-bob/2014/05/08/SingleReponsibilityPrinciple.html)

> A class should have one, and only one, reason to change

> Why was it important to separate these two responsibilities into separate classes? Because each responsibility is an axis of change. 
  When the requirements change, that change will be manifest through a change in responsibility amongst the classes. If a class assumes more than one responsibility, then there will be more than one reason for it to change. If a class has more then one responsibility, then the responsibilities become coupled. Changes to one responsibility may impair or inhibit the class’ ability to meet the others. This kind of coupling leads to fragile designs that break in unexpected ways when changed.

"Each software module should have one and only one reason to change", but then "What defines a reason to change?".

"Incoraggia un'alta coesione interna (tutti i metodi hanno a che fare con lo stesso obiettivo) e un alto disaccoppiamento esterno, porta ad una buona modularizzazione"

"This principle is about people."

This is the reason we do not put SQL in JSPs. This is the reason we do not generate HTML in the modules that compute results. This is the reason that business rules should not know the database schema. This is the reason we separate concerns.

"Gather together the things that change for the same reasons. Separate those things that change for different reasons."

* the secret which I am guaranteeing for, and for which I manage evolution over time; or
* the single reason why I should change

["A good system should be high on cohesion and low on coupling"](https://www.enjoyalgorithms.com/blog/single-responsibility-principle-in-oops)

![img|300](https://cdn-images-1.medium.com/max/640/0*11cZSBOkR82nknwy.png)

### Benefici

* ridotto impatto a seguito di modifiche perche' ogni classe si concentra su uno specifico aspetto o funzionalita' del sistema
* mantenibilita' aumentata perche' posso intervenire su una classe, ad esempio con un refactor, senza che queste modifiche impattino su un'altra
* facilita il riutilizzo perche' siccome e' chiaro di cosa si occupano e non hanno un alto livello di accoppiamento con le altre classi, posso usarle anche in altri ambiti

### Related patterns

* [Decorator](Design%20Pattern.md#Decorator): per decentralizzare le varie aggiunte, ognuna in una classe decoratrice, invece di avere una god class
* [Strategy](Design%20Pattern.md#Strategy): per scegliere quale algoritmo usare, la strategia si occupa di un certo compito, ignorando cio' che ha attorno
* [Factory](Design%20Pattern.md#Factory): delego la creazione di ogni specifico oggetto

## Open-Closed principle

[Engineering Notebook columns for The C++ Report - The Open-Closed Principle](https://courses.cs.duke.edu/fall07/cps108/papers/ocp.pdf)

> The foundation for building code that is maintainable and reusable

-- [See Introduction of Engineering Notebook columns for The C++ Report - The Liskov Substitution Principle](cpp-report_engineering-notebook_liskov-substitution-principle.pdf)

> You should be able to extend a classes behavior, without modifying it

> When the creation of a derived class causes us to make changes to the base class, it often implies that the design is fault, indeed it violates the Open-Closed principle

Permette di ottenere:
* stabilita' grazie al fatto che non vengono modificate le classi
* mantenibilita' attraverso l'estensibilita'

[Dynamic binding](Java.md#Dynamic%20binding) e' un aspetto chiave di OOP, perche' permette di chiamare codice non ancora scritto: cioe' riconoscendo il tipo concreto dal tipo apparente solo a runtime, tengo aperta la possibilita' di future estensioni.
### Related patterns

* [Template](Design%20Pattern.md#Template): definisce lo scheletro di un algoritmo in una classe base, alcuni passaggi vengono lasciati da implementare alle sottoclassi, senza modificare la classe originale
* [Adapter - page 139](Design%20Pattern.md#Adapter%20-%20page%20139): riusare classi in nuovi contesti senza modificarle direttamente
* [Strategy](Design%20Pattern.md#Strategy): ogni  algoritmo e' incapsulato nella sua classe che implementa una interfaccia condivisa

## Liskov substitution principle

[Engineering Notebook columns for The C++ Report - The Liskov Substitution Principle](https://www.cs.utexas.edu/~downing/papers/LSP-1996.pdf)

> if you have a program that works correctly with a base class, then it should continue to work correctly if you replace the base class with any of its derived classes

`S` sottotipo di `T`:
* precondizioni dei metodi di `S` non devono essere piu' stringenti delle precondizioni dei metodi di `T`
* postcondizioni dei metodi di `S` non devono essere piu' larghe delle postcondizioni dei metodi di `T`

Voglio evitare che chi cita una classe e vede una possibilita' (metodo), si ritrovi con una classe figlia che questa possibilita' non ce l'ha.

If a function violates LSP then that function uses a reference to a base class, but must know about all the derivatives of that base class.

## Open-Closed Principle and Liskov Substitution Principle

<aside>OOD is-a relationship pertains to behavior</aside>

> In order for the LSP to hold, and with it the Open-Closed principle, all derivatives must conform to the behavior that clients expect of the base classes that they use.

Siamo interessati al comportamento dei moduli tra di loro, comportamento su cui gli utilizzatori dipendono.

## Design by Contract and Liskov Substituion Principle

In Design by Contract (Bertrand Meyer) methods declare preconditions and postconditions:
* preconditions must be true before method invocation
* postconditions are guaranteed by method invocation

The rule for these conditions, for derivatives, is:

> ...when redefining a routine in a derivative, you may only replace its precondition by a weaker one, and its postcondition by a stronger one.

So:
* when using an object through its base class $\rightarrow$ the client knows only the preconditions and postconditions of the base class $\rightarrow$ derived objects must not expect such clients to obey preconditions that are stronger that those required by the base class
  <u>They must accept anything that the base class accepts</u>
* derived classes must conform to all the postconditions of the base $\rightarrow$ their behaviours and outputs must not violate any of the contraints established for the base class; users of the base class must not be confused by the output of the derived class

## Interface segregation

[Engineering Notebook columns for The C++ Report - The Interface Segregation Principle](https://www.cs.utexas.edu/~downing/papers/ISP-1996.pdf)

>Clients should not be forced to depend on methods they do not use

> Make fine grained interfaces that are client specific

"Offer different views of the same type."

> "Polimorfismo e' un modo per esprimere di fronte alla stessa interfaccia comportamenti diversi. Grazie al polimorfismo possiamo mostrare lo stesso cosa nascondendo diversi come."

 -- Jessica Vecchia

Fa in modo che il client ottenga la dipendenza <span class="b">minima</span> dalla classe che vuole utilizzare, vuole utilizzare solo certi aspetti che la classe implementa, non tutti.

Questo principio permette di raggiungere un grado basso di accoppiamento tra gli oggetti. 

![interface-segregation|400](interface-segregation-example.png)

### Related patterns

* [Observer](Design%20Pattern.md#Observer): un solo metodo `update()` che e' l'unico interesse del Subject

## Dependency inversion

[Engineering Notebook columns for The C++ Report - The Dependency Inversion Principle](https://www.labri.fr/perso/clement/enseignements/ao/DIP.pdf)

> Depend on abstractions, not on concretions

I moduli di alto livello non dovrebbero dipendere dai moduli di basso livello: entrambi dovrebbero dipendere da astrazioni.
Le astrazioni non dovrebbero dipendere dai dettagli. Programmare verso le interfacce.

"Depend on stuff more concrete than me"

This is pure gold: https://blog.cleancoder.com/uncle-bob/2016/01/04/ALittleArchitecture.html
### Benefici

* testabilita' perche' posso mockare con semplificita' una interfaccia invece di portarmi dietro un intero albero di implementazioni
* flessibilita' perche' posso variare l'implementazione in base alle necessita', quindi ad esempio passare un database PostgreSQL a MySQL
* ridotto accoppiamento perche' dei cambiamenti nei moduli di basso livello non impattano i moduli di alto livello
### Related patterns

* [Factory method](Design%20Pattern.md#Factory%20method): definisce una interfaccia per la creazione di un oggetto, ma consente alle sottoclassi di decidere quale classe istanziare
* [Abstract factory](Design%20Pattern.md#Abstract%20factory): fornisce un'interfaccia per la creazione di famiglie di oggetti correlati o dipendenti senza specificare le loro classi concrete
* [Composite](Design%20Pattern.md#Composite): trattare uniformemente parti e insiemi, il client non sa se sta parlando con una parte o un insieme

## The Primeagean on it

<iframe width="560" height="315" src="https://www.youtube.com/embed/TT_RLWmIsbY?si=tsnE7M-qaeAjZ6Lr" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

## Esempi

### Dependency Inversion e Open Close

> Identificare gli aspetti della applicazione che cambiano e separarli da cio' che rimane fisso

In questo caso usiamo due volte lo [Strategy](Design%20Pattern.md#Strategy).

![strategy|300](di-oc-example.png)

Fin qua tutto ok.

![strategy-problem|300](di-oc-problem.png)

Il problema nasce quando si aggiunge `RubberDuck` e successivamente si aggiunge un metodo `fly()` a `Duck`: `RubberDuck` si ritrova a poter volare!

Override non e' una soluzione perche' mi ritrovo a dover implementare uno "stub" dentro `RubberDuck`, sto violando [Liskov substitution principle](SOLID.md#Liskov%20substitution%20principle): ho una sottoclasse che non sa fare qualcosa che la superclasse sa fare.

Definire diverse interfacce come `Quackable`, `Swimmable`, ..., con metodi di default e' meglio ma devo far implementare a `MallardDuck` e `RedheadDuck` le interfacce in base a cosa possono fare, ma mi porta a duplicazione.

Delego!
![pattern-strategy-ducks|500](di-oc-solution.png)
`Duck` contiene (aggrega) al suo interno come si comporta rispetto al fare "quack".

<aside>single responsibility</aside>

Quindi non devo avere piu' ragioni per cambiare: "cambia modo di fare quack o di volare?" se sono nella classe sono due motivi diversi per cambiare la classe.