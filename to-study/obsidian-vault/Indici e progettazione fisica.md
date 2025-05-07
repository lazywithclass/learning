---
cssclasses: 
tags: []
---
Come i dati vengono memorizzati.

Il buffer permette la gestione dei dati in memoria principale, applica strategie per minimizzare il trasferimento dei dati da elaborare da e verso la memoria secondaria.\
Il buffer organizzato in pagine, ogni pagina ha un certo numero di blocchi.

Ogni blocco memorizza più record.

## Fattore di blocco

"Quanti record stanno nello specifico blocco?"


$$
\begin{align}
&\ bfr = \lfloor{\frac{B}{R}}  \rfloor \newline
\end{align}
$$
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

## Struttura primaria dei file

TODO riempi da slide

Criterio con cui disponiamo i record nei blocchi:

 * sequenziale (array)
 * calcolato (hash)
 * albero

### Sequenziale

#### Heap

Riempio con i dati man mano che arrivano.

Inserire e' facile (trovo l'ultimo, vedo se posso mettere il record, altrimenti prendo il prossimo blocco e lo metto li), recuperare e' meno immediato.\
Prima di inserire devo verificare se la chiave non e' già presente, il che richiedere una ricerca lineare.

Un affiancamento di strutture secondarie permette di velocizzare i passaggi critici.