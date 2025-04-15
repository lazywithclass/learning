---
cssclasses:
  - cornell-note
  - flex
tags:
  - relational-algebra
  - sql
---
[Databases](Databases.md)

Linguaggio di interrogazione procedurale che definisce le operazioni necessarie per estrarre dati da una o più relazioni di un database.

Qualunque operazione produce una relazione.

DQL, quindi non si modificano i dati, si interrogano solamente.

## Operatori

Operatori unari:
* proiezione $\pi$
* selezione $\sigma$
* ridenominazione $\rho$

Operatori binari insiemistici:
* unione
* intersezione
* sottrazione

Operatori binari di join:
* $\theta$ join
* equi join
* natural join

### Insiemistici

Ricorda che le relazioni in un database sono insiemi, vedi [Modello relazionale](Databases.md#Modello%20relazionale).

<span class="sidenote">Precondizioni</span>
Per poter fare unione, intersezione, differenza le relazioni devono avere pari grado, e attributo per attributo sono compatibili (le relazioni che partecipano non possono avere vincoli di dominio diversi)<label class="sidenote-toggle sidenote-number"></label>.
#### Unione

Date $R_1$ e $R_2$ restituisce gli elementi appartenenti a $R_1$ o $R_2$ (presi una volta, non ci devono essere duplicati)

$movie\_a \cup movie\_b = { r_1 \in movie\_a \lor r_2 \in movie\_b }$

Produce il medesimo grado della relazione di partenza e cardinalità pari a cardinalità di $movie\_a$ a cui vanno sommati i record di $movie\_b$ (escludendo i duplicati, perché dal punto di vista matematico sono lo stesso individuo).

Il nome della colonna e' quello della relazione di sinistra.

Se avessi

* in movie_a (003, 'shutter island', '2010', 138)
* in movie_b (003, 'shutter island', '2010', 120)

E ne facessi l'unione otterrei entrambi i record perché sono due individui diversi.
#### Intersezione

Date $R_1$ e $R_2$ restituisce gli elementi appartenenti a $R_1$ e $R_2$.

$movie\_a \cup movie\_b = { r_1 \in movie\_a \land r_2 \in movie\_b }$

Produce il medesimo grado della relazione di partenza e cardinalità pari agli elementi in comune.
#### Differenza

Date $R_1$ e $R_2$ restituisce gli elementi appartenenti a $R_1$ e non a $R_2$.
La differenza non e' una operazione simmetrica.
### Selezione $\sigma$

Risponde alla domanda "Quali record della relazione voglio considerare?"

Accetta un predicato, quindi posso usare `AND`, `OR`, `NOT`; conserva il grado e la cardinalità e' minore o uguale a quella di partenza.

Esempio: voglio trovare le pellicole del 2010
$$
\begin{align}
&\ \sigma_{year='2010'}\ movie \newline
\end{align}
$$

Scorre i record uno a uno, quindi e' una valutazione locale, non globale, quindi non può essere usata per quei casi dove si vogliono ottenere valori relativi al globale.
### Proiezione $\pi$

Seleziona solo l'insieme di attributi che voglio mostrare nel risultato. Riduce il grado e la cardinalità e'  uguale a quella di partenza.

E' possibile che applicando una proiezione si possano ottenere righe uguali, in questo caso record uguali collassano in uno solo.

Chiedersi sempre se e' il caso di ridurre gli attributi applicando una proiezione.

### Prodotto cartesiano

$$
\begin{align}
&\ R_1(A,\ B) \newline
&\ R_2(C,\ D) \newline
\end{align}
$$

| A   | B   |
| --- | --- |
| a1  | b1  |
| a2  | b3  |

| C   | D   |
| --- | --- |
| c1  | d1  |
| c2  | d2  |
| c3  | d3  |
$R_1 \times R_2$ produce una relazione avente come grado la somma dei gradi delle relazioni, e una cardinalità che e' il prodotto delle cardinalità delle relazioni

| A   | B   | C   | D   |     |
| --- | --- | --- | --- | --- |
| a1  | b1  | c1  | d1  |     |
| a1  | b1  | c2  | d2  |     |
| a1  | b1  | c3  | d3  |     |
| a2  | b2  | c1  | d1  |     |
| a2  | b2  | c2  | d2  |     |
| a2  | b2  | c3  | d3  |     |
### Ridenominazione $\rho$

Data $R_1(A,\ B,\ C)$ se scrivo $\rho_{C\rightarrow D} R_1$ ottengo $R_1(A,\ B,\ D)$.
### Join $\bowtie$

### $\theta$ join

Il prodotto cartesiano ritorna tutte le combinazioni, ma non tutte le righe concorrono al risultato di questo join, solo quelle dove la chiave primaria della relazione di sinistra e la chiave esterna della relazione di destra sono le stesse.

Quindi e' una selezione sul risultato del prodotto cartesiano. $\theta$ e' il predicato che rappresenta questa selezione.
### Equi join

Join che hanno una uguaglianza come predicato.
### Join naturale *

Equi join che considera l'eguaglianza degli attributi con il medesimo nome nelle due relazioni. Eventuali attributi con lo stesso nome collassano in uno solo.

$$
\begin{align}
&\ R_1(A,\ B) \newline
&\ R_2(B,\ C) \newline
&\ R_1 * R_2 \rightarrow (A,\ B,\ C) \newline
\end{align}
$$
Come si vede nell'ultima riga $B$ compare una volta sola.

### Divisione

Vediamo questo operatore con un esempio
$$
\begin{align}
&\ movie\_production(name,\ country) \newline
&\ country(name) \newline
\end{align}
$$
Permettere di evidenziare una interazione tra tuple di due tabelle: "vuole restituire quei record della tabella $R_1$ che sono in relazione con tutte le tuple della relazione $R_2$", nell'esempio la divisione restituirebbe quelle pellicole che sono state prodotte in tutti i paesi.

Viene definita come l'opposto al prodotto cartesiano.

<span class="sidenote">Numeratore e denominatore</span>
Al denominatore metto elementi che vogliamo siano in relazione con il soggetto di "tutti".
Al numeratore metto una relazione i cui attributi contengono gli attributi al denominatore, e che voglio portare nel risultato <label class="sidenote-toggle sidenote-number"></label>. 

Dal punto di vista formale, date due relazioni $R(XY)$ e $S(Y)$ posso applicare la divisione $R/S$ se lo schema $S$ ha un insieme di attributi contenuto nello schema $R$, con stesso dominio e stesso nome.

$R/S$ ha come schema la differenza tra lo schema di $R$ e lo schema di $S$, quindi in questo caso e' definita su $X$.
$$
\begin{align}
&\ R/S(X) = \{ t_1\ su\ X\ |\ \forall\ t_2 \in S, \exists t \in R\ con \ t[X] = t_1 \land t[Y] = t_2  \} \newline
\end{align}
$$

Nell'esempio vogliamo trovare un record che ha $country.name$ pari a $movie\_production.country$, a parità di $movie\_production.name$.

Altro esempio

$R$
| a   | b   |
| --- | --- |
| a1  | b1  |
| a2  | b1  |
| a1  | b2  |

$S$
| b   |
| --- |
| b1  |
| b2  |
Dobbiamo trovare un valore di $R.a$ per il quale troviamo in $S$ un record di $R$ per cui quel valore di $a$ compare. $R/S$ darebbe 

$R/S$
| a   |
| --- |
| a1  |
La divisione e' utile quando non so a priori cosa ho a denominatore

ESPANDERE Con la chiarificazione di ciò che voglio avere a numeratore a

---
## Suggerimenti

Cercare di limitare quanto più possibile il grado di una relazione, in modo da fare che le relazioni si possano combinare più facilmente.

## Esercizi con lo schema imdb

![IMDB Schema](attachments/imdb-schema.png)

*  Trovare le pellicole del 2010 che non sono thriller

Procedura:

* relazione $R_1$: una relazione con grado 1 che contiene l'attributo id e i record dei movie prodotti nell'anno 2010
* relazione $R_2$: una relazione con grado 1 che contiene l'attributo $genre.movie$ delle pellicole thriller
* quindi posso fare $R_1 - R_2$

Sto cercando l'assenza di qualcosa, quindi devo usare la sottrazione o un join esterno.

$$
\begin{align}
&\ R_1 = \sigma_{year='2010'}\ movie \newline
&\ R_2 = \sigma_{genre='thriller'}\ genre \newline
&\ \pi_{id}\ R_1 - \pi_{movie} R_2 \newline
\end{align}
$$

<span class="sidenote">Usare la proiezione per ottenere relazioni confrontabili in termini di sottrazione</span>
Siccome $R_1$ ha 3 attributi, e $R_2$ ne ha 2 queste due relazioni non sono confrontabili ne per numero ne per dominio <label class="sidenote-toggle sidenote-number"></label>. Anche se le colonne hanno nome diverso hanno lo stesso tipo di dato, quindi sono confrontabili.

* Trovare le pellicole che sono di genere "thriller" o "crime"

Procedura:

* trovo le pellicole di genere "thriller"
* prendo l'id dalla relazione precedente
* trovo le pellicole di genere "crime"
* prendo l'id dalla relazione precedente
* unisco le due relazioni ottenute

* Trovare le pellicole che sono di genere "comedy" e "romantic"

Appartenere ad entrambi i generi vuol dire avere due record in cui c'e' il codice della pellicola e ognuno dei due "genre" voluti

Procedura:

* trovo le pellicole di genere "comedy"
* prendo l'id dalla relazione precedente
* trovo le pellicole di genere "romantic"
* prendo l'id dalla relazione precedente
* prendo l'intersezione delle due relazioni ottenute

$$
\begin{align}
&\ movie\_comedy = \sigma_{genre='comedy'} genre \newline
&\ movie\_romance = \sigma_{genre='romance'} genre \newline
&\ movie\_comedy\ \cap\ movie\_romance \newline
\end{align}
$$
E' sbagliato perché il risultato e' simile a

movie_comedy

| id  | genre  |
| --- | ------ |
| 1   | comedy |
| 2   | comedy |

movie_romance

| id  | genre   |
| --- | ------- |
| 3   | romance |
| 1   | romance |

Che non produce il risultato sperato perché non ci sono record uguali su cui fare intersezione, quindi devo usare la proiezione:

$$
\begin{align}
&\ movie\_comedy = \sigma_{genre='comedy'} genre \newline
&\ movie\_romance = \sigma_{genre='romance'} genre \newline
&\ \pi_{movie}(movie\_comedy)\ \cap\ \pi_{movie}(movie\_romance) \newline
\end{align}
$$
Soluzioni alternative? No!
$$
\begin{align}
&\ R_1 = \sigma_{genre='comedy'\ \land\ genre='romance'}\ genre \newline
&\ R_2 = \sigma_{genre='comedy'\ \lor\ genre='romance'}\ genre \newline
\end{align}
$$
$R_2$ e' sbagliata perché prendo anche le righe che soddisfano una delle due.
$R_1$ e' sbagliata perché ogni riga viene valutata individualmente, ma nessun record può avere entrambi, quindi $R_1$ da sempre come risultato $\emptyset$.

> [!warning] TODO inserire rimando di Ferrara al fatto che ogni riga viene valutata individualmente

* Trovare le persone che hanno interpretato come attore il personaggio 'Dexter'

$$
\begin{align}
&\ crew(movie,\ person,\ role,\ character) \newline
\end{align}
$$
$$
\begin{align}
&\ \pi_{person}(\sigma_{character='Dexter'\ \land\ role='actor'}\ crew) \newline
\end{align}
$$
In questo caso un record ha entrambi gli stessi attributi, quindi posso usare $\land$, differentemente rispetto a prima.

Posso usare l'atomizzazione delle selezioni per ottimizzare la query (l'operazione più selettiva va fatta per prima per poter dare alla seconda un set più piccolo possibile su cui lavorare)

