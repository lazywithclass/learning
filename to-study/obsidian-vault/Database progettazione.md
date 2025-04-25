---
cssclasses: []
tags:
  - databases
  - italian
---

Come costruire un database, come progettarlo.

![Attività di progettazione](Pasted%20image%2020250420190513.png)

## Modello ER

Entità sono rettangoli
Associazioni sono rombi, più importante il significato rispetto al suo nome, cioè quali entità associa
Per ogni relazione ci sono due cardinalità
### Esempio

Si modelli un diagramma ER che descrive una realtà ospedaliera in cui i medici curano i pazienti 

![Schema ER di una realtà ospedaliera](Pasted%20image%2020250416210616.png)

* quali sono le entità che voglio rappresentare? Le chiamo al singolare, sto descrivendo un singolo oggetto di quel tipo. Uso il rettangolo per comunicare che sto mostrando una entità
* metto solamente quegli attributi che sono presenti nella realtà che sto modellando, se parlo di pazienti non avrò `id`, ma avrò `tessera_sanitaria`
* porre attenzione agli attributi composti come ad esempio `residenza`: non voglio concatenare tutto in una stringa
* esprimo la cardinalità con `(x, y)` dove `x` e' la cardinalità minima, `y` la cardinalità massima
	* la cardinalità minima non può essere `N`
	* la cardinalità minima e' sempre meno della massima
	* la cardinalità va espressa per entrambe le direzioni della relazione
	* la cardinalità della relazione la si capisce guardando la cardinalità massima sui due lati della relazione (esempio se avessi `(0,N)` e `(1,N)` sarebbe una cardinalità molti a molti)

### Altri esempi

Seguono alcuni esempi con richieste differenti

| Esempio                                                                                                   | Medico - Paziente | Paziente - Medico | Tipo |
| --------------------------------------------------------------------------------------------------------- | ----------------- | ----------------- | ---- |
| Un medico cura non più di 1000 pazienti, e un paziente non può avere più di 10 medici curanti             | (0, 1000)         | (1, 10)           | N N  |
| Un medico cura almeno 10 e non più di 1000 pazienti, e un paziente non può avere più di un medico curante | (10, 1000)        | (1, 1)            | 1 N  |

In una N a N una associazione viene tradotta con una tabella con un identificativo per parte.
In una 1 a N la chiave esterna va nell'entità che ha 1 come cardinalità massima.

"Ogni medico ha un supervisore che e' un altro medico", produrra' due modi di leggere l'associazione:

* ha\_supervisore (1, 1)
* e'\_supervisore (0, N)

Ed e' una 1 a N.

"I medici lavorano in reparti all'interno di ospedali, ogni reparto ha un primario scelto tra i medici che vi lavorano"

Mettere `primario` come attributo di `Reparto` e' un errore, perché lo identificherei con la sua `matricola`, e quindi sarebbe un `Medico`, quindi modello come una associazione.

### Entità debole

Reparto da solo non e' in grado di descriversi univocamente.

La cardinalità minima e' sempre (1, 1).

![Esempio di entità debole](Pasted%20image%2020250422145718.png)


TODO prendi da appunti di lezione gli altri schemi, ad esempio dove msotra attributi sulle associazioni

TODO mettere rimando al vincolo extra schema: non si puo' gestire con un elemento dello schema, verrebbe gestito da trigger

## Problema della storicizzazione dei dati

Si tratta di scegliere se far si che la base di dati mostri la situazione attuale, o tenga uno storico di come si e' evoluta la situazione nel tempo.

TODO vedi l'esempio dagli appunti del prof su come cambia lo schema ER

## Gerarchie di generalizzazione

Vincoli che riguardano delle specifiche entità.

"Negli ospedali lavorano diverse figure professionali, tra le quali distinguiamo: medici, infermieri, dirigenti, personale amministrativo"

Obiettivo e' avere una entità che generalizzi le diverse figure.

La gerarchia ha due caratteristiche, che vanno sempre specificate (una per coppia):

* totalità e parzialità "a quali sotto classi può appartenere?"
	* totale: un elemento appartiene ad almeno una delle sottoclassi (l'unione delle sottoclassi ricostruisce la superclasse)
	* parziale: esistono individui non appartenente ad alcuna delle sottoclassi
* esclusività e sovrapponibilità
	* esclusività: un individuo e' collocabile solo in una delle sottoclassi, intersezione tra le sottocategorie e' $\emptyset$
	* sovrapponibile: un individuo può appartenere a più sottoclassi

Le sottoclassi possono non avere identificatore, nel caso questo sia presente nella superclasse.

Quando ci sono attributi che sono in comune tra le relazioni e' bene chiedersi se si e' in presenza di una possibile gerarchia di generalizzazione.
### Esempi

"Si sappia che un Dipendente può essere Medico, Dirigente, Infermiere, Amministrativo e Custode"

TODO aggiungi questo esempio

TODO prendi ER dagli appunti del prof

TODO prova a fare lo schema sui dati immobiliari proposto dal prof e dopo guarda la soluzione

## Esercizio su caso d'uso Events

La base di dati dovrà memorizzare:

- dati relativi a clienti distinti in persone fisiche e giuridiche 
- dati relativi agli eventi organizzati, tra i quali si distinguono banchetti e convegni 
- dati relativi ai partecipanti agli eventi con eventuali intolleranze (si tenga presente che non è possibile inserire dati anagrafici dei partecipanti se non il nome e il cognome). Si memorizzino gli accompagnatori (il partecipante A può essere accompagnato da B e C) sapendo che un partecipante può avere più accompagnatori, ma può accompagnare solo un partecipante. 
- dati relativi ai ristoranti che gestiscono i banchetti. Tra i ristoranti si distinguono quelli caratteristici 
- dati relativi ai menu serviti ai banchetti. Un menu è un insieme di portate classificate come antipasto, primo, secondo, dessert. Tra le portate si distinguono le specialità che possono essere offerte solo in ristoranti caratteristici. Il costo di un menu è la somma dei costi delle portate incluse nel menu

TODO rifallo da solo e controlla sia rispetto alla versione dell'anno scorso che rispetto a quella di questo anno

Come abbiamo proceduto a lezione:

* per ogni entità ci chiediamo quali sono gli identificatori, eventualmente pensando se siamo in presenza di una entità debole
* identificato cliente, come gerarchia di persona fisica e persona giuridica (T, E)
* identificato evento, come gerarchi di banchetto e convegno (P, S)
* pensiamo a come collegare le entità con le associazioni
* ogni volta che ottengo "gruppi" di entità mi chiedo anche li come collegarli con le associazioni