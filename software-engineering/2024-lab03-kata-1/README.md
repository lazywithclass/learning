# CORSO INGEGNERIA DEL SOFTWARE A.A. 2024/25

## LABORATORIO 3

* TEAMMATE 1: <Cognome> <Nome> <matricola>
* TEAMMATE 2: <Cognome> <Nome> <matricola>

Ogni coppia di studenti procede a effettuare il **fork** di questo repository.
L'utente che ha effettuato il fork modifica questo README inserendo le opportune **informazioni sui
membri del team** seguendo lo schema sopra riportato.
Inoltre, concede i permessi di scrittura al proprio compagno di team e i **permessi di lettura** ai
docenti (`carlo.bellettini` e `mattia.monga`).


### Processo

Una volta effettuato il **clone** del repository, il gruppo implementa secondo la *metodologia TDD*
le specifiche riportate di seguito; in maggior dettaglio, ripete i passi seguenti fino ad aver implementato tutte le funzionalità richieste:

* creare un nuovo *branch* per la funzionalità corrente attraverso l'esecuzione del comando `git flow feature start`,
* implementare un test per le funzionalità volute;
* verificare che **il codice compili correttamente**, ma l'**esecuzione del test fallisca**;
  solo a questo punto effettuare un *commit* iniziando il messaggio di commit con la stringa `ROSSO:`,
* aggiungere la minima implementazione necessaria a realizzare la funzionalità, in modo che **il
  test esegua con successo**; solo a questo punto
  effettua un *commit* iniziando il messaggio di commit con la stringa `VERDE:`,
* procedere, se necessario, al **refactoring** del codice, accertandosi che le modifiche non
  comportino il fallimento di alcun test; solo in questo caso fa seguire a ogni
  passo un *commit* iniziando il messaggio di commit con la stringa `REFACTORING:`,
* se la feature non è completata ritornare al secondo punto: "implementare un test..."
* chiudere la feature completata eseguendo il *merge* del *branch* per la funzionalità sviluppata all'interno del *branch develop*
  attraverso il comando `git flow feature finish`,
* **solo in fase di rilascio** (vedi nel seguto le specifiche), esegue una *release* all'interno del *branch main* attraverso il comando `git flow release start` e successivamente `git flow release finish`,
* effettua un *push* (di tutti i *branch*) con `git push origin --all` e poi `git push origin --tags`.



Al termine del laboratorio effettua un ultimo *push* e **verifica su
gitlab.di.unimi.it** che ci sia la completa traccia di *commit* effettuati. Si
suggerisce di eseguire i test non soltanto con Idea, ma anche eseguendo il
comando `gradle test` da riga di comando.

### Refactoring