$$
\begin{align}
\pi_{person}(\sigma_{character='Dexter'}(\sigma_{role='actor'}\ crew))
\end{align}
$$
* Trovare il nome delle persone nate dopo il 2000 che recitano in film thriller

$$
\begin{align}
&\ movie(id,\ title) \newline
&\ genre(movie,\ genre) \newline
&\ crew(person,\ movie) \newline
&\ person(id,\ given\_name,\ birth\_date) \newline
\end{align}
$$
Procediamo per step

$$
\begin{align}
&\ person\_2000 = \sigma_{birth\_date\ \geq\ '2000-01-01'}\ person \newline
&\ thriller = \sigma_{genre\ =\ 'thriller'} genre \newline
&\ person\_2000\_crew = person\_2000\ \bowtie_{id=person}\ crew \newline
&\ \pi_{id,\ given\_name}(person\_2000\_crew\ \bowtie_{person\_2000\_crew.movie=movie\_thriller.movie}\ thriller )\newline
\end{align}
$$

* Guardando lo schema IMDB dato a lezione

* Trovare il titolo delle pellicole con valutazione (rating) maggiore di 8
$$
\begin{align}
&\ movie\_rating = \rho_{id\rightarrow movie}(movie)\ *\ rating \newline
&\ \sigma_{score>8}(movie\_rating) \newline
\end{align}
$$

