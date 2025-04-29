---
cssclasses: 
tags:
  - databases
  - italian
---

Come costruire un database, come progettarlo.

![Attività di progettazione](attachments/Pasted%20image%2020250420190513.png)

## Modello ER

Entità sono rettangoli.\
Associazioni sono rombi, più importante il significato rispetto al suo nome, cioè quali entità associa.\
Per ogni relazione ci sono due cardinalità.

### Esempio

Si modelli un diagramma ER che descrive una realtà ospedaliera in cui i medici curano i pazienti 

![Schema ER di una realtà ospedaliera](attachments/Pasted%20image%2020250416210616.png)

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

In una N a N una associazione viene tradotta con una tabella con un identificativo per parte.\
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

![Esempio di entità debole](attachments/Pasted%20image%2020250422145718.png)

TODO prendi da appunti di lezione gli altri schemi, ad esempio dove msotra attributi sulle associazioni.

TODO mettere rimando al vincolo extra schema: non si puo' gestire con un elemento dello schema, verrebbe gestito da trigger.

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

## Progettazione logica

Da modello concettuale a modello logico.

TODO integra con il diagramma che avevi fatto e mettilo qua
TODO integra con gli esempi nel materiale del prof su Ariel

Partiamo da questo 

![Schema ER di una realtà ospedaliera](attachments/Pasted%20image%2020250416210616.png)

Chiavi primarie sono sottolineate con riga continua. TODO metterlo in latex.\
Attributi opzionali sono denotati da un $*$.

* Parto da una entità - $Medico$
* Scrivo i suoi attributi - $Medico(\underline{matricola},\ ruolo,\ cognome,\ nome,\ specialita')$

* Prossima entità - $Ambulatorio(\underline{numero}, telefono*, ubicazione)$

* Prossima entità (focus solo sugli attributi interessanti) - $Paziente(\underline{tessera\_sanitaria}, residenza, diagnosi)$
  Scegliendo $residenza$ come unica stringa allora renderò alcune query molto difficili da fare, dovrò fare il parsing, quindi dipende dal dominio; se invece voglio poter fare query allora li metto come attributi separati dell'entità<label class="sidenote-toggle sidenote-number"></label>
  <span class="sidenote">Gestione attributi composti</span>.
  Per un attributo multi-valore come $diagnosi$ la faccio diventare una entità. Che quindi diventa una associazione 1 a N, 1 perché l'attributo e' diventato entità debole.

  Quindi ottengo una nuova relazione - $Diagnosi(\underline{diagnosi,\ tessera\_sanitaria})$<label class="sidenote-toggle sidenote-number"></label>
  <span class="sidenote">1 a N: metto l'identificatore nel lato 1 che ho nel lato N</span>
  Con la seguente notazione specifico a quale chiave fa riferimento la chiave esterna: $Diagnosi.tessera\_sanitaria \rightarrow Paziente.tessera\_sanitaria$

* vedo se ci sono associazioni rimanenti
* parto da $Curano$, che diventa una nuova tabella perché ho bisogno di mantenere quale $Medico$ e' associato a quale $Paziente$: $Curano(\underline{medico,\ paziente})$, e quindi aggiungo le specifiche per la chiavi esterne\
  $Curano.medico \rightarrow Medico.matricola$\
  $Curano.paziente \rightarrow Paziente.tessera\_sanitaria$

* Prossima associazione $Visita$: $Visita(\underline{medico,\ paziente,\ ambulatorio,\ data\_visita})$, e quindi aggiungo le specifiche per la chiavi esterne\
  $Vistita.medico \rightarrow Medico.matricola$\
  $Visita.paziente \rightarrow Paziente.tessera\_sanitaria$\
  $Visita.ambulatorio \rightarrow Ambulatorio.numero$

Abbiamo sbagliato il modello concettuale, manca $data\_visita$ dentro $Visita$, perché altrimenti non avrei potuto avere una visita tra lo stesso medico, lo stesso paziente, nello stesso ambulatorio, il che e' assurdo. L'attributo $data\_visita$ e' parte della chiave quando voglio $Visita$ come relazione storica.

$Visita.ambulatorio$ non e' rappresentato come 0, come dovrebbe essere secondo la cardinalità, ma siccome fa parte della chiave deve esserci, quindi quando non c'e' semplicemente non appare in $Visita$. E' con i `LEFT JOIN` che vado a pescare questi record quando mi serve.\
In realtà sono sbagliate le `(1,N)` perché vorrebbe dire che ogni volta che aggiungo un $Medico$ devo subito assegnargli un $Paziente$ il che può non essere vero.

Nel caso di entità deboli faccio prima l'entità a cui sono associate.

Per convenienza e' possibile mettere un $id$ dove oltre agli identificatori naturali uso un identificatore "utile", perché cosi ad esempio le `JOIN` non sono un delirio di `ON` a causa dei molteplici attributi coinvolti nella chiave.\
E' una scelta fatta in fase di ristrutturazione.

Per una associazione con N entità devo prendere tutte le chiavi di ogni entità partecipante, per poi metterla nella nuova tabella.

### Gestione gerarchie

TODO mettere immagini da appunti del prof

#### Accorpamento su entità padre (verso l'alto)

Si può sempre fare, con ogni tipo di gerarchia.\
La gerarchia collassa in un'unica entità, che e' quella padre e:

* si aggiunge un tipo enumerativo che identifica le figlie, nella gerarchia totale tipo e' obbligatorio, nella gerarchia parziale tipo e' opzionale; occhio che nelle sovrapposte il tipo deve considerare tutte le possibili combinazioni (può diventare poco maneggevole)
* si aggiungono eventuali attributi delle entità figlie, che arrivano come opzionali

#### Eliminazione dell'entità padre (verso il basso)

Non e' praticabile con una gerarchia parziale.\
La gerarchia viene spostata verso il basso, tutti gli attributi del padre vengono dati ai figli.\
Se non sappiamo ad esempio se una persona e' fisica o giuridica dobbiamo fare una `UNION` per cercarla dappertutto.\

#### Mantenimento di tutte le entità

La gerarchia viene scomposta in associazioni binarie ($e'\_tipo$).\
I figli sono sempre entità deboli rispetto al padre.
