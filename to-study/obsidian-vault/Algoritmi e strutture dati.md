---
cssclasses:
  - cornell-note
tags:
  - algorithms
  - italian
---


## Refresher sui logaritmi
TODO

## Lezione 1 - 25/09

Algoritmo: procedimento per la soluzione di un problema

Fondamentale organizzare i dati -> strutture dati

### Fasi

Sintesi: problema -> algoritmo
Analisi: l'algoritmo trovato e' il migliore?

Sintesi
* Progettazione
* studio delle strutture dati
Analisi 
* correttezza: algo risolve realmente quel problema?
* efficienza: uso delle risorse (tempo, spazio, rete, processori usati, energia usata), molto spesso sono in constrasto, ad es un algoritmo che usa molto spazio magari usa meno tempo
* studio delle limitazioni inerenti e complessita' dei problemi, alcuni problemi hanno soluzioni poco efficienti (problema del commesso viaggiatore)

#### Case study - Commesso Viaggiatore

Istanza: n citta', le cui distanze sono note
Problema: vogliamo trovare il percorso piu' corto che visiti tutte le citta' e torni al punto di partenza

Trovo le permutazioni di n oggetti:
per la prima citta' posso scegliere $n$ possibilita'
per la seconda citta' sono $n - 1$
per la terza citta' sono $n - 2$
$...$
$1$
$n!$

Approssimazione di Stirling su fattoriale

Se ho molte citta' l'algoritmo potrebbe non terminare mai

Mi accontento di risposte approssimate

### Perche' studiare l'algoritmica?

Risoluzione problemi

Ci sono strumenti per capire i problemi, non solo per risolverli

CERCARE:
Knuth, the art of computer programming, spiegare un concetto ad un computer per capirlo realmente

#### Esempio di problema

$N > 0$
$N - 1$ compresi tra $1$ e $N$ tutti diversi tra loro
Trovare il numero mancante

Sommo tutti i numeri da 1 a N
Sommo tutti gli altri

Differenza tra le due

sommatoria da i = 1 a N e' (n+1) * N / 2

## Lezione 2 - 27/09

Punti fondamentali sintesi e analisi algoritmi

Algoritmo
Insieme ordinato e finito
di passi eseguibili e non ambigui
che definiscono un procedimento che termina

Concetto di passo dipende da ambito e livello di astrazione con cui analizziamo l'algoritmo.
Non ambigui: c'e' scritto tutto cio' che serve, niente gradi di liberta' all'esecutore.

Algoritmi che si basano sull'uso della randomizzazione: tecniche Montecarlo

Differenza algoritmo e programma: programma e' espressione dell'algoritmo nel linguaggio di programmazione

Programma
Insieme ordinato e finito
di istruzioni scritte secondo le regole
di uno specifico linguaggio di programmazione

```java
ALGORITMO moltiplicazione(intero a, intero b) -> intero
  RETURN a * b
```

Sintesi: dal problema progettare l'algoritmo

### Analisi

Viene fatta a priori per evitare di incorrere in perdite di tempo, altrimenti potrei scrivere un programma particolarmente lento

Correttezza - risolve il problema?
Efficienza - una volta fatto il punto precedente, valuto la quantita' di risorse usate

#### Esempio moltiplicazione - 1

```code
     19 * 
    114 =
---------
     76
    19
   19
---------
   2166
```
 
\# prodotti a 1 cifra = \# cifre(a) * \# cifre(b)
\# somme              = dipende da \# cifre(a) e \# cifre(b)

#### Esempio moltiplicazione - 2

Somme iterate

$a * b = a + a + ... + a$ sommato $b$ volte

```java
ALGORITMO moltiplicazione(intero a, intero b) -> intero
    prod <- 0              // 1
    WHILE b > 0 DO         // 2
        prod <- prod + a   // 3
        b <- b - 1         // 4
    return prod            // 5
```

Corretto?
Osservazioni
* `a` non viene modificata
* $b_{i}$, $prod_{i}$ valori dopo i-esima iterazione
Proprieta'
* $b_{i} = b - i$
* $prod_{i} = a * i$

