---
cssclasses:
  - cornell-note
tags:
  - algorithms
  - italian
---

- [[#Lezione 1 - 25/09|Lezione 1 - 25/09]]
	- [[#Lezione 1 - 25/09#Fasi|Fasi]]
		- [[#Fasi#Case study - Commesso Viaggiatore|Case study - Commesso Viaggiatore]]
	- [[#Lezione 1 - 25/09#Perche' studiare l'algoritmica?|Perche' studiare l'algoritmica?]]
		- [[#Perche' studiare l'algoritmica?#Esempio di problema|Esempio di problema]]
- [[#Lezione 2 - 27/09|Lezione 2 - 27/09]]
	- [[#Lezione 2 - 27/09#Analisi|Analisi]]
		- [[#Analisi#Esempio moltiplicazione - 1|Esempio moltiplicazione - 1]]
		- [[#Analisi#Esempio moltiplicazione - 2|Esempio moltiplicazione - 2]]
		- [[#Analisi#Esempio moltiplicazione - 3|Esempio moltiplicazione - 3]]
			- [[#Esempio moltiplicazione - 3#Dimostrazione per induzione su i|Dimostrazione per induzione su i]]
- [[#Lezione 3 - 30/09|Lezione 3 - 30/09]]
	- [[#Lezione 3 - 30/09#Esempio potenza - 1|Esempio potenza - 1]]
	- [[#Lezione 3 - 30/09#Esempio potenza con equazione di ricorrenza - 2|Esempio potenza con equazione di ricorrenza - 2]]
		- [[#Esempio potenza con equazione di ricorrenza - 2#Equazione di ricorrenza|Equazione di ricorrenza]]

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

```
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

```
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

```
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