* Trovare le pellicole thriller con valutazione sopra 8
$$
\begin{align}
&\ thriller\_movies = \sigma_{genre='thriller'}(\rho_{id\rightarrow movie}(movie)\ *\ genre) \newline
&\ \sigma_{score>8}(thriller\_movies) \newline
\end{align}
$$

* Trovare il nome dei registi di film thriller
$$
\begin{align}
&\ thriller\_movies = \pi_{movie}(\sigma_{genre='thriller'}(\rho_{id\rightarrow movie}(movie)\ *\ genre)) \newline
&\ \sigma_{p\_role='director'}(thriller\_movies * movie) \newline
\end{align}
$$

* Trovare i film le cui recensioni sono sempre superiori a 8 (non immediato!)
$$
\begin{align}
&\ movies\_under\_8 = \pi_{movie}(\sigma_{score\leq 8}(\rho_{id\rightarrow movie}(movie) * rating)) \newline
&\ \pi_{movie}(rating) - movies\_under\_8 \newline
\end{align}
$$

* Trovare le pellicole distribuite (released) sia in USA sia in FRA

$$
\begin{align}
&\ released\_usa = \pi_{movie}(\sigma_{country='USA'}(\rho_{id\rightarrow movie}(movie)\ *\ released)) \newline
&\ released\_fra = \pi_{movie}(\sigma_{country='FRA'}(\rho_{id\rightarrow movie}(movie)\ *\ released)) \newline
&\ released\_usa\ \cap\ released\_fra \newline
\end{align}
$$