|Quando     |   Linee eseguite  | Tempo |
| --- | --- | --- |
|b = 0     | 1, 2, 5    | 3 |
|b > 0     | 1, 5 1 volta | 2 |
|b > 0     | 3, 4 b volte | 2b |
|b > 0     | 2 b + 1 volte | b + 1 |

Per $b = 0$ totale $3$
Per $b > 0$ totale $3b + 3$

#### Esempio moltiplicazione - 3

Algoritmo "alla russa"

$19 * 114$

```code
* 2     / 2
19      114
38       57 <- 
76       28
152      14
304       7 <-
608       3 <-
1216      1 <-
```

Prendo un valore dalla colonna di sinistra quando destra e' dispari, cioe' nei punti in cui perdo $1$ a causa della divisione intera

$38 + 304 + 608 + 1216 = 2166$

$a * b = 2a * b / 2$ per $b$ pari
$a * b = 2a * (b - 1) / 2 + a$ per $b$ dispari

```java linenos:1
ALGORITMO moltiplicazione(intero a, intero b) -> intero
    prod <- 0
    WHILE b > 0 DO
        IF b e' dispari THEN
            prod <- prod + a
        b <- b / 2 // divisione intera
        a <- a * 2
    RETURN prod
```

$a_{i},b_{i},prod_{i}$ valori delle variabili dopo i-esima iterazione

Proprieta' $ai * bi + prodi = a * b$

##### Dimostrazione per induzione su i

Parto dalla proprieta' di cui sopra e svolgo

Base $i = 0$ $a_{0} = a$ $b_{0} = b$ $prod_{0} = 0$
$a_{0}b_{0} + prod_{0} = a * b + 0 = a * b$

Assumiamo dimostrato per $i-1$, dimostriamo per $i$ 

se $b_{i}-1$ e' dispari
$prod_{i} = prod_{i-1} + a_{i-1}$
$b_{i} = (b_{i-1}-1)/2$
$a_{i} = 2*a_{i-1}$

$a_{i}*b_{i}+prod_{i} = 2*a_{i-1}*(b_{i-1}-1)/2 + prod_{i-1}+a_{i-1}$
dopo le semplificazioni ottengo
$a_{i-1}*b_{i-1}+prod_{i-1}$
che e' $a*b$ secondo l'ipotesi induttiva

Se $b_{i-1}$ e' pari
$b_{i} = b{i-1}/2$
$prod_{i} = prod_{i-1} a_{i}=2a_{i-1}$
$a_{i}*b_{i} + prod_{i} = 2a_{i-1}*b_{i-1}+prod_{i-1}=a*b$

Se $b_{i}$ vale $0$ allora $a_{i}*b_{i} + prod_{i} = a*b$
che fa $0 + prodi = a*b$

quindi abbiamo provato.

u = \# iterazioni ciclo while

|Quando     |   Linee eseguite  | Tempo |
| --- | --- | --- |
|b > 0     | 1, 7 1 volta | 2 |
|b > 0     | 3, 5, 6 u volte | 3u |
|b > 0     | 4 <= u volte | <= u |
|b > 0     | 2 u + 1 volte | u + 1 |

Totale <= 5u + 3

Quanto vale u?

```code
b     u
0     0
1     1
2     2
3     2
4     3
5     3
...
```

per b > 0 u = log2n + 1 (parte intera del log)

Quindi T(a,b) = 5 logb + 8 (parte intera del log)
crescita logaritmica

## Lezione 3 - 30/09

"Come cresce il tempo al crescere della lunghezza dei dati in input?"

Dati x e y > 0 interi, voglio calcolare la potenza $x^{y}$

$x^y = x * x * ... * x$

### Esempio potenza - 1

```java linenos:1
ALGORITMO potenza(intero x, intero y) -> intero
    power <- 1
    WHILE y > 0 DO
        power <- power * x
        y <- y - 1
    RETURN power
```

TODO provare come fatto per le somme

