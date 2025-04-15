---
cssclasses: 
tags:
  - italian
  - testing
---
Debuggare o capire codice e' 3 volte più  difficile che scrivere codice, quindi se qualcuno scrive codice al massimo della sua capacita' e' per definizione incapace di debuggarlo o capirlo.
Quindi scrivi codice semplice.

## Terminologia

<span class="b">Convalida</span>: confronto del software con i requisiti informali posti dal committente.
Test di accettazione.

<span class="b">Verifica</span>: confronto del software con le specifiche formali prodotte dall'analista.
Test di unita'.

### Malfunzionamento (guasto / failure)

Funzionamento non corretto del programma, non del suo codice. 
Esterno al sistema.

Quindi dire "c'e' un malfunzionamento alla riga 42" e' un uso improprio del termine.
Dire "invocando somma con 1 e 2 produce un malfunzionamento perche' si ottiene 2"
Non e' il comportamento previsto. Ad esempio l'Ariane 5 che esplode e' chiaro sia un malfunzionamento.

### Difetto (anomalia / fault)

Legato al codice, e' condizione necessaria (ma non sufficiente) per il verificarsi di un malfunzionamento.
Ad esempio se ho una funzione raddoppia che fa 
```language-java
int raddoppia(int n) {
  return n * n;
}
```
non sempre ritorna un risultato sbagliato, per `2` e' corretta, per `3` no.
Pericolose perche' sembra stiano funzionando.
Sempre in Ariane 5 anomalia e' stata la conversione di un 64bit a 16bit del valore della velocita' orizzontale.

### Sbaglio (mistake)

Causa di un difetto. In genere si tratta di un errore umano (concettuale, battitura, scarsa conoscenza del linguaggio).
Il riutilizzo della parte incriminata dall'Ariane 4 per l'Ariane 5, perche' Ariane 4 raggiungeva velocita' orizzontali non rappresentabili con 16bit.
E' possibile evitare che si ripeta, con dei processi.

### Tecniche

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

## Testing

Testing e' verifica di correttezza o validazione di affidabilita'.

Un programma si puo' dire corretto se aderisce alle specifiche per ogni dato appartenente al dominio di ingresso.

Un test ha successo quando riesce a rilevare uno o piu' malfunzionamenti presenti nel programma che non fossero gia' noti.

Test ideale: se il superamento del test (quindi il suo non successo) implica la correttezza del programma
$$
\begin{align}
&\ \lnot successo(T,\ P)\ \rightarrow\ ok(P,\ D) \newline
\end{align}
$$

### Criterio di selezione test

Presi due test un criterio si dice <span class="b">affidabile</span> se entrambi i test hanno successo o entrambi falliscono.

$$affidabile(C, P) \leftrightarrow \newline (\forall T_{1} \in C, \forall T_{2} \in C \ successo(T_{1}, P) \leftrightarrow successo(T_{2},P))$$

Presi due test un criterio si dice <span class="b">valido</span> se, qualora $P$ non sia corretto, allora esiste almeno un $T$ selezionato in base al criterio $C$ che ha successo per il programma $P$, quindi che rileva uno o piu' malfunzionamenti
$$valido(C,P) \leftrightarrow (\neg ok(P, D) \rightarrow \exists T\in C |  successo(T,P))$$

#### Esempio

Dato

```language-java
int raddoppia(int n) {
  return n * n;
}
```

Un criterio che seleziona sottoinsiemi di $\{ 0,2\}$ e' affidabile ma non valido.<br />
Un criterio che seleziona sottoinsiemi di $\{0,1,2,3,4\}$ e' non affidabile ma valido.<br />
Un criterio che seleziona sottoinsiemi finiti di $D$ con almeno un valore maggiore di $18$ e' affidabile e valido.

#### Tutto  bello, ma...

 Cosa succede se ho un test affidabile e valido? ($\lnot successo(T,\ P)$ vuol dire che non trova problemi)
$$
\begin{align} \begin{gathered} affidabile(C,\ P)\ \land\ valido(C,\ P)\ \land\ \exists T\in C\ \land\ \lnot successo(T,\ P) \\ \Rightarrow \\ ok(P,\ D) \end{gathered} \end{align}
$$

Quindi:

* siccome e' affidabile se uno non trova, allora tutti non trovano
* siccome e' valido se il programma fosse stato sbagliato allora almeno un test avrebbe dovuto trovare problema
* esiste (o per ogni) non ha successo

Allora implica che il programma e' corretto, ma questa e' la definizione di test ideale, che abbiamo detto che non esiste.
Quindi non può esistere un criterio che sia contemporaneamente affidabile e valido! 

Quindi l'unico caso in cui ho $affidabile$ a priori senza conoscenza del test e' quando sto selezionando un unico test.

Quindi l'unico caso in cui ho $valido$ a priori senza conoscenza dei test e' quando sto selezionando infiniti test.

Vogliamo quindi arrivare ad avvicinarci.

### Utilita' di un test