* Trovare le pellicole che non sono prodotte in GBR 
$$
\begin{align}
&\ \sigma_{country!='GBR'}\rho_{id\rightarrow movie}(movie) * produced \newline
\end{align}
$$

Questa e' sbagliata, perché potrei avere una situazione di questo tipo

| movie | country |
| ----- | ------- |
| m1    | FRA     |
| m1    | GBR     |
| m2    | FRA     |

Non ci si può accontentare della selezione per via della presenza di $m1$, quindi devo fare la sottrazione.

* Trovare titolo e anno dei film che sono thriller, drama, e action

$$
\begin{align}
&\ movie(id,\ title,\ year) \newline
&\ genre(movie,\ genre) \newline
&\ \newline
&\ G = \sigma_{genre='thriller' \lor genre='action' \lor genre='drama'} genre \newline
&\ H = \rho_{movie\rightarrow id} G \newline
&\ J = movie\ *\ H  \newline
&\ J / \pi_{genre}G  \newline
\end{align}
$$
Il risultato e' definito sulle colonne $id$, $title$, $year$.

Funzionerebbe anche con l'intersezione in questo caso, ma il vantaggio della divisione e' che non e' necessario specificare a priori come e' composto il divisore.
## Esercizi usando uno schema simile a quello dell'esame

$$
\begin{align}
&\ continent(\underline{id},\ name) \newline
&\ country(\underline{id},\ name,\ continent(continent),\ currency) \newline
&\ city(\underline{id},\ name,\ country(country),\ population) \newline
&\ politician(\underline{id},\ name,\ birth\_place(country),\ gender,\ birth\_date,\ party) \newline
&\ govern(\underline{city(city),\ head(politician),\ election\_year}) \newline
&\ country\_borders(\underline{country\_a(country),\ country\_b(country)}) \newline
\end{align}
$$