Prestare attenzione ai seguenti [code smell](https://it.wikipedia.org/wiki/Code_smell):

* codice duplicato, o pressoché uguale, in diverse sezioni,
* troppi livelli di indentazione (es., > 2),
* metodo troppo lungo (es., > 10 linee di codice),
* lunghe sequenze di *if*-*else* o *switch case*,
* nomi di classi/metodi/campi/variabili non significativi,
* troppi attributi per classe (es., > 2),
* uso di metodi *setter*/*getter* per modificare/accedere campi privati.

In presenza di *code smell*, il gruppo effettua alcuni passi di *refactoring*,
per ottenere codice più *leggibile* e *manutenibile*.

Di seguito si accenna ad alcune possibili azioni di refactoring.
Accanto al tipo di refactoring è stata elencata una delle [Refactor Actions](https://www.baeldung.com/intellij-refactoring) di Idea (se presente) che la coppia può usare.

* Rename Method [[fowler]](http://refactoring.com/catalog/renameMethod.html) [[guru]](https://refactoring.guru/rename-method) (oppure Field, o Variable): *Rename*,
* Replace Magic Number with Symbolic Constant [[fowler]](http://refactoring.com/catalog/replaceMagicNumberWithSymbolicConstant.html) [[guru]](https://refactoring.guru/replace-magic-number-with-symbolic-constant): *Extract Constant*,
* Extract Variable [[fowler]](http://refactoring.com/catalog/extractVariable.html) [[guru]](https://refactoring.guru/extract-variable: *Extract  Variable*,  *Extract Field*,
* Extract Method [[fowler]](http://refactoring.com/catalog/extractMethod.html) [[guru]](https://refactoring.guru/extract-method): *Extract Method*,
* Extract Class [[fowler]](http://refactoring.com/catalog/extractClass.html) [[guru]](https://refactoring.guru/extract-class): *Extract Class*,
* Replace Array with Object [[fowler]](http://refactoring.com/catalog/replaceArrayWithObject.html) [[guru]](https://refactoring.guru/replace-array-with-object).

Una lista di possibili passi di refactoring è accessibile dai seguenti cataloghi:
[fowler](https://refactoring.com/catalog/) e [guru](https://refactoring.guru/refactoring/techniques).



## Specifica dei requisiti

Le funzionalità  di seguito elencate vanno implementate **nell'ordine in cui sono presentate**. Si suggerisce  di prendere visione di una funzionalità  per volta (procedendo subito al ciclo di implementazione della medesima) in modo da non farsi influenzare dalle specifiche successive circa le scelte di progetto.

Obiettivo dell'esercizio è la creazione di un semplice interprete [Forth](https://en.wikipedia.org/wiki/Forth_(programming_language)). I programmi Forth sono stringhe, la cui interpretazione è guidata da una struttura dati Last-In-First-Out (stack).

Un interprete Forth deve avere un metodo `input` che permette di resettare lo stack e trasmettere l'intero programma da eseguire (dati e istruzioni).
Una rappresentazione testuale dello stack può essere ottenuta grazie al metodo `toString`.

* Se non ha ricevuto nessun input (o una stringa vuota) il metodo `toString` riporta uno stack vuoto `"<- Top"`.
```java
input("") produce uno stack vuoto che toString riporta come "<- Top"
```

* La ricezione via input di un programma contenente solo numeri (separati da uno spazio), li impila sullo stack.
```java
input("1") produce uno stack che toString riporta come "1 <- Top"
input("1 2") produce uno stack che toString riporta come "1 2 <- Top"
```

* Il separatore tra un elemento e l'altro nella stringa dell'input non è garantito essere un singolo spazio, ma può essere un qualunque numero di spazi e newline.
```java
input("1 2") produce ... "1 2 <- Top"
input("1\n2") produce ... "1 2 <- Top"
input("1   2 \n3") produce ... "1 2 3 <- Top"
```

* L'interprete è in grado di eseguire le somme: l'operatore `+`  sostituisce i due operandi in cima allo stack con la loro somma.
```java
input("1 2 +") produce ... "3 <- Top"
input("1 2 + 5 +") produce ... "8 <- Top"
```

* L'interprete considera corretto l'input solo se tutti i token (numeri, operatori, parole chiave) sono separati da almeno un separatore. In caso contrario solleva un'eccezione IllegalArgumentException con messaggio "Token error '<token>'".
```java
input("1 2+") solleva una eccezione IllegalArgumentException con messaggio "Token error '2+'"
input("1 2 +5 +") solleva una eccezione IllegalArgumentException con messaggio "Token error '+5'"
```

* Se non c'è un numero sufficiente di operandi sullo stack, si ottiene un'eccezione IllegalArgumentException con messaggio "Stack Underflow".
```java
input("1 +") solleva una eccezione IllegalArgumentException con messaggio "Stack Underflow"
```

* L'interprete è in grado di eseguire le moltiplicazioni: l'operatore  `*` sostituisce i due operandi in cima allo stack con il loro prodotto. In caso di numero di operandi insufficienti, solleva eccezione come nel test precedente.
```java
input("1 2 *") produce ... "2 <- Top"
input("1 2 * 5 *") produce ... "10 <- Top"
```

* In maniera analoga l'interprete è in grado di eseguire le sottrazioni e le divisioni intere (operatori `-` e `/`)
```java
input("1 2 -") produce ... "-1 <- Top"
input("1 2 /") produce ... "0 <- Top"
```

* A questo punto rilasciare la **prima release** del vostro progetto di nome "v1.0" (tramite git flow release...)

* Il comando "dup" duplica la cima dello stack.
```java
input("1 2 3 dup") produce ... "1 2 3 3 <- Top"
```

* Il comando "swap" scambia due operandi sulla cima dello stack
```java
input("1 2 3 swap") produce ... "1 3 2 <- Top"
```

* Il comando "drop" cancella il dato sulla cima dello stack.
```java
input("1 2 3 drop") produce ... "1 2 <- Top"
```

* Verificare (dovrebbe essere un VERDE DIRETTO) che comandi e operazioni siano combinabili a piacere
```java
input("1 2 + 3 * 4 dup 5 + drop swap") produce ... "4 9 <- Top"
input("1 2 + 3 * drop swap") solleva eccezione
```

* A questo punto rilasciare la **seconda release** del vostro progetto di nome "v2.0"

* L'interprete accetta anche la definizione di nuovi comandi (*word* nel gergo di Forth). Per farlo bisogna usare il comando `:`  che denota l'inizio della definizione (la fine verrà indicata da un `;`). La prima parola della definizione corrisponde al nome del nuovo comando.

```java
// definizione (e successivo uso) del nuovo comando "raddoppia"
input(": raddoppia 2 * ; 5 raddoppia dup raddoppia") produce ... "10 20 <- Top"
```

* Controllare che la presenza in input di una word non definita sollevi una eccezione `IllegalArgumentException` con messaggio `"Token error '<word>'"` (probabile VERDE DIRETTO).

```java
input("pippo") solleva una eccezione "Token error 'pippo'"
input("1 2 pippo") solleva una eccezione "Token error 'pippo'"
input("1 : raddoppia 2 * ; raddoppi raddoppia") solleva una eccezione "Token error 'raddoppi'"
```

* A questo punto avete finito e potete rilasciare la **terza release** del vostro progetto di nome "v3.0"
