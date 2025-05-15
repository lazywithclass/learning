---
cssclasses: 
tags: []
---
Come i dati vengono memorizzati.

Il buffer permette la gestione dei dati in memoria principale, applica strategie per minimizzare il trasferimento dei dati da elaborare da e verso la memoria secondaria.\
Il buffer organizzato in pagine, ogni pagina ha un certo numero di blocchi.

Buffer organizzato in pagine, ogni pagina ha dei blocchi. Il blocco su disco memorizza i record.

Ogni blocco memorizza più record.

## Fattore di blocco

"Quanti record stanno nello specifico blocco?"

$$
\begin{align}
&\ bfr = \lfloor{\frac{B}{R}}  \rfloor \newline
\end{align}
$$

Se un record non sta interamente in un blocco e' possibile che una parte del blocco rimanga inutilizzata.\
Da una previsione media.

Dove 

* $B$ e' la dimensione del blocco
* $R$ e' la dimensione media del record

"Quanto spazio occupa una tabella?"

```language-sql
CREATE TABLE country (
	id serial,
	iso3 char(3) PRIMARY KEY,
	name varchar(50) NOT NULL UNIQUE,
	population integer,
	image bytea
)
```

Il tipo di dato `char` usa comunque tutto lo spazio specificato, il `varchar` no, usa ciò che inserisco.

* `char`: 4 byte x char -> 12 byte
* `integer`: 8 byte
* `bytea`: non posso saperlo, viene memorizzato in un altro blocco, e referenziato nel blocco dove ci sono gli altri campi

Vincoli tipo `PRIMARY KEY` e `UNIQUE` sono di particolare interesse, sono indicizzati, quindi ad accesso ottimizzato, per fare in modo che la ricerca sia particolarmente performante.

Il costo di ricerca all'interno di un blocco e' irrisorio.


TODO Controllare se e' corretta la divisione strutture primarie e secondarie

## Struttura primaria dei file

TODO riempi da slide

"Come finiscono i dati nei blocchi?"; criterio con cui disponiamo i record nei blocchi:

 * sequenziale (array)
 * calcolato (hash)
 * albero (gli indici finiscono in questa categoria)

### Sequenziale

I record finiscono uno dopo l'altro in blocchi sequenziali.

#### Heap

Riempio con i dati man mano che arrivano, senza ordinarli.

Inserire e' facile (trovo l'ultimo, vedo se posso mettere il record, altrimenti prendo il prossimo blocco e lo metto li), recuperare e' meno immediato.\
Prima di inserire devo verificare se la chiave non e' già presente, il che richiedere una ricerca lineare.

Un affiancamento di strutture secondarie permette di velocizzare i passaggi critici.

#### Array

"Bello ma scarsamente fattibile"

#### Struttura sequenziale ordinata

Ordinamento di un campo detto pseudo-chiave

### Hash

La funzione di hashing deve garantire una buona equi distribuzione dei dati.

### Indici primari

TODO inserire immagine da slide

Sono ordinati, quindi ricerche puntuali o per intervallo sono efficienti. 

Sono sempre sparsi, perché l'albero non contiene mai tutte le chiavi.

Alberi utilizzati per memorizzare dati.

Ho $(k,\ p)$ dove $k$ e' valore della chiave e $p$ e' il puntatore alla posizione di memoria del record. 

## Strutture secondarie

Ci permettono di raggiungere i dati in modo efficiente, indici (alberi). Es. `CREATE INDEX`.

Possiamo usarle anche per memorizzare dati.

### Indici secondari

TODO inserire immagine da slide

Sono ordinati, quindi ricerche puntuali o per intervallo sono efficienti. 

Sono sempre densi, perché l'albero contiene sempre tutte le chiavi.

## Esercizi indici primari

$R = 100B$, $Blocco = 1024B$, file con 30000 record

Quante voci conterrà l'indice?

* calcoliamo il blocking factor (cioè quanti record sono memorizzati in ogni blocco): floor(1024/100) = 10
* calcolo quanti blocchi sono occupati dai record della tabella: 30000 / 10 = 3000
* quante voci di indice primario mi servono per gestire 3000 blocchi? In un indice primario ho bisogno di una chiave per ogni blocco, che punta al primo record del blocco, quindi posso gestire 3000 blocchi con altrettante voci di indice

---

* i valori della pseudo-chiave dell'indice occupano $3B$
* il puntatore al blocco e' un indirizzo di $6B$

Quanti blocchi occupa l'indice?

* ogni record dell'indice occupa 3+9 = 9B (dimensione del record dell'indice)
* bf = floor(B/R) - floor(1024 / 9) = 113 record
* quanti blocchi mi servono (considero che l'indice contiene 3000 voci)? 3000 / 113 = 26.5 quindi 27

## Esercizi indici secondari

TODO prendi da risoluzioni del prof

R = 100B, B = 1024B, 30000 record

Quanti accessi devo fare prima di trovare il valore che cerco? Scansione di tutti i record.