### Esempio potenza con equazione di ricorrenza - 2

```java linenos:1
ALGORITMO potenza(intero x, intero y) -> intero
    IF y = 0 THEN
        RETURN 1
    ELSE
        power <- potenza(x, y / 2) // divisione parte intera
        power <- power * power
        IF y e' dispari THEN
            power <- power * x
        RETURN power
```

T(x,y) tempo in funzione di x e y

|Quando     |   Linee eseguite  | Tempo |
| --- | --- | --- |
|y = 0  | 2,3 1 volte | 2 |
|y > 0  | 1,5,6,7,8 1 volta | 5 |
|y > 0  | 8 <= 1 volta | <= 1 |
|y > 0  | 5 chiamata ricorsiva | $T(x, \lfloor y/2\rfloor)$ |

La riga 8 viene eseguita una sola perche' si considera il caso in cui `y` viene passato dall'esterno come dispari, e cio' puo' avvenire al piu' una volta.

Totale <= $6 + T(x, \lfloor y/2\rfloor)$ parte intera

$$
\begin{equation}
    T(x,y) =
    \begin{cases}
      2, & \text{se}\ y=0 \\
      6 + T(x, \lfloor y/2\rfloor) & \text{se} \ y > 0
    \end{cases}
  \end{equation}
$$

#### Equazione di ricorrenza

Calcolo $T(x, 1)$ usando la formula
$T(x, 1) = 6 + T(x, 0)) = 6+2 = 8$

Calcolo $T(x, y)$: fingiamo che $y$ sia pari, cosi che sparisca la parte intera e semplifichi le cose

$$
\begin{align}
T(x,y) & = 6+T(x, y/2) \\
& = 6 + (6 + T(x, y/2^2)) \\
& = 6 + 6 + 6 + T(x, y/2^3) \\
& \dots \\
& = 6 * k + T(x, y/2^k)
\end{align}
$$

Ogni volta quindi aggiungo un $6$ e aumento l'esponente al denominatore

<aside>Scrivere l'equazione di ricorrenza</aside>

Mi chiedo se riesco a far venire $1$ al posto di $y/2^k$: $k = log_{2}y$
Devo sostituire fino a che non arrivo a poter estrarre la k come qua sopra, perche' cosi posso utilizzare il calcolo per $T(x,1)$ che ho fatto sopra, quindi ottengo $6log_{2}y + T(x, 1) = 6log_{2}y + 8$

Questo pero' ci costa in termini di spazio, perche' il function call frame aumenta ad ogni chiamata ricorsiva!

Function call frame (il piu' in basso e' il primo chiamato, che aspetta gli altri)
potenza(2, 0) che poi esce liberando gli altri
potenza(2, 1)
potenza(2, 3)
potenza(2, 6)

Altezza della pila

$$
\begin{equation}
    H(x,y) =
    \begin{cases}
      1, & \text{se}\ y=0 \\
      1 + H(x, \lfloor y/2 \rfloor) & \text{se} \ y > 0
    \end{cases}
  \end{equation}
$$

Procedimento per la risoluzione dell'equazione di ricorrenza
1) scrivere il caso base
2) scrivere il passo ricorsivo
3) cercare di arrivare ad avere il caso base scritto nel passo ricorsivo
4) metto tutto assieme

Caso base: $H(x,0) = 1$, l'ho scritto sopra
Passo ricorsivo:

$$
\begin{align}
H(x,y) & = 1 + H(x, \lfloor y/2 \rfloor) \\
& = 1 + (1 + H(x, \lfloor y/2^2 \rfloor )) \\
& = 1 + (1 + (1 + H(x, \lfloor y/2^3 \rfloor ))) \\
& \dots \\
& = k + H(x, y/2^k)
\end{align}
$$

So che $H(x,0)$ e' $1$, quindi $H(x, 1)$ e' $1 + H(x, 0)$ quindi $2$.
Quindi che valore devo dare a $y/2^k$ per ottere $1$? Perche' se lo trovo poi e' fatta.

