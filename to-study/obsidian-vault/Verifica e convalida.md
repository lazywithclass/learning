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

<span class="b">Malfunzionamento</span>: funzionamento del programma, non del suo codice. 
Quindi dire "c'e' un malfunzionamento alla riga 42 e' un uso improprio del termine".
Dire "invocando somma con 1 e 2 produce un malfunzionamento perche' si ottiene 2"
Non e' il comportamento previsto. Ad esempio l'Ariane 5 che esplode e' chiaro sia un malfunzionamento.

<span class="b">Difetto o anomalia</span>: legato al codice, e' condizione necessaria (ma non sufficiente) per il verificarsi di un malfunzionamento.
Ad esempio se ho una funzione raddoppia che fa 
```java linenos:1
int raddoppia(int n) {
  return n * n;
}
```
non sempre ritorna un risultato sbagliato, per `2` e' corretta, per `3` no.
Pericolose perche' sembra stiano funzionando.
Sempre in Ariane 5 anomalia e' stata la conversione di un 64bit a 16bit del valore della velocita' orizzontale.

<span class="b">Sbaglio</span>: causa di una anomalia. In genere si tratta di un errore umano (concettuale, battitura, scarsa conoscenza del linguaggio).
Il riutilizzo della parte incriminata dall'Ariane 4 per l'Ariane 5, perche' Ariane 4 raggiungeva velocita' orizzontali non rappresentabili con 16bit.

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