Dobbiamo trovare una metrica che misuri la copertura di un criterio e ci permetta di

* decidere quando smettere
* decidere quale altro caso di test aggiungere per aumentare la copertura
* confrontare la bonta' di test diversi

Un caso di test per evidenziare un malfunzionamento causato da una anomalia deve:

1. eseguire il comando che contiene l'anomalia 
2. il punto precedente deve portare il sistema in uno stato scorretto
3. il punto prececente deve portare a produrre un output diverso da quello atteso

Deriva dal punto 1. che voglio assicurarmi di stimolare tutti i comandi.

Voglio che i miei test siano limitati in merito al cammino che coprono, in modo tale che mi sia più agevole utilizzarli come punto di partenza per il debugging.

#### Criterio di copertura dei comandi (una riga)

Un test $T$ soddisfa il criterio di copertura dei comandi se e solo se ogni comando eseguibile del programma e' eseguito in corrispondenza di almeno un caso di test $t \in T$.

Soddisfare questo criterio non garantisce la correttezza del programma, esegue semplicemente tutte le righe di codice raggiungibili.

#### Criterio di copertura delle decisioni ( `if`, `while`, ...)

Un test $T$ soddisfa il criterio di copertura delle decisioni se e solo se ogni decisione effettiva viene resa sia vera che falsa in corrispondenza di almeno un caso di test $t \in T$.

Implica il criterio di copertura dei comandi.

#### Criterio di copertura delle condizioni (esempio una delle condizioni di un `if`)

Un test $T$ soddisfa il criterio di copertura delle condizioni se e solo se ogni singola condizione effettiva viene resa sia vera che falsa in corrispondenza di almeno un caso di test $t \in T$.