$$
\begin{align}
y/2^k = 1 \\
y = 2^k \\
log_{2}y = k
\end{align}
$$

Quindi, dato che:
* $k + H(x, y/2^k)$, e
* per $k=1$ abbiamo detto che $H(x,1) = 2$ 

ottengo $log_{2}y + 2$.

Il totale poi si otterrebbe moltiplicando il numero di variabili per questa quantita'.


## Lezione 4 - 02/10

> [!Error] RECUPERARE FINO A NOTAZIONI ASINTOTICHE

Quando si fa O(n^2) - O(n^2) occhio a non semplificare, non fa 0.

Nelle notazioni asintotiche la base del logaritmo non si scrive.

$x^y = x^{(y/2)*2}$



Domanda: quante cifre per rappresentare un numero in una base?

k = 5 

10000 2^4 = 16 numero piu' piccolo che riesco a ottenere con 5 cifre
11111 2^5 -1 = 31 numero piu' grande che riesco a ottenere con 5 cifre

Quindi 
2^{k-1} <= n < 2^k
k - 1 <= log_{2} < k
k <= log_{2}n+1 < k +1

Risposta: parteIntera(log2n)+1 bit
Per esprimere n per una qualunque base b>1 si usano theta(logn) cifre


Che rapport c'e' tra le lunghezze di un numero scritto in base 10 e uno scritto in base 2?

log2n = (log10n)/log102 = log210 * log10n
                            ^
                            questo ci dice che e' quasi 3 volte

In base 1 la lunghezza e' pari al numero stesso, quindi il ragionamento vale dal numero 2 in poi.


### Modello di calcolo astratto

Quando si calcola il peso di una riga bisogna sapere cosa il pc riesce a calcolare in tempo costante, ci serve un modello di calcolo di riferimento

Primo modello proposto: Macchina di Turing

Modello successivo e' la macchina ad accesso diretto (RAM)
Operazioni a tempo costante $O(1)$:
* accesso memoria load / store
* operazioni aritmetico logiche
* confronto
* salto



Ho una sequenza di elementi e voglio trovare il piu' piccolo; so solo che posso confrontare gli elementi, non conosco la loro natura

```java linenos:1
// assumo che s non sia vuota
ALGORITMO minimo(Sequenza s) -> elemento
    min <- primo elemento di s
    WHILE non hai ispezionato tutta s DO
        x <- prossimo elemento di s
        IF x e' minore di min THEN
            min <- x
    RETURN min
```

n e' il numero di elementi di `s`, facciamo 
* `n - 1` confronti
* 1 + n -1 + (al massimo n - 1) quindi <=2n-1

Faccio ThetaGrande(n) operazioni

Se ciascuna operaione usa tempo costante allora il tempo e' ThetaGrande(n)

Nel caso di stringhe i confronti non costano $O(1)$

## Lezione 5 - 04/10

Criterio di costo uniforme - ragionevole quando i valori trattati sono di una grnadezza limitata

Tempo: ogni istruzione elementare usa un;unit di tempo indipendendentemente dalla grandezza degli operandi
Spazio: ogni variabile elementare utilizza un'unita di spazio indipendnete dal valore contenuto


```java linenos:1
ALGORITMO xx(intero x) -> intero
    p <- 1
    FOR i <- 1 TO x DO
        p <- p * x
    return p
```

theta(x) assegnamenti e prodotti
theta(x) confronti e incrementi

Se usiamo il criterio di costo uniforme questo algoritmo costa O(x) tempo, pero' se dovessimo avere non piu' interi ma numeri piu' grossi il criterio di costo uniforme non puo' piu' essere usato.


Come calcoliamo il costo in caso di assegnamenti molti grandi?


Criterio di costo logaritmico

Tempo: proporzionale alla lunghezza dei valori coinvolti
Spazio: lunghezza della rappresentazione del dato

p + x
ThetaGrande(logp + logx)


```java linenos:1
ALGORITMO xx(intero x) -> intero
    p <- 1
    FOR i <- 1 TO x DO
        p <- p * x
    return p
```