* Trovare il nome dei politici che non hanno governato città con più' di 500.000 abitanti

A: seleziono città con più di 500k abitanti
B: seleziono da govern i politici che hanno governato le città in A
tolgo da politician i politici in B

Ha senso prendere da politician piuttosto che da govern come sottrazione di partenza, perché voglio tenere anche quelli che non hanno mai governato.

* Trovare il nome delle città in cui non e' utilizzato il dollaro come moneta

* Trovare i paesi che non confinano con l'italia

A: trovare i paesi che confinano con ITA
B: sottrarre A dall'elenco di tutti i country

$$
\begin{align}
&\ A = \pi_{country\_b}(\sigma_{country\_a\ =\ 'IT'}\ country\_borders) \newline
&\ B = \pi_{country\_a}(\sigma_{country\_b\ =\ 'IT'}\ country\_borders) \newline
&\ C = A \cup B \newline
&\ \pi_{id}(country) - C \newline
\end{align}
$$

Devo ottenere $A$ e $B$ perché ITA puo' apparire sia come $country\_a$ che $country\_b$ 

Non c'e' bisogno di ridenominare l'id in $A$ e $B$, perché conta il dominio del dato, non il suo nome. 

* Trovare i politici che hanno governato tutte le città di San Marino
$$
\begin{align}
&\ \pi_{city.id}(\sigma_{name='San Marino'} (country)\ \bowtie_{city.country = country.id}\ city) \newline
&\ \pi_{head,\ city}(govern) / \rho_{id\rightarrow city}A \newline
\end{align}
$$
Vogliamo sapere quali sono i politici in relazione con tutte le città di San Marino, quindi serve la divisione.

Se non togliamo $election\_year$ allora otteniamo quei politici che nello stesso anno hanno governato città di San Marino.

* Trovare le citta' governate da piu' di un politico dopo il 2020
$$
\begin{align}
&\ A = \pi_{head,\ city}(\sigma_{election\_year \gt 2020}\ govern) \newline
&\ B = \rho_{city\rightarrow city',\ head\rightarrow head'}\ A \newline
&\ \pi_{city}(A \bowtie_{city=city'\ \land\ head<>head'} B) \newline
\end{align}
$$
Essendo che non siamo in grado di contare possiamo mettere in join $A$ con se' stessa, in modo da poterli comparare.

