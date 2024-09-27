---
cssclasses:
  - cornell-note
tags:
  - algorithms
  - italian
---

> [!info]
> This will be mainly in italian

[[Algoritmi e strutture dati#Lezione 1 - 25/09]]
[[Algoritmi e strutture dati#Lezione 2 - 27/09]]

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

Differenza algoritmo e programma

Programma e' espressione dell'algoritmo nel linguaggio di programmazione

Programma

Insieme ordinato e finito
di istruzioni scritte secondo le regole
di uno specifico linguaggio di programmazione

```java
ALGORITMO moltiplicazione(intero a, intero b) -> intero
  RETURN a * b
```


Sintesi

Dal problema progettare l'algoritmo

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

somme iterate

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
TODO Dimostrarlo per induzione

|Quando     |   Linee eseguite  | Tempo |
| --- | --- | --- |
|b = 0     | 1, 2, 5    | 3 |
|b > 0     | 1, 5 1 volta | 2 |
|b > 0     | 3, 4 b volte | 2b |
|b > 0     | 2 b + 1 volte | b + 1 |

Per b = 0 totale 3
Per b > 0 totale 3b + 3

#### Esempio moltiplicazione - 3

19 * 114

```
* 2     / 2
19      114
38       57 
76       28
152      14
304       7
608       3
1216      1
```

Prendo un valore dalla colonna di sinistra quando destra e' dispari

38 + 304 + 608 + 1216 = 2166

a * b = 2a * b / 2 per b pari
a * b = 2a * (b - 1) / 2 + a per b dispari

Perche' +a?
Comunque quel +a e' proprio il +a che si fa prendendo dai dispari

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

Proprieta' ai * bi + prodi = a * b
TODO dimostrare per induzione

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

Quindi T(a,b) = 5 logb + 8 
crescita logaritmica