Dobbiamo capire quanto ci costa riga 4.
Dopo i-esima iterazione p contiene $x^i$

Costo prodotto
i-esima iterazione `p <- p * x`
                         ^ $x^{i-1}$
Quindi
logx^{i-1} + logx = (i - 1)logx + logx = ilogx

Costo assegnamento
Devo assegnare a p $x^{i}$, sposto logx^{i} bit, quindi $ilogx$

Costo i-esima iterazione
Theta(ilogx)

$\theta$

Costo totale
Somma di tutte le iterazioni

sommatoria da i=1 a x di Theta(ilogx) =

Theta(sommatoria da i=1 a x di ilogx) =

Theta(x(x+1)/2logx) = Theta(x^2logx)

Costo totale spazio
Theta(logx^x) = Theta(xlogx)


Tempo massimo
TRa tutte le istanze di un problema prendiamo 
T(n) = max{tempo(I) | |I| = n} stima nel caso peggiore
Cosi che sono sicuro che per qualsiasi input saro' sicuro del tempo massimo

Tempo medio 
Media dei tempi utilizzati su input di lunghezza n, pesata rispetto alle probabilita'
T_{avg} (n) = sommatoria|I| =n di Prob(I) * tempo(I)


Algoritmi ragionevoli
Limitati da un tempo polinomiale ($n$, $n^2$, $n^3$, ...)

Algoritmi non ragionevoli ($2^n$)
Limitati da un tempo esponenziale, la risposta ci arriva sempre troppo tardi

Conviene investire in algoritmi piuttosto che in hardware.

Dato un problema P, quanto tempo si impiega per risolvere P?
Trovo un algoritmo A che risolve P in tempo T(n)

Limitazione superiore (upper bound): 
Trovo un algoritmo A che risolve P in tempo T(n), quindi T(n) e' sufficiente per risolvere P
"P e' risolubile in tempo O(T(n)) significa che esiste un algoritmo che risolve P e utilizza tempo O(T(n))"

<aside>Difficolta' trovare limite inferiore θ$ </aside>

Limitazione inferiore (lower bound): 
Dimostro che ogni algoritmo che risolve P deve usare tempo almeno T'(n), quindi T'(n) e' necessario per risolvere P, "P richiede tempo Omega(T(n)) significa che ogni algoritmo (anche quelli non ancora inventati) che risolve P utilizza tempo Omega(T(n))"

## Lezione 6 - Algoritmi di ricerca - 07/10

Introduciamo la ricerca di elementi all'interno di array: strutture indicizzate (array)
Collezione di elementi dello stesso tipo, ciascuno dei quali accessibile in base alla posizione

Caratteristiche:
* memorizzato in una porione contigua di memoria, quindi non è possibile aggiungere nuove posizioni, limitazione superabile: posso espandere la struttura incorrendo in un costo
* accesso mediante indice
* tempo di accesso indipendente dalla posizione del dato

Problema trovare indice di `x`
Input: Array `a`, elemento `x`
Output: Indice i tale che A[i] = x, -1 se non lo trovo

### Ricerca sequenziale

```java linenos:1
ALGORITMO ricercaSequenziale(Array A[0..n-1], elemento x) -> indice
    i <- 0
    WHILE i < n AND A[i] != x DO
        i <- i + 1
    IF i = n THEN RETURN -1
             ELSE RETURN i
```

Non posso fare meglio di una ricerca lineare, se pero' qualcuno ci dà l'array gia' in ordine allora cambia tutto.

### Ricerca binaria o dicotomica

#### Versione ricorsiva

`dx` rappresenta la prima porzione esclusa, quindi cerco da `sx` a `dx - 1` 

```java linenos:1
FUNZIONE ricercaBinaria(Array A, indice sx, indice dx, elemento x) -> indice
    IF dx <= sx THEN RETURN -1
    ELSE
        m <- (sx + dx) / 2 // divisione intera
        IF x = A[m] THEN RETURN m
        ELSE
            IF x < A[m] THEN RETURN ricercaBinaria(A, sx, m, x)
            ELSE             RETURN ricercaBinaria(A, m + 1, dx, x)

ALGORITMO ricerca(Array A[0..n-1], x) -> indice
    RETURN ricercaBinaria(A, 0, n, x)
```