Si differenzia dal precedente [Criterio di copertura delle decisioni](#Criterio%20di%20copertura%20delle%20decisioni) perché si riferisce alle singole condizioni, non a tutta l'espressione oggetto di valutazione della condizione.

Non implica il criterio di copertura dei comandi.

#### Criterio di copertura delle decisioni e delle condizioni

Un test $T$ soddisfa il criterio di copertura delle decisioni e delle condizioni se e solo se ogni decisione vale sia vero che falso e ogni condizione che compare nelle decisioni del programma vale sia vero che falso per diversi casi di test $t \in T$.

E' l'intersezione di [Criterio di copertura delle decisioni](#Criterio%20di%20copertura%20delle%20decisioni) (e quindi del [Criterio di copertura dei comandi](#Criterio%20di%20copertura%20dei%20comandi)) e [Criterio di copertura delle condizioni](#Criterio%20di%20copertura%20delle%20condizioni).

#### Criterio di copertura delle condizioni composte

Un test $T$ soddisfa il criterio di copertura delle condizioni composte se e solo se ogni possibile composizione delle condizioni base vale sia vero che falso per diversi casi di test $t \in T$.

Per esempio per la condizione `x != 0 && y < 3`, vengono testati separatamente i casi $\langle V,V \rangle, \langle V,F \rangle, \langle F,V \rangle, \langle F,F \rangle$.

Data la natura combinatoria del criterio e' considerato non applicabile in pratica, oltre che alcune combinazioni potrebbero non avvenire, e quindi non avrebbe alcun senso testarle.

#### Criterio di copertura delle condizioni e delle decisioni modificate

Conto solo le combinazioni piu' rilevanti, cioe' quelle per cui la modifica di una condizione base porti a modificare l'esito della decisione.

Permette quindi di testare solamente per quei valori significativi che vanno a far cambiare la decisione.

#### Criterio di copertura dei cammini

Un test $T$ soddisfa il criterio di copertura dei cammini se e solo se ogni cammino del grafo di controllo del programma viene percorso per almeno un caso di $t \in T$.

Non applicabile in pratica.

#### Criterio di $n$-copertura dei cicli

Un test $T$ soddisfa il criterio di $n$-copertura se e solo se  per ogni ciclo, abbiamo che viene eseguito $0$, $1$, ..., $n$ volte per almeno un caso di test.

Il criterio viene spesso applicato nella forma $2$-copertura dei cicli:
* zero iterazioni
* una iterazione
* piu' di una iterazione

#### Implicazioni tra criteri

![Implicazioni tra criteri](attachments/Pasted%20image%2020250330182124.png)

## Analisi Data Flow (DF)

E' analisi statica. Gli elementi da analizzare non sono infiniti come invece possono esserlo gli elementi nell'analisi dinamica.

Serve a capire come ottimizzare il codice, e per identificare potenziali errori.

Ci sono tre operazioni:

* $d$ (definizione): il comando assegna un valore alla variabile, stesso dicasi per quando una variabile viene passata come parametro
* $u$ (uso): il comando legge il contenuto di una variabile
* $a$ (annullamento): il comando rende il valore della variabile invalido

Analizziamo le operazioni su `x`, `x1`, `x2`

```language-c
void swap(int &x1, int &x2) {
    int x1; // (a) - shadowing di x1 parametro
    x3 = x1; // (u)
    x3 = x2;
    x2 = x1; // (u)
} // (a) - scope di x1 terminato
```

| variabile | DF        |
| --------- | --------- |
| x         | auua      |
| x1        | ...dud... |
| x2        | ...ddd... |
`x` viene usata 2 volte senza essere prima stata definita. `x2` viene definita più volte senza essere usata nel frattempo.

Lista di stati non corretti:

* aa
* au
* da
* dd

### Criterio di copertura delle definizioni

$def(i)$ insieme delle variabili definite nel nodo $i$
$du(x,\ i)$ e' l'insieme dei nodi $j$ tali che:

* $x\in def(i)$
* $x$ usato in $j$
* esiste un cammino da $i$ a $j$, libero da definizioni di $x$

Un test $T$ soddisfa il criterio di copertura delle definizioni se e solo se per ogni nodo $i$ e ogni variabile $x$ appartenente a $def(i)$ $T$ include un caso di test che esegue un cammino libero da definizioni da $i$ ad almeno uno degli elementi di $du(x,\ i)$.

Ovvero, in termini umani: esiste almeno un uso di quella definizione.

Esempio

```language-c
void main() {
	float a, b, x, y;
	read(x);
	read(y);
	a = x;
	b = y;
	while (a != b)
		if (a > b)
			a = a - b;
		else
			b = b - a;
	write(a);
}
```

Considerando la variabile `a`TODO AGGIUNGERE LINE NUMBERS

$du(a,\ 5) = \{7,8,9,11,12\}$
$du(a,\ 9) = \{7,8,9,11,12\}$

* $d_5u_7$ viene gratis
* $d_9u_7$ basta entrare una volta nel ciclo

CHIEDERE A NOTEBOOK ULTERIORI ESEMPI E DOMANE SU QUESTA PARTE

### Criterio di copertura degli usi

Un test $T$ soddisfa il criterio di copertura degli usi se e solo se per ogni nodo $i$ e ogni variabile $x$ appartenente a $def(i)$ per ogni elemento $j$ di $du(x,\ i)$ $T$ include un caso di test che esegue un cammino libero da definizioni da $i$ a $j$.

Non copre istruzioni che non sono usi.

CHIEDERE A NOTEBOOK ULTERIORI ESEMPI E DOMANE SU QUESTA PARTE

Esempio

```language-c
void main() {
	float a, b, x, y;
	read(x);
	read(y);
	a = x;
	b = y;
	while (a != b)
		if (a > b)
			a = a - b;
		else
			b = b - a;
	write(a);
}
```

Considerando ancora la variabile $a$

$du(a,\ 5) = \{7,8,9,11,12\}$
$du(a,\ 9) = \{7,8,9,11,12\}$

$d_5u_7u_8u_{11}u_7u_{12}$

CHIEDERE A NOTEBOOK DI FARE UN ESEMPIO Di $d$

## Bebugging

Inseriamo $n$ errori dentro il codice prima di mandare il programma a chi lo deve testare. Chi testa sa che ci sono $n$ errori ma non sa dove sono.
Quindi nel mentre cerca questi errori ci sono ottime probabilità che ne trovi altri.

Tipicamente in questo scenario c'e' un team di testing separato dal team di sviluppo.

Problemi

* potrebbe essere che ho messo errori più semplici di quelli che realmente voglio trovare
* potrei usare strumenti sbagliati per trovare i bug

## Analisi mutazionale

Viene generato un insieme di programmi $\Pi$ simili al programma $P$ in esame.

Su di essi viene eseguito lo stesso test $T$ previsto per il programma $P$:

* se $P$ e' corretto allora i programmi in $\Pi$ devono essere sbagliati
* per almeno un caso di test devono quindi produrre un risultato diverso

Un test $T$ soddisfa il criterio di copertura dei mutanti se e solo se per ogni mutante $\pi \in \Pi$  esiste almeno un caso di test in $T$ la cui esecuzione produca per $\pi$ un risultato diverso da quello prodotto da $P$.

La metrica e' la frazione di mutanti riconosciuta come diversa da $P$ sul totale di mutanti generati.

Voglio un mutante per ogni possibile difetto, virtualmente infiniti. I più semplici effettuano modifiche sintattiche che comportino modifiche semantiche.

L'onere di esecuzione e' molto forte.

## Testing ed ereditarietà, testing e collegamento dinamico

https://youtu.be/mZ8vBaHtzlY?t=771

Chiedere a NOTEBOOK di espandere questa parte

## Class testing

Isolare la classe: costruiamo stub per renderla eseguibile indipendentemente dal contesto

I mock consentono di parallelizzare lo sviluppo di componenti che dipendono l'una dall'altra, a patto di avere dei contratti, espressi con le interfacce.

## Copertura della classe

Chiedere a NOTEBOOK

## Test funzionale

Sinonimo di blackbox.

Punto di partenza con cui effettuare ragionamenti per la copertura. Parto dai requisiti.