* Trovare città che sono state governate da politici sia di destra sia di sinistra
$$
\begin{align}
&\ A = govern\bowtie_{head=id}(politician) \newline
&\ governate\_destra = \pi_{city}(\sigma_{party='destra'} A) \newline
&\ governate\_sinistra = \pi_{city}(\sigma_{party='sinistra'} A) \newline
&\ governate\_destra \cap governate\_sinistra \newline
\end{align}
$$
Soluzione alternativa con join
$$
\begin{align}
&\ A = govern\bowtie_{head=id}(politician) \newline
&\ B = \sigma_{party='destra'\ \lor\ party='sinistra'}\ A \newline
&\ C = \rho_{party\rightarrow party',\ city\rightarrow city'}(B) \newline
&\ A\ \bowtie_{city=city'\ \land\ party<>party'} C \newline
\end{align}
$$
* Trovare le pellicole prodotte in due paesi diversi TODO (e' un self join, rivedi come si fa a chiamare in modi diversi la stessa tabella)
$$
\begin{align}
&\  \newline
\end{align}
$$



VEDI ESERCIZI su Ariel e appunti del prof
INSERIRE e capire come si deriva la divisione









---
VECCHIO FILE

Allows to understand 
 * fundamental knowledge about how queries work
 * execution plans and how to construct efficient ones

## Fundamental principle

<aside>one tuple at a time</aside>
We never have the chance to see all data together, we just see one tuple at a time. So we need to put data side by side to be able to compare it.
This is because the relations contain facts.
Understanding this allows to reason about operators.

## Notation

To use attribute $a$ from relation $R$ write $R.c$

## Operators

Considering table as if they were sets in the mathematical meaning helps a lot.

Domains of the tables should be the same for the operations to be performed.

* $\cup$ union
* $-$ difference: $R - S$ keeps only the tuples that are in $R$ but not in $S$, no records in $S$ are in the result
* $\cap$ intersection: could also be written as $R \cup S - (R - S) - (S - R)$
* $\sigma$ selection: returns some of the tuples, based on a predicate, <span class="b">one tuple at a time</span> they are checked against the predicate, example $\sigma_{minplayers < 3 \wedge maxplayers \geq 3}(\textsf{boardgame})$
* $\pi$ projection: selects attributes from the tuples, example $\pi_{A,B}{R}$, if the projection makes it so that there would be duplicated tuples these are removed
* $\bowtie$ equi-join: <span class="b">selection over Cartesian product</span>, tuples with same values on shared attributes are put together; 
    * given two tables with the same attributes like $R(A, B, C)$ and $S(A, B, C)$, then $R \bowtie S = R \cap S$
    * given two tables without common attributes like $R(A,B,C)$ and $Z(X,Y,K)$, then $R \bowtie Z = R \times Z$, because we get all attributes and all tuples combinations, from these we remove those that have the same values over the in common attributes, which are none, therefore it is equivalent to the Cartesian product 
* $\bowtie_{\theta}$ theta-join: which is a $\sigma$ applied to a Cartesian product, example $\bowtie_\theta{id=category}$, used to match tuples with others
* $\rho$ rename: for example $\rho_{a\rightarrow b}$ 
* $\div$ division: given $R_{1}$ and $R_{2}$ by doing $R_{1} \div R_{2}$ we get all tuples in $R_{1}$ that combine with $R_{2}$, which is the inverse of Cartesian product

Note on joins: they can be implemented as the Cartesian product with a selection.

## The need for subtraction

There are scenarios where it's tricky to understand what to do, or at least it is for me: say we want to find all board games that are not wargames, these are the tables:

<p class="table-header">Categories</p>

| id  | name    |
| --- | ------- |
| 1   | Wargame |
| 2   | Ancient |

<p class="table-header">GamesCategories</p>

| category | boardgame |
| -------- | --------- |
| 1        | 6         |
| 1        | 4         |
| 2        | 4         |

We can't do $\pi_{GC.boardgame}(\sigma_{C.name\neq Wargame}(GC\bowtie_{GC.category=C.id}C))$ resulting in:


| boardgame | category | name    |
| --------- | -------- | ------- |
| 6         | W        | Wargame |
| 4         | A        | Ancient |

because the $boardgame$ having id 4 has different categories, so we would not show the line where it has $name$ 4, but we would show the like where it has $name$ $Ancient$, which is wrong because this boardgame also is a $Wargame$. 

<span class="b">To verify a negative condition we have to verify all data together, which we can't</span>, because of the [Relational algebra#Fundamental principle](Relational%20algebra#Fundamental%20principle.md).

<aside>Solution when working with negation</aside>
Always obtain those records that fall in the category, and then subtract these from all records: 

$\pi_{id}(B) - \pi_{GC.boardgame}(\sigma_{C.name=Wargame}(GC\bowtie_{GC.category=C.id}C))$

## The need for division

Say you have

<p class="table-header">R</p>

| A   | B   |
| --- | --- |
| 1   | a   |
| 1   | b   |
| 2   | a   |

<p class="table-header">S</p>

| B   |
| --- |
| a   |
| b   |
and you want to find all $R.A$ that have a relation with all $S.B$.
To achieve that we
1) find all correspondence $\pi_{A}(R) \times \pi_{B}(S)$
2) remove all correspondence in $R$ $\pi_{A}(R) \times \pi_{B}(S) - R$, so we get elements from $A$ that are missing a correspondence from elements in $B$
3) remove from all possible elements what we got at 1) $\pi_{A}(R) - \pi_{A}(\pi_{A}(R) \times \pi_{B}(S) - R)$

This operation is called division $\div$.

### More info