Divido in ALGORITMO e FUNZIONE per pulizia, cosi non chiamo l'algoritmo con `0` e `n`.

Quanto tempo ci mette?

Array di esempio: 1 5 7 12 16 18 20 22
Valore da cercare: 12

| Chiamata    | Function call frame    |
| --- | --- |
| ricercaBinaria(A, 0, 8, 12)    | sx 0, dx 8, m 4   |
| ricercaBinaria(A, 0, 4, 12)    | sx 0, dx 4, m 2   |
| ricercaBinaria(A, 3, 4, 12)    | sx 3, dx 4, m 3   |

1 chiamata $n$ elementi
2 chiamata $n/2$ elementi
3 chiamata $n/2^2$ elementi
...
i chiamata $n/2^{i-1}$ elementi

Per arrivare ad uno spzio di 1 elemento allora $n / 2^{i-1} = 1$ $n = 2^{i-1}$ $i = 1+ log_2n$
Ma devo contare anche il caso base quindi $i = 2 + log_2n$
Quindi il tempo nel caso peggiore è $\theta(logn)$

Per lo spazio?
L'altezza della pila è uguale al numero di chiamate, quindi $\theta(logn)$
Lo spazio è $\theta(logn)$ perche' ogni record di attivazione ha dimensione costante, 5 in questo caso

<aside>Ricorsione in coda</aside>

"Non fa niente col valore se non rimbalzarlo all'esterno"
Questo tipo di ricorsione puo' essere sempre eliminato facilmente:

#### Versione iterativa

Notare l'utilizzo del while e della condizione `pos = -1` per evitare di avere il `RETURN` nel `WHILE`

```java linenos:1
ALGORITMO ricercaBinaria(Array a[0..n-1], elemento x) -> indice
    sx <- 0
    dx <- n
    pos <- -1
    WHILE sx < dx AND pos = -1 DO
        m <- (sx + dx) / 2
        IF x = A[m] THEN pos <- m
        ELSE IF x < A[m] THEN dx <- m
        ELSE                  sx <- m + 1
    RETURN pos
```

Quindi non ho piu' la memoria della ricorsione, ma solo 4 variabili. La memoria non dipende dalla grandezza dell'array.
Tempo $\theta(logn)$ 
Spazio $O(1)$

## Lezione 7 - Algoritmi di ordinamento - 09/10

Siccome avere un vettore ordinato ci consente ricerche veloci, come facciamo ad ordinare un vettore?

Ordinamento interno: dati all'interno della memoria centrale; ordiniamo array o vettori
Ordinamento esterno: dati in memoria di massa; accesso a blocchi di dati, lentezza hardware periferiche da tenere in conto

Stabile: se preserva l'ordine relativo tra record con la medesima chiave.

### Selection sort

https://tobinatore.github.io/algovis/selectionsort.html

Cerco l'elemento minore e lo metto al primo posto, scambiandolo con l'elemento alla posizione.
Avanzo un indice che rappresenta il limite della "parte ordinata", a sx del quale l'array e' ordinato

```java linenos:1
ALGORITMO selectionSort(Array A[0..n-1])
    FOR k <- 0 TO n - 2 DO // l'ultimo elemento, quando ci arrivo, 
                           // e' gia' al suo posto
        // ricerca la posizione m del minimo in A[k..n-1]
        m <- k
        FOR j <- k + 1 TO n - 1 DO
            IF A[j] < A[m] THEN
                m <- j
        scambia A[m] con A[k]     
```

Quante iterazioni facciamo nel ciclo piu' interno? $n-k-1$

Sommatoria da k=0 a n-2 di (n-k-1)
che e' come 
sommatoria da i=1 a n-1 di i
che e' come
(n-1)n tutto fratto 2
che e' come
$\theta(n^2)$

TODO: COME OTTENGO n-k-1?

