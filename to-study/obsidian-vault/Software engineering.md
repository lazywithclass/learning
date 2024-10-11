---
cssclasses:
  - cornell-note
tags:
  - software-engineering
  - italian
---

- [[#Refresh di Programmazione II|Refresh di Programmazione II]]
- [[#Domande orale|Domande orale]]
- [[#Lezione 1 - 25/09|Lezione 1 - 25/09]]
	- [[#Lezione 1 - 25/09#Logistica|Logistica]]
	- [[#Lezione 1 - 25/09#Problemi|Problemi]]
	- [[#Lezione 1 - 25/09#Qualita'|Qualita']]
		- [[#Qualita'#Funzionare|Funzionare]]
			- [[#Funzionare#Correttezza|Correttezza]]
			- [[#Funzionare#Affidabilita'|Affidabilita']]
			- [[#Funzionare#Innocuita' / robustezza|Innocuita' / robustezza]]
		- [[#Qualita'#Bello|Bello]]
		- [[#Qualita'#Farmi ricco|Farmi ricco]]
	- [[#Lezione 1 - 25/09#Processo|Processo]]



SISTEMARE DOPO LAB

// null? code smell  
// nested classes  
// metodi per esplicitare cosa stiamo facendo  
// posso avere un nodo che non contiene un valore? meglio di no  
// Objects.requireNonNull  
  
// privilegiare assert, il compilatore le leva direttamente in prod  
// flag EA del compiler

https://www.memory.com/courses/dashboard/ingegneria-del-software-it-it--ao8b_1695973981615185115_73/Italian/Italian

# Refresh di Programmazione II

https://prog2unimi-temi-svolti.netlify.app/

********************

Orali 26/01




Review del codice
“Controllato in costruzione” non “preservato in costruzione”
Documentazione metodi che contenga anche ad esempio per quali input uno split di un orario formattato con 00:00:00 non funziona
Perché ha usato getClass e non ha usato instanceof nell’equals?
Brano statico con String album come campo, discussione
Coerenza documentazione - codice
Ritiene appropriato lanciare una eccezione se non trova un elemento in una lista?
List.copyOf cosa ritorna?
Cosa sono le eccezioni cosa servono quando e’ opportuno sollevare checked e unchecked?




Review del codice
        Catch di Exception “e’ bruttarello”
        Perche’ getClass dentro l’equals?
        Differenza tra StringBuilder e + tra stringhe per poche concatenazioni
FA e RI
Effetto collaterale benevolo
Come si mescola RI e sottoclassi? super.repOK && repOK, ok ma ci sono dei casi in cui non controllare gli attributi del supertipo puo’ essere ritenuto ragionevole?
Cosa si intende per attributi del supertipo? Quando si ricorre agli attributi protected? Attributi di una classe astratta, quali sono le circostanze in cui e’ preferibile public, protected, o private?




Review del codice
Non fare troppe eccezioni che siano sottoclassi di IllegalArgumentException
        Come si costruisce un buon iteratore?
        Assumendo che durataComplessiva dentro Album scorre tutti gli i brani per dare il risultato, si puo’ fare meglio?
Metodi totali e parziali, cosa sono, quando si usano, pregi e difetti
Insieme di regole che le pre-post condizioni devono soddisfare perche’ valga LSP
Astrazione per specificazione, perche’ sono importanti le proprieta’ caratteristiche di questa astrazione
        


Review del codice
Legame tra hashcode e equals, qual e’ il verso dell’implicazione? Cosa sarebbe successo se hashcode uguale implicasse equals?
Cosa sono FA e RI? In che contesto ne parliamo e perche’ li usiamo? Con che proposito vengono introdotte? Esempio di iniettivita’ della FA
Principio di sostituzione di Liskov
Covarianza?
Astrazione per iterazione


Consigli dal prof
Prima di consegnare scrivete il main per vedere se il codice lancia eccezioni
Assicurarsi che la documentazione sia in linea con l’implementazione
repOK non dovrebbe mai sollevare eccezioni
Allo scritto dovete principalmente cercare di arrivare all’orale, contenere le cagate e poi fare l’orale

*******************



https://github.com/Pasinim/prog2/tree/main/.-Dispense/orali

dispatching

metodi totali parziali

AF RI

Benevolent side effect

eccezioni

Program Development in Java
- 5.7 Reasoning about Data Abstractions
- 5.8 Design Issues
- 5.9 Locality and Modifiability
- 9.2 Some Criteria for Specifications

Sidebar 
1.1, 1.2, 2.2, 2.3, 2.4, 3.1, 3.2, 3.3, 4.1, 4.2, 5.1, 5.2, 5.3, 5.4, 5.5, 5.6, 6.1, 6.2, 6.3, 7.1, 7.2, 7.3, 7.4, 7.5, 7.6, 7.8, 
8.1, 8.2, 9.1, 9.2, 9.3, 

capitoli 13 - 14 - 15 da leggere e basta

Equals e ereditarieta' che lo rompe


Differenza tra avere assert in dev e non averla in prod
Discorso dell'assunzione

classi e tutte le posizioni posizioni
nested
non nested una che utilizza l'altra
nested statica
per ognuna use case e cosa comporta 

https://prog2-unimi.github.io/notes/TGERDS.html
https://prog2-unimi.github.io/notes/DM.html
https://prog2-unimi.github.io/notes/EACO.html
https://prog2-unimi.github.io/notes/UEE.html
https://prog2-unimi.github.io/notes/CED.html

- reference escaping (che se presente invalida tutta la encapsulation)
- interface segregation
- nullability
- mocking
- fattorizzazione codice tramite pattern strategy o template

- astrazione (tramite procedure e tipi di dati),
- incapsulamento (tramite classi concrete/astratte e interfacce),
- estensibilità (per ereditarietà, o composizione),
- polimorfismo (e tipi generici).

- l'uso di **astrazioni** di vario livello (come i metodi, i tipi di dato astratti, l'iterazione esterna, l'estensione),
    
- alcuni **strumenti concettuali** per ragionare su tali astrazioni (come l'_invariante di rappresentazione_, la _funzione di astrazione_, le _pre-_/_post-condizioni_, gli _effetti collaterali_, l'_induzione sui tipi di dato_…),
    
- alcuni **criteri di valutazione della qualità** del progetto di codice _object oriented_ (come l'_incapsulamento_, il _data hiding_, la _manutenibilità_, il _riuso_ e l'_estendibilità_),


- per quanto concerne il libro di testo [Effective Java](http://www.informit.com/store/effective-java-9780134685991) i capitoli 2, 3, 4, 5 (eccetto gli _item_ 32 e 33), 8 (eccetto gli _item_ 53 e 55), 9 (eccetto l'_item_ 66), 10;

# Domande orale

- due principi solid (approfondimento poi sui due principi)
- stime di tempi nell'ambito di un progetto
- quando un criterio di selezione è valido e quando è affidabile 
- relazioni di conflitto tra transizioni

primo
- liskov substitution principle
- interface segregation
- problemi nella stima dei tempi e come risolverli
- parlami di analisi statica e analisi data flow
- legame tra limitatezza e conservatività

secondo
- legame tra limitatezza e conservatività (continua...)
- cosa è refactoring?
- terminologia degli errori
- modelli di sviluppo a spirale

terzo
- mocking e differenza tra stub e mock
- modello prototipale vs modello incrementale
- meta-pattern e pattern composite
- albero di copertura vs abero di raggiungibilità

quarto
- cosa è un test ideale?
- criteri di selezione: proprietà
- tell dont ask
- due pratiche xp per convincere il manager
- differenza semantica debole e forte

1 orale (28 -> bocciato):
- pattern decorator
- pattern composite

2 orale (24 -> 23):
- la parte di processi. Cos’è il tdd e perché è una buona tecnica
  - tecnica di requisiti o tecnica di design o di progettazione
  - cosa vuol dire che ho fatto uno sviluppo tdd e poi mi trovo una copertura al 70% del codice
- differenza tra verifica e convalida
- pattern strategy
  - Quale anti-pattern cerca di risolvere?
  - Quali sono i 5 principi solidi?
  - perché l’open closed principal è legato a questo pattern
- disegnato una rete di petri, fare l’albero di copertura
  - scrivere formalmente la regola di abilitazione

3 orale (28 -> bocciato):
- pattern decorator:
  - domande sulle frecce ecc.
  - quale problema risolve?

4 orale (bocciato):
- scegli due pratiche xp per convinvermi che è una buona cosa:
- cosa si intende per test ideale e perché ne abbiamo parlato?
  - quali caratteristiche?
- diagrammi di sequenza

Primo orale: 1. Che cos’è la nullability? Perché è importante? Qual è la caratteristica del valore null che “da fastidio”? 2. Processi, parlami positivamente del modello a cascata. Modelli di ciclo del software (descrittivo). Come nasce il modello a cascata? 3. Terminologia di base di verifica e convalida. Sbaglio, errore, difetto, anomalia…, esempio in cui si presenta anomalia ma non (difetto o errore (?)) 4. Prova a scrivermi la relazione di conflitto, relazione di concorrenza (fai esempi e scrivi in maniera formale) evidenzia strutturale/effettivo Secondo orale: 1. Inserisco un valore che sfora, dove limito questo inserimento (model o presenter?Andava messo nel model) 2. Mi parli di criterio di selezione. Che cos’è? Perché dobbiamo scegliere nei test un sottoinsieme del loro domino (cioè non seleziono tutti i test, ma solo alcuni)? Divagato su definizione di criterio valido. La parola criterio l’abbiamo usata in altri contesti, quali? Criteri di copertura dei comandi/decisioni ecc. dimmi qualcosa su uno di questi. Esempio dove “un criterio è soddisfatto e l’altro no” 3. Da dove derivano, chi è che le scrive le specifiche? Dopo averle raccolte cosa devi fare prima di implementarle? Terzo orale: 1. Pattern, chain of responsability, interface segregation 2. Modelli iterativi e modelli sequenziali, prototipale (?) 3. boh... di una transizione. È una caratteristica strutturale? 4. Che è il TDD, come funziona? È una tecnica di specifica? Quarto orale: 1. Esempio di rete limitata. 2. Differenza tra merge e pull request. 3. Testing funzionale Quinto orale: 1. Cosa sono gli STUB? Quando devo “mockkare”? Perché non voglio usare le altre classi già presenti? Quando invece NON POSSO usare le altre classi? Problemi uso mock (ad esempio di performance, perché non simula reale tempo di risposta). Dumming/stubbing. Che funzioni usi per fare stubbing con mockito? 2. Albero di raggiungibilità vs albero di copertura. Cosa rappresenta Ω? Vari esempi 3. Onesto non ho capito, un esempio su relazioni aziendali (?)

# Lezione 1 - 25/09

## Logistica

Libri:
* Head first design patterns 
* Fundamentals of software engineering
* A handbook of software and systems engineering - ha i riferimenti alle "leggi"

Software per il lab
* git
* gradle
* intellij
* jdk 17 o 21

Turno A matricole pari (Monga), turno B matricole dispari (Bellettini)

---

Comunicazione tra sviluppatori e' un fattore importante nello sviluppo software.
Il software deve fare cio' che voglio, con qualita', per poter dire: "per come ho creato il software ti garantisco che funziona".

Voglio delle metriche che siano correlate rispetto al mio obiettivo di qualita'.
Next step, definisco un tool che controlli il rispetto di queste metriche.

## Problemi

* numero di persone coinvolte - il programmatore non e' il cliente -> comunicazione. Overhead comunicazione cresce al crescere del numero di persone che lavorano su un programma, quindi non basta tirare sviluppatori al problema
* dimensioni software, sia come linee di codice che come tempo uomo necessario per scriverle
* software - in fase di scrittura del codice e' come se la storia venisse cambiata mentre la si sta scrivendo, come se in un libro al capitolo 9 si modificasse il capitolo 1 facendo morire un personaggio

Un buon processo puo' produrre un prodotto di qualita'.

[CMMI](https://en.wikipedia.org/wiki/Capability_Maturity_Model_Integration)
Programma a livelli per stabilire il grado di maturita' di un gruppo di lavoro.
0 - dipendenza dall'eroe, come se avessi Achille come programmatore, ma cosa succede se sparisce? Dipendo da lui?
1 - ...
...

## Qualita'

Valore per gli stakeholder
Scelte: "ottimizzo sullo spazio o sul tempo?"

<aside>Qualita'</aside>

Esterne: visibili agli utenti
Interne: visibili agli sviluppatori

Un programma deve: 
* [[Software engineering#Funzionare]]
* essere bello
* farmi ricco

### Funzionare

Funziona e' diverso da compila!

#### Correttezza

Qualita' assoluta: o corretto o non corretto.
Correttezza: fa cio' che e' stato chiesto, confronto con le specifiche.

> [!info] La correttezza e' una proprieta' matematica che stabilisce l'equivalenza tra il software e la sua specifica

Differenza tra requisiti e specifiche:
* requisiti - volatili, arrivato dall'esterno, sono i desiderata del cliente
* specifiche - scritte, cosa io ho capito che il cliente voleva, contratto tra cliente e programmatore

<aside>L1</aside>

> [!info] Glass' Law (L1): Requirement deficiencies are the prime source of project failures

#### Affidabilita'

Qualita' relativa: se avviene un errore non grave il programma puo' comunque essere inteso come affidabile.
Affidabilita': mi posso fidare che faccia cio' che voglio in un dato periodo di tempo

#### Innocuita' / robustezza

Innocuita': che non faccia male
Robustezza: si comporta bene anche rispetto situazioni fuori specifica, questa e' la linea di demarcazione tra correttezza e robustezza: corretto o non corretto e' definito in base alle specifiche, robusto in base a cio' che e' fuori da esse

### Bello

Usabilita': facile da usare, non solo UI, ad esempio facilita' di configurazione di un tool da CLI
Adattabilita' all'esigenza dell'utente e' una caratteristica dell'usabilita'

<aside>L26</aside>

> [!info] Nielsen-Norman's Law (L26): Usability is quantifiable

Osservare gli utenti mentre utilizzano il programma e' un quick win.

Velocita', ed efficienza nell'uso delle risorse

Pulito
<aside>La pulizia del codice e' richiesta all'esame</aside>
Verificabilita, posso dimostrare che e' corretto

### Farmi ricco

Riusabilita' dei componenti
Rivendo un software gia' fatto per un altro cliente, lo riuso cosi non impiego tempo
Bonus point: mi porto dietro affidabilita' di quel software

<aside>L15</aside>

> [!info] McIlroy's law (L15): Software reuse reduces cycle time and increases productivity and quality

Vedi incidente Ariane5, il riuso va bene ma deve essere di qualita'

Manutenibilita' e' semplificare gli interventi post consegna, suddivisibile in
* riparabilita', se consente facilmente di eliminare i difetti (ben diviso in moduli, information hiding, no interconnessioni complesse, etc)
* evolvibilita', se facilita i cambiamenti permettendo di soddisfare nuovi requisiti

<aside>L27 L28</aside>

> [!info] Lehman's first law (L27): A system that is used will be changed

"Change is inevitable"

> [!info] Lehman's second law (L28): An evolving system increases its complexity unless work is done to reduce it

Design for change.

## Processo

> [!danger] TODO ESPANDERE

Funzionare
Essere bello 
Farmi diventare ricco

Resistere agli imprevisti - robustezza
Bello - sono veloce, produttivo
Cogliere l'attimo - tempismo

# Lezione 2 - 30/09

Produrre software:
* non solo scrivere codice
* risolvere problema di comunicazione
* essere rigorosi - ne linguaggio formale ne linguaggio "normale"

<aside>H3</aside>

> [!info] Bauer-Zemanek Hypotesis (H3): Formal methods significantly reduce design errors, or eliminate them early

Un linguaggio formale e' veramente la soluzione? Potrebbe essere molto piu' difficile del problema stesso.
Per questo e' una ipotesi, non una legge.
Va pesato sulla difficolta' del progetto.

Ci sono molti aspetti da considerare nel processo di produzione del software.

## Ciclo di vita del software

### Identificare le attivita' necessarie

Come si svolgono, e chi coinvolgono.
"Cosa devo fare adesso?"
"Fino a quando devo farlo?"

### Studio di fattibilita'

Capire il problema, ci conviene affrontare il problema? Riusciamo a diventare ricchi risolvendo questo problema?
Parte economica; parte di importanza; a quanto riusciro' a venderlo; c'e' gia' chi lo fa?
Il tempismo gioca un ruolo determinante.

Lo scrivo io? Lo faccio scrivere? Metto assieme componenti gia' esistenti?
Devo espandere il team?

Stima dei costi, dei tempi, risorse necessari. Benefici?

Chi fa lo studio di fattibilita'?

E' possibile che l'output sia "non ci conviene farlo".

### Dai requisiti alle specifiche

Comprendere il dominio applicativo.
Ok, ma noi sviluppatori non sappiamo quasi nulla di quello, infatti:

> It's developers' understanding, not domain experts' knowledge, that gets released in production

C'e' un momento in cui si parla con gli esperti, si raccoglie il contento e si consolida nelle specifiche.

<aside>Stakeholder</aside>
Gli stakeholder sono tutte quelle persone interessate che questo progetto vada a buon fine: committente, utente, manutentore, installatore, colui che dovra' farlo evolvere, ... 

<aside>Differenza requisito specifiche</aside>

Requisito: cosa deve fare il sistema
Specifiche: come deve farlo, non ambigue

In output ho:
* documento approvato dal committente
* base per il lavoro di design e verifica
Quindi e' importante avere un documento formale, non ambiguo

Due domande che il modello deve aiutare a rispondere:
* cosa devo fare adesso?
* fino a quando?

> [!info] Davis' Law (L4): The value of models depends on the view taken, but none is best for all purposes

Linguaggio che serva da ponte per descrivere il problema.

### Progettazione

Definizione dell'architettura del sistema
* scelta di una architettura software
* scomposizione in moduli o oggetti
* identificazione pattern

Programmazione e test di unita': verifica e programmazione fanno parte della stessa attivita'.

Top down
Esempio: modulo che gestisce l'interfaccia utente e chiama sotto di se dei moduli fittizi, che non fanno nulla, man mano che mi serve scambio un modulo fittizio con una implementazione.

Bottom up
Esempio: parto dal modulo che fa la radice quadrata, che viene usato dal modulo che calcola la velocita', etc. Non sono i moduli che vedra' l'utente.

### Manutenzione

Correttiva: ci sono errore e faccio modifiche che risolvono gli errori 
Adattiva: era corretta prima, ora le specifiche sono cambiate
Perfettiva: posso perfezionare una delle qualita'?

## Modello a cascata

Keyword: documento

Requisiti -> Progetto -> Codifica -> Testing -> Prodotto
C'e' una sequenzialita' stretta.

Permette la separazione dei compiti.
Semplifica la pianificazione e il monitoring dello stato di avanzamento, ma il problema e' che se ci sono dei ritardi di stima tutto slitta.

Non prevede una manutenzione, perche' tutto viene assunto come fatto bene. E' fuori dal modello.
Coinvolge il cliente solo nella parte iniziale.

## Riposizionamento modello a V

Cascata ma con interazione con utente e test a ogni livello.

## Modello a fontana

Da qualunque step posso ricadere ad uno degli step precedenti.

<aside>iterativi vs incrementali</aside>

Un modello e' incrementale quando nelle iterazioni viene inclusa la consegna, ogni volta consegno qualcosa in piu'.

<aside>definizione di consegna</aside>

Qualcosa di usabile e di valore per l'utente

Implementazione iterativa, raccolta requisiti e progettazione una volta, poi un pezzo alla volta sviluppiamo iterativamente e poi un'unica consegna. Solo la parte implementativa e' iterativa, non tutto lo sviluppo.

Sviluppo incrementale, un pezzo alla volta viene sottoposto a tutte le fasi.
Dopo aver finito un pezzo: "Bene, adesso cosa vuoi che faccia?"

I modelli incrementali hanno problemi:
* complicato il lavoro di planning, perche' lo stato del processo e' meno visibile
* bisogna pianificare tutte le iterazioni
* cos'e' e quanto dura una iterazione?


## Modelli prototipali

"Faccio male", senza soffermarmi sulle qualita'.
Voglio usare una consegna parziale per poter parlare con il cliente.
"Verra' cosi, e' cosi che lo vuoi?"

La base e' caotica e non pensata per l'evoluzione, non e' un punto di partenza valido.

Perfetto per esplorare concetti, come ad esempio un nuovo linguaggio, per ponderare diverse scelte per problemi difficili, etc

<aside>L3</aside>

> [!info] Boehm's Law (L3): Prototyping (significantly) reduces requirements and design errors, especially for user interfaces

# Lezione 3 - 02/10

## Modelli non agili

### Pinball life-cycle

1984

Flipper, posso controllare solo come lancio la pallina, per il resto passa tempo prima che la pallina ritorni a me: 
* processo indefinito
* non ci sono passi in sequenza, posso andare da un punto del processo a qualsiasi altro
* niente vincoli temporali
* non misurabile

Difficile riprodurre la stessa partita due volte.
"Non ho bisogno di un modello", vuol dire che sto adottando questo modello.

### Modelli trasformazionali

Mirano ad avere tutto sotto controllo.
Raffinamento / concretizzazione passo passo delle specifiche formali del problema.
Ogni passo garantisce che vengano mantenute le proprieta' del passo precedente.
Mi avvicino sempre piu' alla versione eseguibile delle specifiche.

TODO INSERISCI immagine dalla slide (e' attorno al 10 min di lezione)

### MetaModello a spirale

1988

Guidato dall'analisi dei rischi

Prevedere ciclicamente se ci sono rischi e decidere se andare avanti o interrompere.

Fasi
Determinazione obiettivi, alternative, vincoli
Identificare i rischi
Sviluppo e test
Pianificare iterazione successiva

Il raggio della spirale rappresenta il costo del progetto, che aumenta.

TODO Inserire immagine

### Modello spirale win-win

Ha espanso l'area dei rischi.

TODO ESPANDERE

### Modello COTS

Components off the shelf

"Non c'e' gia' qualcosa che fa quello che ci serve? Prendiamo quelli e integriamo"

Prima della progettazione c'e' una fase di ricerca dei componenti gia' esistenti.

Ricordarsi del problema dell'adattabilita' dei differenti componenti

## Metodologie agili

fine 1990

eXtreme Programming

Nascono dal basso, dagli sviluppatori, non da teorici.
Design emergente. Quindi una critica che si muove a XP e' che non c'e' un design up-front.

$$
\begin{align}
& \text{\large{Individuals and interactions} \small{over processes and tools}} \\
&\text{\large{Working software} \small{over comprehensive documentation }} \\
& \text{\large{Customer collaboration} \small{over contract negotiation}} \\
& \text{\large{Responding to change} \small{over following a plan}} \\
\end{align}
$$
[agile manifesto](https://agilemanifesto.org/)

### Lean software (snello)

Reduce waste, dentro il software e' tutto cio' che non ha valore per il cliente.
Difficile capire cosa non ha valore.

Un dipendente e' per la vita. Investo nel dipendente, voglio il dipendente felice.

### Kanban

Stories - cio' che vogliamo fare
Todo - cosa vogliamo fare nel prossimo periodo, cioe' su cui ci concentriamo
In progress - su cosa sto lavorando
Testing - cose che qualcuno sta valutando
Done - finito, cliente lo ha verificato e accettato

Definition of done e' molto importante.
Minimizzare la colonna "In progress", perche'aiuta minimizza il context switch

### Scrum

Scrum e' la mischia del rugby: "si lavora tutti assieme spingendo per avanzare"

Congelare i requisiti durante brevi iterazioni.
"Cliente puoi cambiare i requisiti quanto vuoi, ma non durante un'iterazione", un'iterazione dura 2 settimane / 1 mese.

### Crystal

Comunicazione per osmosi.
Pair programming: due persone acquisiscono conoscenza su quella parte.

## TDD

[TDD](TDD.md)

## Variabili in gioco

Portata - quante funzionalita' si vogliono implementare
Tempo - quanto tempo si puo' dedicare al progetto?
Qualita' - qualita' del progetto che si deve ottenere
Costo - risorse finanziarie che si possono impegnare

I cliente fa parte del team di sviluppo.
Con il TDD vede che il numero di test e di funzionalita' cresce.

Espandere perche' e' ARRIVATO L'ESITO di ARCHITETTURA

eXtreme Programming:
* feedback rapido
* presumere la semplicitia' - non complicarti la vita
* accettare il cambiamento
* modifica incrementale
* lavoro di qualita' - voglio i programmatori felici perche' se andassero via si perderebbe la loro conoscenza della codebase e tutta la formazione fatta nel tempo

sweng classica:
* separazione interessi
* astrazione e modularita'
* anticipazione del cambiamento (design for change)
* generalita'
* incrementalita'

# Lezione 4 - 07/10

## Figure in gioco in eXtreme Programming

Manager / Cliente
Ha responsabilita' di decidere
* portata del progetto
* priorita' tra funzionalita'
* date dei rilasci
Ha ditto di
* sapere cosa puo' essere fatto, tempistiche e costi
* vedere progressi del sistema, con test da lui definiti
* cambiare idea, sostituire funzionalita', cambiare priorita' ma nell'ottica SCRUM, quindi non nelle iterazioni

Sviluppatore
Ha responsabilita' di decidere
* stime dei tempi delle funzionalita'
* conseguenze di scelte tecnologiche
* pianificazione dettagliata nelle settimane di sviluppo
Ha diritto di
* sapere cosa e' necessario tramite chiare storie con priorita', "voglio che quando faccio questo il software deve fare questo"
* cambiare stime dei tempi in base all'esperienza fatta nelle iterazioni precedenti
* identificare e indicare funzionalita' pericolose
* produrre software di qualita', in pace, con un buono stipendio, etc

## Tecniche

Mettere insieme delle pratiche che si rafforzino in varie situazioni.
Le iterazioni durano due settimane.

### Planning game

Carte sufficientemente piccole in modo che non sia possibile scriverci sopra un papiro, basate sulle storie scritte dall'utente (prima del planning game).
Servono a decidere le funzionalita' del prossimo rilascio, combinando priorita' commerciali e valutazioni tecniche.

Il cliente partecipa al planning game.

Carta:
* descrizione
* caso di test che funge da test di accettazione
* valore di business

Sviluppatori:
* ognuno da la propria stima, si ricava una stima condivisa che il team fa sua

Manager:
* prende le carte e puo' decidere quali schede verranno implementate nella prossima iterazione

#### Stime

"Quante cose riusciamo a mettere dentro l'iterazione?"

La stima non è personale, ma del team. Questo perché chiunque può trovarsi a farla.
Come faccio a mettere assieme stime molto differenti quindi? Perché è difficile per chi ha stimato più alto scendere ad esempio.

Se le stime sono molto diverse magari il problema è come è scritta la storia.
Non si prende la media, ne il tempo piu' basso, ne il tempo piu' alto.

Non si possono fare stime per cui ognuno dice la sua, perche' altrimenti il primo che parla [setta un'ancora](https://en.wikipedia.org/wiki/Anchoring_effect) sulle stime degli altri.

Non si parla di ore / uomo se non esattamente alla fine per evitare 
* stime che si discostano dalla realta' (7 ore)
* per incasellare le stime possibili in range (ad esempio secondo planning poker usando i numeri di [Fibonacci arrotondati (vedi Equipment sul perche' arrotondati)](https://en.wikipedia.org/wiki/Planning_poker))

##### Planning poker

Procedura

1) si presentano le carte stando attenti ai numeri, i numeri rischiano di settare ancore
2) si fanno domande, si chiedono chiarimenti, si discute per chiarire assunzioni e rischi
3) ogni componente prende una carta dal proprio mazzo che rappresenta la stima
4) si girano tutte le carte contemporaneamente
5) tutti zitti, chi ha fatto la stima piu' bassa e chi ha fatto la stima piu' alta parlano, 1 minuto per spiegare perche'
6) si rivota, ora si ha un range di ancora

##### Team estimation game

Procedura

1) carte sul tavolo
2) la prima la si mette in centro
3) il primo developer prende una carta, la legge ad alta voce, e la mette a sx (piu' semplice) o a dx (piu' difficile) o sotto quella in centro
4) il prossimo puo'
    1) prendere una nuova carta e posizionarla secondo le regole
    2) spostare una carta precedentemente posizionata motivando
5) si parla solo se qualcuno non e' d'accordo con una disposizione precedente

Una volta che si e' tutti d'accordo sulle posizioni

1) usando un solo mazzo di carte, si prende la prima carta e la si mette sulla prima colonna (di solito si mette un 2 per lasciare spazio ad eventuali risistemazioni)
2) il primo developer prende la prossima carta e la mette sulla prima colonna che pensa abbia quel valore 
3) il prossimo puo'
    1) prendere una nuova carta e posizionarla secondo le regole
    2) spostare una carta precedentemente posizionata motivando

##### Unita' di misura

Se serve. Quantifico all'ultimo, per tenere gli errori al minimo.

Stimo in tempo la carta piu' semplice. Tramite proporzioni stimo tutte le altre.

#### Velocity

Capacita' osservata di completare lavori.
Dopo la prima iterazione il team dira' che puo' sviluppare tanti punti quanti ne ha fatti all'iterazione precedente.
Permette di compensare stime sbagliate.

<aside>Pericolosa la tentazione di aggiungere nuovi membri</aside>

Se i membri del team diminuscono allora posso adattare al ribasso.
Se i membri del team aumentano la velocity rimane la stessa della volta precedente.
Si tratta di fare assunzioni che siano safe.

Non deve essere valutato come metro di valutazione.
Non deve essere imposta.
Non si devono considerare storie non finite.

### Brevi cicli di rilascio

Ridurre rischi con rilasci significativi ogni due settimane.

### Usare una metafora

Trovare un vocabolario diverso su cui rimappare termini tecnici con cui cliente e sviluppatori hanno dimestichezza.

TODO ESPANDERE

### Semplicita' di progetto

L'arte di massimizzare il lavoro non fatto.

One and once only: cioe' tutto quello che serve e senza duplicazioni.
KISS.

### Testing

[TDD](TDD.md)

### Refactoring

"Modifiche al codice che non modificano le funzionalita'"

Serve a tenere bassa la crescita di complessita' di un progetto.

Non devo aver paura di modificare il progetto per semplificarlo.
Graduale ma 
* con continuita'
* senza pieta'

### Pair programming

Il manager dice "pago due persone perche' solo uno lavori".

Invece:
* mentre un programmatore sta scrivendo codice l'altro non e' che li guarda, ma ispeziona il codice, e potrebbe pensare "questo pezzo potrebbe essere fatto meglio?"
* aiuta inserimento di nuovo personale
* aiuta a ottenere proprieta' collettiva (conoscenza osmotica di Crystal)
* aiuta refactoring
* "se avessi avuto due persone a lavorare separatamente avrei ottenuto due feature, le ho messe assieme e ne ho ottenute tre": la produttivita' aumenta rispetto al singolo

Molti dei costi sono dopo la consegna, siamo sicuri che ragionare sulla produttivita' in base al giorno sia la scelta migliore?

### Proprieta' collettiva

Il codice non appartiene ad una sola persona.
"Non e' il mio codice, e' del team".
Ci si deve sentire responsabilizzati sull'intero codice, anche se non si conosce tutto alla stessa maniera.

### Integrazione continua

"Metto il mio lavoro in comune con quello fatto da altri".

Azione rapida, perche' il codice nel frattempo non e' cambiato molto.

### Settimana di 40 ore

### Cliente sul posto

Dove ci sono gli sviluppatori c'e' anche un rappresentante del cliente.
"Invece di fare una documentazione esaustiva iniziale ho il depositario della conoscenza del dominio"

In SCRUM c'e' il Product Owner, un intermediario rispetto al cliente, si prende la responsabilita' di aver dato la giusta interpretazione rispetto a cosa voleva il cliente

### Standard di codifica

Convenzioni distribuite e approvate all'interno del team.
Aiuta
* refactoring
* pair programming
* proprieta' collettiva