[Great explanation](https://www2.cs.arizona.edu/~mccann/research/divpresentation.pdf).

Given relation $R$ and $S$, $R \div S$ identifies attribute values in $R$ that can be paired with all of the values in $S$. It is the inverse of Cartesian product.

![relational-algebra-cartesian-product.png](relational-algebra-cartesian-product.png) 
![relational-algebra-division.png](relational-algebra-division.png)

![relational-algebra-in-short.png](relational-algebra-in-short.png)

If $U = R \times S$ then $U \div S = R$ e $U \div R = S$.

## Talking about "all"

It is useful when you need to "find all X that Y" to think about it in a different way, so try to "find all X that do not Y" and then remove from X what you just found (hope this makes sense).

In other scenarios it might be more difficult to "find all X that are Y in all Z", so you could:
* create a new table called $All$, using Cartesian products, that contains all possible solutions available in the data
* from $All$ we remove those records that match out condition, so we get $Opposite$, which are those that do not match the condition
* finally from the existing records we remove $Opposite$

and so we arrive to the result. <span class="b">Which is the same of doing a division</span>.

We are doing a double negation, so to make an example with BoardGame: "we take games such that it does not exist a game such that it is not in first position".

## Notes

Cartesian product between something and the empty set is the empty set $R \times \emptyset = \emptyset$.

Sometimes it might be useful to invert the sentence and ask ourselves the negated version of it.

## Exercises

### Boardgame

Following examples use this schema:

![Pasted image 20240201195400.png](Pasted%20image%2020240201195400.png)

<aside>writing a query</aside>
How to approach writing a query:

* imagine the data I need, what characteristics does it have? 
* which tables do we need?
* which attributes do we need? selections
* when dealing with negation first find the query without the negation then subtract

1. name of those games that have an average score of 0.7
$$\pi_{gvalue}(\sigma_{Ratings.average\gt0.7}(Gamename\bowtie Ratings))$$
There is no need to specify the attributes since they're the same

2. games that are wargames
$$\pi_{GameCategory.boardgame}(\sigma_{cname="Wargame"} (GameCategory\bowtie_{boardgame=id} Category))$$
Since we don't have attributes with the same name now we need the condition in the join

3. games that are not wargames
$$\pi_{GameCategory.boardgame}(\sigma_{cname\neq"Wargame"} (GameCategory\bowtie_{boardgame=id} Category))$$
<aside>Note on negating conditions</aside>
That query is wrong though, because there could be games that are both "Wargame" and "Ancient" for example.
Since we query line by line we don't have visibility on the relationship as a whole.
This is different from looking a game that's a "Wargame".
The idea would be to take from all boardgames the ones that are "Wargame":

$$\pi_{id}(boardgame) - \pi_{GameCategory.boardgame}(\sigma_{cname\neq"Wargame"} (GameCategory\bowtie_{boardgame=id} Category))$$

4. Given the following we want to find all values of `R.A` that have a correspondance with all values `S.B`. Could be useful when you want to find all students that attended all courses for example.
![Pasted image 20240201221133.png](Pasted%20image%2020240201221133.png)
$$\pi_A(R) \times \pi_{B}(S) - R$$
With the first part $\pi_A(R) \times \pi_{B}(S)$ we get all possible combinations, we remove `R` so we get the online one that's missing.
So we arrive to $\pi_{A}(R) - \pi_{A}(\pi_A(R) \times \pi_{B}(S) - R)$
This last operation is called division.

5. All boardgames of type "Wargame" playable by at least three players
$\pi_{B.id}(\sigma_{B.minp\geq 3 \wedge C.name="Wargame"}((Boardgame\bowtie_{B.id=GC.gamecategory}GameCategory) \bowtie_{GC.category = C.id}))$ 
or an optimized version
$(\pi_{B.id}(\sigma_{B.minp \geq 3}(B)) \bowtie_{B.id = G.boardgame} G) \bowtie_{G.cateogry=C.id} \pi_{C.id}(\sigma_{C.name="Wargame"}C)$

6. Find all producers that had both designer and artist role in the same game
$\pi_{C_{1}.production}(\sigma_{C_{1}.ctype=designerC_{1}}$
$\bowtie_{C_{1}.boardgame=C_{2}.boardgame \wedge C_{1}.poduction=C_{2}.production}$
$\sigma_{C2.ctype=artist}C_{2})$
or also
$\pi_{boardgame,production}(\sigma_{C_{1}.ctype=designer}C_{1})$
$\bowtie$
$\pi_{boardgame,production}(\sigma_{C_{1}.ctype=artist}C_{1})$
or also
$\pi_{boardgame,production}(\sigma_{C_{1}.ctype=designer}C_{1})$
$\cap$
$\pi_{boardgame,production}(\sigma_{C_{1}.ctype=artist}C_{1})$

7. Find all games that are first in all their rankings
Looking at the `Ranking` table above we see we might have this situation.
So doing $\sigma_{position=1}Ranking$ produces a wrong result since boardgame with id `1` has position `2` for rank `2` so it should not appear in a query that is listing all games that are first in all their rankings, because it's clearly second here. 
So let's find all games that do not have position 1 and then subtract this from all rankings.
$\pi_{boardgame}R - \pi_{boardgame}(\sigma_{position\neq 1}Ranking)$

8. Find all games that are first in all rankings
All games that are not first in some ranking
$NotFirst = \pi_{Boardgame.id}(\pi_{id}Boardgame \times \pi_{id}Ranking \times \pi_{pos}(\sigma_{pos=1}Ranking)$
$-$
$\pi_{rank,boardgame, position}Ranking(\sigma_{pos=1}Ranking))$
and then
$\pi_{id}Boardgame - NotFirst$
or using the $\div$ operator
$\pi_{boardgame,rank,pos}Ranking \div \pi_{rank,position}(\sigma_{pos=1}Ranking)$

9. Find users that have commented all wargame boardgames
$\pi_{bgguser,boardgame}(Comment)$
$\div$
$\pi_{boardgame}(\sigma_{cnmame="Wargame"}Category \bowtie_{Category.id=GameCategory.category}GameCategory)$
or also
$\pi_{bgguser}Category - \pi_{bgguser}(\pi_{bgguser}C \times \pi_{boardgame}(\sigma_{cname=Wargame}Category \bowtie_{Category.id=GameCategory.category}GameCategory)$
$-$
$\pi_{bgguser.boardgame}((Category \bowtie_{Category.boardgame=GameCategory.boardgame}GameCategory) \bowtie \sigma_{cname=Wargame}(Category))$

10. Find all games that are not sold at less than a 100E
$\pi_{boardgame}Listings - \pi_{boardgame}(\sigma_{currency=EU \wedge price\lt 100}Listings)$

### Cities

![relational-algebra-politicians.png](relational-algebra-politicians.png)

1. Find the names of politicians that are in charge in cities of nations where they were not born into
$\sigma_{C.country \neq P.birth\_place}(Politician \bowtie_{P.id=C.head}City)$
or
$Politician \bowtie_{P.id=C.head \wedge C.country \neq P.birth\_place}City$

2. Find cities that have a greater population than their nearby cities
$NEARBY\_CITIES = \pi_{id,population}City_{1} \bowtie_{C_{1}.id=CB.city_a} City\_Borders \bowtie_{C_{2}.id=CB.city_b} City_{2}$
$NEARBY\_CITIES\_NOT\_GREATER = \sigma_{C_{2}.population \gt C_{1}.population}$
So these are the cities for which there exists another nearby city with a greater population, which is what I DO NOT want, so now I just need to subtract
$\pi_{id}Cities - \pi_{C_{1}.id}(NEARBY\_CITIES\_NOT\_GREATER)$

3. Find nations in which there are no cities ruled by politicians born before 1960
I will find countries where there is a politician born before 1960 and then I subtract from the list of countries.
$COUNTRIES = \pi_{country}(\sigma_{birth\_date<01.01.1960}Politician \bowtie_{P.id=C.head} City)$
$\pi_{C.id}Country - COUNTRIES$

4. Find politicians that are head in more than one city
$\pi_{C_{1}.head}(City_1 \bowtie_{City_1.id \neq City_2.id \wedge City_1.head = City_2.head} City_2)$