### Insertion sort

https://tobinatore.github.io/algovis/insertionsort.html

Muovo un puntatore verso destra, scambio verso sinistra scambiando elementi fino a che non ottengo una situazione ordinata.

Differentemente da selection sort gli elementi a sinistra del puntatore non sono gia' tutti ordinati, ma puo' succedere che debba riordinarli

```java linenos:1
ALGORITMO insertionSort(array A[0..n-1])
    FOR k <- 1 TO n-1 DO
    x <- A[k]
    j <- k-1
    WHILE j >= 0 AND A[j] > x DP
        a[j+1] <- A[j]
        j <- j-1
    A[j+1] <- x
```

Nel ciclo while mentre cerco dove mettere `A[j]` sto anche creando posto spostando gli elementi!

Numero confronti totali nel caso peggiore: sommatoria k=1 a n-1 di k, che e' uguale a (n-1)n tutto /2

Caso peggiore quando l'array e' ordinato al contrario
Caso migliore quando l'arry e' gia' ordinato

### Bubble sort

https://tobinatore.github.io/algovis/bubblesort.html

Diverse passate sugli elementi, ogni passata scambia, l'effetto delle passate e' far migrare gli elementi piu' grandi in alto.
Mi fermo quando faccio una passata senza scambi.

```java linenos:1
ALGORITMO bubbleSort(array A[0..n-1])
    i <- 1
    DO
        scambiato <- False
        for j <- 1 TO n-i DO
            IF A[j] < A[j-1] THEN
                scambia A[j] e A[j-1]
                scambiato <- True
        i <- i + 1
    WHILE scambiato AND i < n
```

Dopo n-1 passate sono a posto tutti gli elementi.
`i < n` evita di fare l'ultima passata per controllare che siano tutti ordinati.

Su un array gia' ordinato fa $n-1$ confronti.
Totale confronti sommatoria da i=1 a n-1 di n-i che e' sommatoria da k=1 a n-1 di k che e' n(n-1)/2 theta(n^2)

## Lezione 8 - Algoritmi di ordinamento - 11/10

Supponiamo di avere due array ordinati, voglio ottenere un array ordinato che contenga gli stessi elementi.

```java linenos:1
ALGORITMO merge(array B[0..lb-1], array c[0..lc-1]) -> array
    Sia X[0..lb+lc-1] un array
    i1 <- 0, i2 <- 0, k <- 0
    WHILE i1 < lb AND i2 < lc DO
        IF B[i1] <= C[i2] THEN
            X[k] <- B[i1]
            i1 <- i1 + 1
        ELSE 
            X[k] <- C[i2]
            i2 <- i2 + 1
         k <- k + 1

    IF B i1 < lb THEN
        FOR j <- i1 TO lb - 1 DO
            X[k] <- B[j]
            k <- k + 1
    ELSE
        FOR j <- i2 TO lc - 1 DO
            X[k] <- B[j]
            k <- k + 1

    RETURN X
```

### Divide et impera

`|I|` vuol dire lunghezza di `I`

```java linenos:1
ALGORITMO risolviP(Istanza I) -> soluzione
    IF |I| <= C THEN
        risolvi P su I direttamente
        RETURN la soluzione
    ELSE
        dividi I in I1, I2, ..., Im con |Ij| < |I| per i = 1..n
        sol1 <- risolviP(I1)
        sol2 <- risolviP(I2)
        ...
        soln <- risolviP(In)
    RETURN combina(sol1, sol2, ..., soln)
```

Calcolo il tempo

T(I) = costante se |I| < C
$T(I) = T_{dividi(I)} + T(I_1) + T(I_2) + ... + T(I_n) + T_{combina(sol_1, ..., sol_n)}$ altrimenti

```java linenos:1
MergeSort Array A[0..n-1]
    IF n <= 1 THEN
        A e' gia' ordinato
    ELSE
        dividi a in due parti circa della stessa lunghezza
        ordina le due parti separatamente
        fondi i risultati in un'array ordinato
```

