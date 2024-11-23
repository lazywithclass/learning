# CORSO INGEGNERIA DEL SOFTWARE A.A. 2024/25

## LABORATORIO 7 (VALUTATO)

* TEAMMATE 1: <Cognome> <Nome> <matricola>
* TEAMMATE 2: <Cognome> <Nome> <matricola>

Ogni coppia di studenti effettua il **fork** di questo repository.
L'utente che ha effettuato il fork modifica questo README inserendo le opportune **informazioni sui
membri del team** seguendo lo schema sopra riportato.
Inoltre, concede i permessi di scrittura al proprio compagno di team e i **permessi di lettura** ai
docenti (`carlo.bellettini` e `mattia.monga`).

## Scartino a 2

### Il gioco

Si deve implementare una versione semplificata del gioco [Scartino](https://it.wikipedia.org/wiki/Scartino) nella variante a 2 giocatori.

Il gioco necessita di un mazzo di 40 carte, divise in quattro semi, ciascuno dei
quali con i 10 valori Asso, 2, 3, 4, 5, 6, 7, Fante, Cavallo, Re. All'inizio della
partita vengono distribuite 3 carte a ciascuno dei due giocatori che si alterneranno nelle giocate.

Il giocatore di turno, cala una carta (attacco) e di seguito fa lo stesso l'altro (risposta),
costituendo così una "passata". 

Vince e raccoglie le carte chi ha giocato la carta più alta di seme o (a parità di seme) la carta più alta di valore.
L'ordine dei semi è (in ordine crescente) Coppe, Bastoni, Spade, Denari.
Ci sono due eccezioni a questa regola:
- se almeno una delle due carte è una figura, nessuno vince la passata che viene quindi scartata.
- altrimenti se solo una delle due carte è un cinque, vince colui che l'ha giocata indipendentemente dai semi. 

Al termine della passata ciascun giocatore pesca una nuova carta dal mazzo (se non è esaurito).
Cambia l'attaccante solo se chi prima difendeva ha vinto la presa.

Si procede così fino a utilizzare tutte le carte. 
Il punteggio finale della partita è calcolato valutando le prese di ciascuno, attribuendo a ogni carta presa il 
suo valore numerico (Asso=1, Due=2, ... Sette=7, le figure valgono 0).

# Codice

Vengono fornite già diverse classi da completare (vedi i `TODO` nei commenti nel codice fornito).

Creare le classi necessarie a simulare una singola partita di "Scartino a due" con 
possibilità di farsi calcolare il punteggio finale e proclamare il vincitore.

### Processo

Una volta effettuato il **clone** del repository, il gruppo completa l'implementazione seguendo la *metodologia TDD*; 
in maggior dettaglio, ripete i passi seguenti fino ad aver implementato tutte le funzionalità richieste:

* scelta la prossima funzionalità richiesta da implementare, inizia una feature di gitflow
* implementa un test per la funzionalità,
* verifica che **il codice compili correttamente**, ma l'**esecuzione del test fallisca**; solo a questo punto effettua un *commit*
  (usando `IntelliJ` o `git add` e `git commit`) iniziando il messaggio di commit con la stringa `ROSSO:`,
* aggiunge la minima implementazione necessaria a realizzare la funzionalità, in modo che **il test esegua con successo**; solo a questo punto
  effettua un *commit* (usando `IntelliJ` o `git add` e `git commit`) iniziando il messaggio di commit con la stringa `VERDE:`,
* procede, se necessario, al **refactoring** del codice, accertandosi che le modifiche non
  comportino il fallimento di alcun test; solo in questo caso fa seguire a ogni
  passo un *commit* (usando `IntelliJ` o `git add` e `git commit`)
  iniziando il messaggio di commit con la stringa `REFACTORING:`,
* ripete i passi precedenti fino a quando non considera la funzionalità realizzata nel suo complesso e allora chiude la feature di gitflow
* effettua un *push* dei passi svolti su gitlab.di.unimi.it con `IntelliJ` o`git push --all`.

**Controllate ad ogni commit diverso da ROSSO, e in special modo prima di chiudere una feature, 
che il grado di copertura dei comandi sia prossimo al 100%.**

### RELEASES

Durante lo sviluppo avete alcune release da fare (con gitflow e pushare su gitlab):

- prima release quando per la prima volta chiudete una feature dopo le 16:30
- seconda release quando per la prima volta chiudete una feature dopo le 17:30
- ultima release quando consegnate PRIMA delle 18:30.

Al termine del laboratorio impacchettate l'ultima versione stabile come una
release di gitflow chiamata "consegna" comprendente tutte le feature  completate,
poi effettuate un ultimo *push* anche di tutti i
rami locali (comprese eventuali feature aperte ma non completate). Suggeriamo di
**verificare su gitlab.di.unimi.it** che ci sia la completa traccia dei *commit*
effettuati e di averne dato visibilità ai docenti.


## Pseudo struttura di un possibile main

```java
public class Main {
  public static void main(String[] args) {
    Player carlo = new Player("Carlo");
    Player mattia = new Player("Mattia");
    
    //TODO: set delle strategie dei giocatori
    
    Game game = new Game(carlo, mattia);
    System.out.println(game.playGame());
  }
}
```

# NOTE

Per ogni coppia può essere usato un solo device (computer, telefono, tablet, schermo...).

Potete consultare appunti, libri, siti web, comprese chat con AI (tipo chatGPT...) ma non potete scambiare informazioni,
parlare, scrivere a, porre domande, ricevere aiuti o suggerimenti da parte di persone interne o esterne alla classe che 
non siano il vostro compagno di coppia,i professori o il tutor del corso.

Chiudete quindi subito (pena esclusione immediata e non trattabile dalla prova e eslusione dall'accesso alla rete in 
tutte le prove successive) programmi/pagineWeb tipo telegram, whatsapp, email, slack, zulip, forum, ...