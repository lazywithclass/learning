---
cssclasses:
  - cornell-note
tags:
  - italian
---

Debuggare o capire codice e' 3 volte piu' difficile che scrivere codice, quindi se qualcuno scrive codice al massimo della sua capacita' e' per definizione incapace di debuggarlo o capirlo.
Quindi scrivi codice semplice.

# Terminologia

<span class="b">Convalida</span>: confronto del software con i requisiti informali posti dal committente.
Test di accettazione.

<span class="b">Verifica</span>: confronto del software con le specifiche formali prodotte dall'analista.
Test di unita'.

## "Bug"

<span class="b">Malfunzionamento (failure)</span>: funzionamento non corretto del programma, non del suo codice. 
Quindi dire "c'e' un malfunzionamento alla riga 42 e' un uso improprio del termine".
Dire "invocando somma con 1 e 2 produce un malfunzionamento perche' si ottiene 2"
Non e' il comportamento previsto. Ad esempio l'Ariane 5 che esplode e' chiaro sia un malfunzionamento.

<span class="b">Difetto o anomalia (fault)</span>: legato al codice, e' condizione necessaria (ma non sufficiente) per il verificarsi di un malfunzionamento.
Ad esempio se ho una funzione raddoppia che fa 
```java linenos:1
int raddoppia(int n) {
  return n * n;
}
```
non sempre ritorna un risultato sbagliato, per `2` e' corretta, per `3` no.
Pericolose perche' sembra stiano funzionando.
Sempre in Ariane 5 anomalia e' stata la conversione di un 64bit a 16bit del valore della velocita' orizzontale.

<span class="b">Sbaglio (mistake)</span>: causa di una anomalia. In genere si tratta di un errore umano (concettuale, battitura, scarsa conoscenza del linguaggio).
Il riutilizzo della parte incriminata dall'Ariane 4 per l'Ariane 5, perche' Ariane 4 raggiungeva velocita' orizzontali non rappresentabili con 16bit.
E' possibile evitare che si ripeta, con dei processi.

## Tecniche

<span class="b">Tecniche statiche</span>: basate sull'analisi del codice:
* metodi formali
* analisi data flow
* modelli statistici

<span class="b">Tecniche dinamiche</span>: basate sull'esecuzione del programma eseguibile
* testing
* debugging (non di verifica)

Classificazione delle tecniche:
* simplified properties: una versione semplificata del programma e' corretto
* optimistic inaccuracy: "non sono sicuro, ma se non riesco a dimostrarti che non va bene allora va bene", che e' cio' che fa il testing
* pessimistic inaccuracy: "se non riesco a dimostrarti formalmente che non c'e' quell'errore per me e' come se ci fosse", metodi formali

Metodi formali: tecniche che si prefiggono di provare l'assenza di anomalie nel prodotto finale
Testing: tecniche che si prefiggono di rilevare malfunzionamenti, o fornire fiducia nel prodotto
Debugging: tecniche che si prefiggono di localizzare le anomalie che causano malfunzionamenti rilevati in precedenza

# Testing

Testing e' verifica di correttezza o validazione di affidabilita'.

Un programma si puo' dire corretto se aderisce alle specifiche per ogni dato appartenente al dominio di ingresso.

Un test ha successo quando riesce a rilevare uno o piu' malfunzionamenti presenti nel programma che non fossero gia' noti.
Test ideale: se il superamento del test (quindi il suo non successo) implica la correttezza del programma.

## Criterio di selezione test

Presi due test un criterio si dice <span class="b">affidabile</span> se entrambi i test hanno successo o entrambi falliscono.

$affidabile(C, P) \leftrightarrow \newline (\forall T_{1} \in C, \forall T_{2} \in C \ successo(T_{1}, P) \leftrightarrow successo(T_{2},P))$

Presi due test un criterio si dice <span class="b">valido</span> se, qualora $P$ non sia corretto, allora esiste almeno un $T$ selezionato in base a $C$ che a successo per il programma $P$.

$valido(C,P) \leftrightarrow (\neg ok(P, D) \rightarrow \exists T\in C successo(T,P))$

### Esempio

Dato

```java linenos:1
int raddoppia(int n) {
  return n * n;
}
```

Un criterio che seleziona sottoinsiemi di $\{ 0,2\}$ e' affidabile ma non valido.
Un criterio che seleziona sottoinsiemi di $\set{0,1,2,3,4}$ e' non affidabile ma valido.
Un criterio che seleziona sottoinsiemi finiti di $D$ con almeno un valore maggiore di $18$
e' affidabile e valido.

## Utilita' di un test

Dobbiamo trovare una metrica che misuri la copertura di un criterio e ci permetta di
* decidere quando smettere
* decidere quale altro caso di test aggiungere per aumentare la copertura
* confrontare la bonta' di test diversi

Un caso di test per evidenziare un malfunzionamento causato da una anomalia deve:
1. eseguire il comando che contiene l'anomalia 
2. il punto precedente deve portare il sistema in uno stato scorretto
3. il punto prececente deve portare a produrre un output diverso da quello atteso

Deriva dal punto 1. che voglio assicurarmi di stimolare tutti i comandi.