```java linenos:1
ALGORITMO mergeSort(array A[0..n-1])
    IF n > 1 THEN
        m <- n / 2
        B <- A[0..m-1] // prima meta' di A
        C <- A[m...n-1] // seconda meta' di A
        mergeSort(B)
        mergeSort(C)
        A <- merge(B, C)
```

Quanti confronti?


C(n) = C(parteIntera(n/2)) + C(parteInteraSuperiore(n/2)) + C_{merge}(n) se n > 1
C(n) = 0 altrimenti

Ma se ipotizzo che n e' pari posso togliere le parti intere

C(n) = 2C(n/2) + (n - 1) se n > 1
C(n) = 0 altrimenti

C(n) = 2C(n/2) + n - 1 = 2 (2 C(n/2^2) + n/2 -1 ) + n - 1 = 2^2 C(n/2^2) + n - 2 + n - 1
    = 2^2 (2C(n/2^3) + n / 2^2 - 1) + n-2 +n-1 = 2^3 C(n / 2^3) + n -2^2 + n - 2 + n - 1
    ... = 2^k C(n / 2^k) + kn - somma da i=0 a k-1 di 2^i
    = 2^k C(n/2^k) + kn - 2^k + 1

Quando n/2^k = 1?
n = 2^k 
k = log_2n

Sostituisco
$n(C(1) + nlog_2n-n+1)$ = $nlog_2n - n + 1$

Per n potenza di 2 $\theta(nlogn)$

In generale esiste potenza di 2 con n <= N < 2n

C(n) <= C(N) = Nlog_2N - N + 1 < 2n(log_22n) -n + 1

= 2n(1+log_2n) - n + 1 = $n+2nlog_2n+1$

= $\theta(nlogn)$ anche in generale


## Lezione 9


## Lezione 10 - Divide et impera, moltiplicazione matrici 16/10

INTEGRARE CON SLIDE

Prodotto tra due matrici M x N

Se N = 1 e' facile: C = (a_{11} * b_{11})
Se n > 1 l'idea e' dividere le matrici in quarti e alla fine rimetto tutto assieme

Controllo se ho guadagnato qualcosa con questo approccio:

T(n) = operazioni effettuate su matrici n x n

Equazione di ricorrenza

T(n) = 1 se n = 1
T(n) = 8T(n/2) + n^2 altrimenti

Spiegazione

Devo fare 8 prodotti di matrici n/2 * n/2
8T(n/2)
e 4 somme di matrici
4(n/2)^2 = n^2

T(n) = theta(n^3)

Vedi algoritmo di Strassen

<aside>teorema fondamentale / master theorem</aside>

Da una idea dell'ordine di grandezza per un algoritmo ricorsivo

Equazioni di ricorrenza VEDI SLIDE su teorema fondamentale
Vedi anche esempi sulla slide

<aside>QuickSort</aside>

https://tobinatore.github.io/algovis/quicksort.html

QuickSort

Idea e' partizionare l'array rispetto ad un pivot, ordino ricorsivamente quelli piu' piccoli e ricorsivamente quelli piu' grandi.

```java linenos:1
ALGORITMO quickSort(Array A) 
    IF lunghezza A > 1 THEN
        scegli un elemento x di A
        B <- { y appartiene A | y < x }
        C <- { y appartiene A | y > x }
        quickSort(B)
        quickSort(C)
        a <- concatenazione di B, x, C
```

Non vogliamo fare copie dell'array, quindi scriviamo un algoritmo che partizioni un array usando i puntatori.
Dopo la partizione il perno raggiunge la sua posizione definitiva.

```java linenos:1
ALGORITMO partiziona(Array A, indice i, indice f) -> indice
    perno <- A[i]
    dx <- f
    sx <-i
    WHILE sx < dx DO
        DO dx <- dx - 1 WHILE A[dx] > perno
        DO sx <- sx + 1 WHILE sx < dx AND A[sx] <= perno
        IF sx < dx THEN 
            scambio A[sx] con A[dx]
    scambia A[i] con A[dx]
    RETURN dx

```