---
cssclasses:
  - cornell-note
tags:
  - databases
  - italian
---

# Link utili

[Algebra relazionale](Algebra%20relazionale.md)
[SQL](SQL.md)

[Ambiente di esecuzione query](https://github.com/lazywithclass/learning/tree/master/postgresql)

# Dati strutturati

Sfide nella gestione dei dati:

* duplicazione
* violazione dell'integrità
* errori di inserimento
* incapacità di controllare il tipo di dato
* difficoltà di collegamento tra file
* controllo dell'accesso
* valori nulli - alcuni valori non possono mancare

Tutti questi vengono gestiti dai DBMS.

Vincoli - leggi che voglio applicare per mantenere l'integrità dei dati

$$
\begin{align}
&\ movie(id, title, year, length) \\
&\ genre(id, name) \\
&\ movie\_genre(id\_movie, id\_genre)
\end{align}
$$

In realtà $genre.id$ e' deleterio perché per come e' strutturata la tabella non serve.

$$
\begin{align}
&\ movie(id, title, year, length) \newline
&\ genre(name) \newline
&\ movie\_genre(id\_movie, name\_genre)
\end{align}
$$

La tabella $genre$ e' comunque utile perché consente di sapere rapidamente quali sono i generi presenti nella base dati

La struttura delle tabelle deve cambiare solo quando cambiano i requisiti. <label class="sidenote-toggle sidenote-number"></label>
<span class="sidenote">Rigidita' dei database.</span>

## Case study: film associati a recensioni

$$
\begin{align}
&\ reviewer(name) \newline
&\ rating(score, scale, description, review\_date, reviewer\_name, id\_movie) \newline
\end{align}
$$

Se c'e' un rating allora vuol dire che c'e' uno e un solo film, per via del fatto che c'e' $id\_movie$ come chiave esterna. 

# Caratteristiche

DDL - Data Definition Language - lavora sullo schema
DML - Data Manipulation Language - lavora sui dati

Indipendenza - astrazione di come i dati vengono memorizzati su disco, che ci consente di disinteressarci di come sono fisicamente distribuiti

DQL - Data Query Language - ogni utente ha una vista dei dati che e' di interesse per quello specifico utente

Controllo della concorrenza - condivisione magari sugli stessi dati o addirittura sullo stesso record (lock, transazioni)

DCL - Data Control Language - controllo degli accessi

Database sono i dati, DBMS e' il software.

# Definizioni

DBMS - sistema software in grado di gestire collezioni di dati che siano grandi, condivise, persistenti assicurando la loro affidabilità e privatezza, deve essere efficiente ed efficace
Una base di dati (o database) e' una collezione di dati gestita da un DBMS

![Architettura a tre livelli](Pasted%20image%2020250401210255.png)

# Modello relazionale

<span class="sidenote">Definizione di tabella</span>
Basato sul concetto di relazione matematica <label class="sidenote-toggle sidenote-number"></label>, intesa come sottoinsieme del prodotto cartesiano fra due o più insiemi di dati detti domini
$$
\begin{align}
&\ D1 = \{cane, gatto\}; D2 = \{ bianco, nero, marrone \} \newline
\end{align}
$$
Dove $D1$, $D2$ sono domini: cioè possibili valori che può assumere un attributo che usa questo dominio.

Posso definire una relazione $R$
$$
\begin{align}
&\ animale(tipo, colore) \newline
\end{align}
$$
che avrà $6$ elementi ($2*3$), e che sara' espressa come
$$
\begin{align}
&\ R \subset D1 \times D2 \newline
\end{align}
$$
Posso scrivere
$$
\begin{align}
&\ R(D1, D2, D3) \newline
&\ t(d1, d2, d3) \newline
\end{align}
$$
dove nella prima riga ho la relazione con i domini, nella seconda riga ho un esempio di tupla, dove $d_1 \in D_1$, $d_2 \in D_2$, $d_3 \in D_3$. Notare come non serve dare un nome a $d_1$, ma e' sicuramente utile farlo, quindi con $t[k]$ voglio puntare al valore $k$ nella tupla $t$.

## Schema di una base di dati

Insiemi degli schemi di tutte le relazioni che lo costituiscono, ogni relazione ha poi il suo schema:
$$
\begin{align}
&\ BD = \{ R_1(X_1), R_2(X_2), ..., R_n(X_n)\}   \newline
&\ X_1 = \{ A_1, A_2, ..., A_n \}   \newline
\end{align}
$$
Dove $X_1$ fino ad arrivare ad $X_n$ sono gli schema (o insiemi di attributi).
<label class="sidenote-toggle sidenote-number"></label>
<span class="sidenote">Lo schema non cambia se cambiano i requisiti, l'istanza si e anche spesso</span>
Istanza della base di dati e' l'insieme dei record nelle relazioni.

<label class="sidenote-toggle sidenote-number"></label>
<span class="sidenote">Estensionale ed intensionale</span>
La parte estensionale di una tabella sono i suoi dati. La parte intensionale e' il suo schema.

Per ogni tabella esiste sempre un identificatore, al peggio e' composto da tutti i valori degli attributi che definiscono la tabella.

Grado di relazione: quanti attributi sono definiti sulla relazione
Cardinalità di una relazione: quante tuple ci sono nella relazione

## Vincoli

Caratteristiche che i dati devono avere per poter essere ammessi.
Predicato il cui valore di verità e' verificato all'inserimento di un possibile record nella relazione.

* intra-relazionali: all'interno della stessa relazione
* inter-relazionali: tra diverse relazioni

Avere un tipo di valore `DATE` già fornisce vincoli su come deve presentarsi il dato. 
### Vincolo di `NULL` e `NOT NULL`

Consentire l'inserimento di `NULL` e' il default.

Ad esempio

$$
\begin{align}
&\ rating(check\_date, source, movie, scale,votes, score) \newline
\end{align}
$$
Potrebbe avere come vincoli di integrità
$$
\begin{align}
&\ check(score \geq 0\ AND\ score \leq scale) \newline
&\ check(scale\ in\ (5, 10, 100)) \newline
\end{align}
$$
### Vincoli di chiave

Valori che referenziano valori identificativi referenziati altrove.
#### Superchiave

Una qualsiasi combinazione di attributi che garantisce l'univocità dei valori nelle ennuple della relazione.
Data $K$ superchiave della relazione $R$, non esistono due tuple $t_1$, $t_2$ in $R$ per le quali $t_1[K] = t_2[K]$.

La seguente relazione
$$
\begin{align}
&\ movie(id, title, year, length, budget, plot) \newline
\end{align}
$$
 avrebbe come superchiavi

* $id$
* $id, title, year, length, budget, plot$
* ogni combinazione di attributi che contenga $id$
* $title, year, length, budget, plot$
* $plot$ (dipende da quali situazioni vogliamo tollerare, "ci possono essere due film con la stessa trama?")
* $title, plot$ (dipende da quali situazioni vogliamo tollerare, "ci possono essere due film con lo stesso titolo e la stessa trama?")
* $title, plot, length$
### Chiave

Una chiave e' una superchiave minimale: non posso toglierle alcun attributo senza che sia una chiave.
$K$ e' un insieme di attributi superchiave di $R$, dato un qualunque insieme $S \subseteq K$, $K$ e' chiave se può esistere $t_1[K-S] = t_2[K-S]$.

Non posso togliere attributi alla chiave mantenendone la caratteristica di chiave, ad esempio a $title, year, length$ non posso togliere $length$, o $year$, o $length$, senza perdere univocità.

Tutte le combinazioni che contengono $id$ sono superchiavi, ma non sono chiavi perché posso togliere qualsiasi attributo, dal momento che $id$ da solo basta ad identificare una tupla.
### Chiave primaria

Vincolo di entity integrity; e' una chiave sulla quale non sono possibili valori `NULL`. Ogni relazione ha una e una sola chiave primaria.
## Integrità referenziale

Metto in relazione dati appartenenti ad entità diverse. Permette l'operazione di `JOIN`

$R_1$ e $R_2$, la prima e' la relazione che referenzia, la seconda e' quella referenziata

$R_1$ contiene attributo o insieme di attributi $X$ detto chiave esterna
$R_2$ contiene attributo o insieme di attributi $Y$ che e' chiave per $R_2$

Possibili problemi quando si fa un `INSERT` o `UPDATE`:
* chiavi
* vincoli integrità
* vincoli dominio - inserisco una stringa dove mi aspettavo un numero
* valori null

Quando si fa una `DELETE` su una tabella referenziata si può avere un problema solo se esistono record nella tabella referenziante; `NO ACTION`.
Politiche `NO ACTION` e `CASCADE` servono a mantenere l'integrità referenziale.

Un film viene proiettato in un cinema in un certo giorno e orario:
$$
\begin{align}
&\ projection(movie, c\_name, c\_city, p\_date, h\_date) \newline
\end{align}
$$
con:
* chiave $movie,\ c\_name,\ c\_city,\ p\_date,\ h\_date$ 
* $projection.movie$ foreign key $movie.id$
* $projection.c\_name,\ projection.c\_city$ referenziano $cinema.name,\ cinema.city$ e potrei scrivere in SQL `FOREIGN KEY (c_name, c_city) REFERENCES cinema(name, city) ON UPDATE CASCADE ON DELETE NO ACTION`; per evitare la chiave esterna composta, si può considerare l'uso di una chiave atomica

Se una tabella non e' referenziata da altre tabelle non serve un $id$.

Data
$$
\begin{align}
&\ person(id,\ firstname,\ lastname,\ father) \newline
\end{align}
$$

| id  | firstname | lastname   | father |
| --- | --------- | ---------- | ------ |
| 1   | Mario     | Montanelli | [NULL] |
| 2   | Stefano   | Montanelli | 1      |
$father$ e' chiave esterna, sebbene si riferisca alla stessa tabella: `FOREIGN KEY (father) REFERENCES person(id)`; inoltre la chiave esterna può essere `NULL`.


---
VECCHIO file

---












# Relational model

Mathematical relations between sets (domains) via a Cartesian product.
Data is organized in rows, all rows are different from each other (since they are the result of a Cartesian product).

| $Attribute_1$ | $Attribute_2$ | $\dots$ | $Attribute_k$ |
| ------------- | ------------- | ------- | ------------- |
| $Data_1$      | $Data_2$      | $\dots$ | $Data_k$      |

Keep the schema as fixed as possible, so that applications using it do not need to change accordingly.

Given $D_1 = \{a,b\}$ and $D_2 = \{x,y,z\}$ the Cartesian product is $D_1 \times D_2 = \{(a,x),(a,y),(a,z),(b,x),(b,y),(b,z)\}$.
A relation is a subset of that product such that $r \subseteq D_1 \times D_2 = \{(a,x), (a,y), (b,x), (b, y)\}$.

## NULL

Absence of a value in the domain.
We can't use it to compare values, an attribute $year$ which could be $NULL$ could not be compared with, for example, $2024$ to understand if it comes before or after. We can't even say if they're different or not (in a way).

### Open-world semantic

Everything that does not falsify a predicate it's true.

### Closed-world semantic

Only something that verify a predicate it's true.

## Constraints

A predicate that for each instance could return true or false.

### In a table

* domain constraints - for example $year \geq 1950 \ AND \ year \leq 2015$
* row constraints - express conditions on attributes on a row, domain constraints are row constraints that operate on a single attribute
* key constraints

## Functional dependency

A dependency $FD: X \rightarrow \ Y$ means that the values of $Y$ are determined by the values of $X$.

"Which are the minimum attributes I need to determine that attribute?"

This allows to determine which attributes go in which tables.

Of course we could only recognize functional dependencies if we know the domain of the application, if we don't know anything it's very hard to do a good job in identifying them.

<aside>key concept</aside>

Key question to ask ourself to see if there's a functional dependency: "left side of this hypothetical dependency identifies precisely the right side?" if not then there isn't a functional dependency.

## Normal forms

We want to avoid redundancy in the information.

A database is well formed if it has 1NF, 2NF, 3NF.
As a rule of thumb:
* we want lots of writes $\rightarrow$ more normalization
* we want lots of reads $\rightarrow$ less normalization

### 1NF

Only atomic attributes.
We don't have a relational database if we don't have this NF.

### 2NF

Depends on 1NF.

Every non-key attribute must depend on the primary key, as a whole, and not just on a subset of it.
Normalization: put the subset of the key and the attribute in a new table.

If the key is a single attribute then we always have 2NF.

### 3NF

Depends on 2NF.

Transitive dependency: $X \rightarrow Y$ is transitive if a set of attributes $Z$ (not key and not subset of a key) exists such that $X \rightarrow Z$ and $Z \rightarrow Y$. 
2NF and no non-prime attribute in $R$ depends in a transitive way on the primary key.

In other words: we don't want two non-key attributes on the left AND on the right of a functional dependency. They all have to be on the left side.

### BCNF

All non prime attributes must depend on a super-key.

The key in the decomposed relations must be a super-key in the original relation. 

## Using normal forms to decompose relations

Goal is to start from a big universal relation and finish to a scheme with multiple relations, which respect the normal forms.

There is a procedure to follow, which has 3 rules:
* preserve attributes
* preserve functional dependencies
* lossless joins - we don't want joins to that create data that was not present in the original relations

The algorithm is:
1. define functional dependencies
2. minimize functional dependencies
3. for each functional dependency $X \rightarrow Y$, create a new relation where we have $X$ and all dependencies on the right side that have $X$ on the left side 
4. attributes left out go in a relation of their own

## Normalization

Anomalies and redundancies arise when we have functional dependencies like $X \rightarrow Y$, where $X$ is not a superkey (a subset of the key). 
To avoid redundancies and anomalies, we have to put our relation in BCNF. 


