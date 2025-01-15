---
cssclasses:
  - cornell-note
  - software-engineering
  - italian
---

> [!warning] 
> Questo documento e' incompleto, alcuni concetti che gia' conosco sono stati saltati

Correzione 
https://www.youtube.com/watch?v=7vb089FkfiI&sttick=0
file:///C:/Users/monte/Downloads/Telegram%20Desktop/slides%20(2).pdf



FLASHCARDS

https://www.memory.com/courses/dashboard/ingegneria-del-software-it-it--ao8b_1695973981615185115_73/Italian/Italian

# Refresh di Programmazione II

## Nested classes

Sono di 4 tipi

### Static member classes

Se una istanza di questa classe puo' esistere indipendentemente da una istanza della classe contenitrice.
Se non si richiede accesso all'istanza della classe contenitrice allora sempre mettere `static`

### Nonstatic member classes

Ogni istanza di questa classe e' associata ad una istanza della classe contenitrice

### Anonymous classes

### Local classes


---

Cio' che segue e' ancora tutto da sistemare

https://prog2unimi-temi-svolti.netlify.app/

---

https://martinfowler.com/bliki/TellDontAsk.html

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


# Punti di attenzione dal lab

## Approccio ai parziali ed in generale all'implementazione di un programma

Planning: "cosa riesco a fare?", "era impossibile farlo in 4 ore" $\rightarrow$ non si e' capito cosa e' un modello incrementale
"Cosa riesco a fare in 4 ore che da piu' valore al cliente?"

## gitflow

Utilizzare assolutamente la funzionalita' dentro l'IDE.


## Code smells

ISTEMARE DOPO LAB

// null? code smell  
// nested classes  
// metodi per esplicitare cosa stiamo facendo  
// posso avere un nodo che non contiene un valore? meglio di no  
// Objects.requireNonNull  
  
// privilegiare assert, il compilatore le leva direttamente in prod  
// flag EA del compiler


non si fa in nessun caso commit diretto su develop.

Si apre sempre una nuova feature.

Nel caso 2 e 3 si parte con un test (anche nel due: infatti se hai trovato un bug...scrivi per prima cosa un test che lo verifica, commit del rosso e solo dopo applichi il fix)

Nel caso 1... se il refactoring si tradurrà in un unico commit... gitflow di default applicherà fast forward (sempre che nessuno nel frattempo non abbia integrato qualcosa di nuovo...). Quindi magari sembrerà che sia un commit diretto su develop, ma appunto sarà un caso speciale (singolo commit e no overlapping con altri developer)

# Esame

## Commenti su esami ed errori comuni

https://www.youtube.com/watch?v=7vb089FkfiI


Stazione dei treni
https://www.youtube.com/watch?v=Oq1EnhTgTyQ
https://www.youtube.com/watch?v=AswqBpGB6BE

Sapore di sale
https://www.youtube.com/watch?v=39hEK_YDOvw

Principi SOLID 
Single Responsibility 
Open-Closed 
Liskov Substitution Interface 
Segregation 
Dependency Inversion 
Testing Unità 
* copertura di comandi e decisioni/condizioni 
* isolamento e mocking 
Processo TDD 
* gitflow e feature 
* refactoring 
Patterns 
* MVP 
* Template e/o Strategy 
* Singleton 
* Builder 
* Chain of Responsibility 
* Adapter 
appropriate data structures 
nullability 
encapsulation 
no escaping reference 
readability (clean code)


### Domande orale

Puo' chiedere di scrivere un diagramma delle classi di qualunque cosa vista a lezione (tipicamente design pattern) e puo' chiedere di leggere qualsiasi diagramma.

Nullability e mocking vengono chiesti molto spesso, sia allo scritto che all'orale.

* principi SOLID, con approfondimento su qualcuno di questi (+)
* stime di tempi nell'ambito di un progetto, problemi e come risolverli (+)
* interface segregation (+)
* refactoring
* modelli di sviluppo, a spirale; modello prototipale vs modello incrementale (+)
* modelli di ciclo di vita del software
* parlami positivamente del modello a cascata, come nasce il modello a cascata?
* mocking, differenza tra stub e mock (+)
* meta-pattern, pattern decorator, pattern composite, pattern strategy, chain of responsability (+++++)
* cosa e' un test ideale? (+)
* perche' TDD e' una buona tecnica? Tecnica di requisiti o tecnica di design o di progettazione? Cos'e'? (+)
* mi parli di criterio di selezione. Che cos’è? Perché dobbiamo scegliere nei test un sottoinsieme del loro domino (cioè non seleziono tutti i test, ma solo alcuni)
* differenza tra tecnica di design e tecnica di progettazione
* due pratiche XP per convincere il manager (+)
* da dove derivano, chi è che scrive le specifiche? Dopo averle raccolte cosa devi fare prima di implementarle?
* differenza tra merge e pull request

UNSORTED
- quando un criterio di selezione è valido e quando è affidabile
- relazioni di conflitto tra transizioni 
- problemi nella stima dei tempi e come risolverli
- parlami di analisi statica e analisi data flow
- legame tra limitatezza e conservatività
- legame tra limitatezza e conservatività (continua...)
- terminologia degli errori
- albero di copertura vs abero di raggiungibilità
- criteri di selezione: proprietà
- tell dont ask
- differenza semantica debole e forte
- differenza tra verifica e convalida
- Che cos’è la nullability? Perché è importante? Qual è la caratteristica del valore null che “da fastidio”?
- disegnato una rete di petri, fare l’albero di copertura
  - scrivere formalmente la regola di abilitazione
- diagrammi di sequenza
- Terminologia di base di verifica e convalida. Sbaglio, errore, difetto, anomalia…, esempio in cui si presenta anomalia ma non (difetto o errore (?))
- Prova a scrivermi la relazione di conflitto, relazione di concorrenza (fai esempi e scrivi in maniera formale) evidenzia strutturale/effettivo
- Inserisco un valore che sfora, dove limito questo inserimento (model o presenter?Andava messo nel model)
- Esempio di rete limitata.


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

[CMMI](https://en.wikipedia.org/wiki/Capability_Maturity_Model_Integration) - standardizzazione del livello di qualita' di una ditta
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
* [Software engineering#Funzionare](Software%20engineering#Funzionare.md)
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

<aside>mappa XP</aside>

http://www.extremeprogramming.org/map/project.html

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

Far parlare tutti prima di iniziare la procedura, anche solo per dire il proprio nome, sblocca quelle persone in modo tale che poi avranno meno problemi a parlare.

#### Stime

<aside>Stime</aside>

"Non e' un consuntivo, e' una stima"
"Quante cose riusciamo a mettere dentro l'iterazione?"

L'incertezza c'e' sulla prima stima, poi la metodologia "ingrana".

La stima non è personale, ma del team. Questo perché chiunque può trovarsi a farla.
Come faccio a mettere assieme stime molto differenti quindi? Perché è difficile per chi ha stimato più alto scendere ad esempio.

Se le stime sono molto diverse magari il problema è come è scritta la storia.
Non si prende la media, ne il tempo piu' basso, ne il tempo piu' alto.

Non si possono fare stime per cui ognuno dice la sua, perche' altrimenti il primo che parla [setta un'ancora](https://en.wikipedia.org/wiki/Anchoring_effect) sulle stime degli altri.

Non si parla di ore / uomo se non esattamente alla fine per evitare 
* stime che si discostano dalla realta' (7 ore)
* per incasellare le stime possibili in range (ad esempio secondo planning poker usando i numeri di [Fibonacci arrotondati (vedi Equipment sul perche' arrotondati)](https://en.wikipedia.org/wiki/Planning_poker))

<aside>The Mythical Man Month</aside>

[The Mythical Man-Month](The%20Mythical%20Man-Month.md)

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

> Given enough eyeballs, all bugs are shallow
-- Eric S. Raymond

"Durante pair programming siamo una singola entita' e come tale abbiamo problemi condivisi, idee, etc"

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

# Lezione 5 - 09/10

## Fasi

### Requirements

Dal momento che il cliente fa parte del team di sviluppo (lavora assieme) puo' spiegare le storie che ha scritto direttamente ai programmatori.

Consegne incrementali e pianificazioni continue.

### Design

ESPANDERE
Una metafora come visione unificante del progetto

Refactoring.
Presumere la semplicita', contrapposta a "design for change".
ESPANDERE QUESTA ULTIMA COSA

### Code

Programmare a coppie modifica completamente l'approccio al codice.
Proprieta' collettiva "non ho codice su cui non posso lavorare".
Integrazioni continue.

### Test

Testing di unita' continuo, da scrivere prima del codice.
Test funzionale scritto dagli utenti.

## Documentazione

Use stories
ESPANDERE CON LA DIFFERENZA CON USE CASES DI UML

Codice e test sono la documentazione.
"Test e' la traduzione in linguaggio formale delle use stories e di cio' che ci siamo detti verbalmente".

Ha un valore documentale molto piu' forte della documentazione scritta.

Le persone sono la documentazione.

## Quando non si puo' usare XP?

Ambienti che
* non permettono di testare e avere un feedback immediato (es: codice che gira in un acceleratore di particelle del CERN)
* non permettono di avere uno sviluppo incrementale (es: centrale nucleare)
* frappongono barriere di tipo manageriale o burocratico (es: team troppo grossi non suddivisibili)
* frappongono barriere di tipo fisico (es: elementi del team divisi)

## Critiche

ESPANDERE
Sottovalutazione dell'up-front
Sopravvalutazione delle user stories
Mancata evidenziazione dipendenze tra user stories
TDD puo' portare a visione troppo ristretta
In team con competenze diverse puo' esserci un problema

ESPANDERE CON ![](Pasted%20image%2020241012211104.png)

## Open source process

ESPANDERE

### The Cathedral and the Bazaar

<aside>the cathedral and the bazaar</aside>

http://www.catb.org/~esr/writings/cathedral-bazaar/ ([Audiobook](https://www.youtube.com/watch?v=qpyewW09HXo))

"Ogni buon lavoro software inizia dalla frenesia personale di uno sviluppatore"

Sono entrambi due esempi positivi

Cattedrale: ideata da uno o da pochi eletti, ben strutturata
Bazaar: funzionale e pratico, ma e' un po' un casino, chiunque puo' dire la sua e contribuire

La bonta' del codice non e' una caratteristica essenziale, se ci sono problemi, se non e' perfetto crea comunicazione e una voglia di guardarci dentro per sistemare.

19 Lessons:
1. Every good work of software starts by scratching a developer's personal itch.
2. Good programmers know what to write. Great ones know what to rewrite (and reuse).
3. Plan to throw one [version] away; you will, anyhow (copied from Frederick Brooks's _[The Mythical Man-Month](https://en.wikipedia.org/wiki/The_Mythical_Man-Month "The Mythical Man-Month")_).
4. If you have the right attitude, interesting problems will find you.
5. When you lose interest in a program, your last duty to it is to hand it off to a competent successor.
6. Treating your users as co-developers is your least-hassle route to rapid code improvement and effective debugging.
7. [Release early. Release often.](https://en.wikipedia.org/wiki/Release_early,_release_often "Release early, release often") And listen to your customers.
8. Given a large enough beta-tester and co-developer base, almost every problem will be characterized quickly and the fix obvious to someone.
9. Smart data structures and dumb code works a lot better than the other way around.
10. If you treat your beta-testers as if they're your most valuable resource, they will respond by becoming your most valuable resource.
11. The next best thing to having good ideas is recognizing good ideas from your users. Sometimes the latter is better.
12. Often, the most striking and innovative solutions come from realizing that your concept of the problem was wrong.
13. Perfection (in design) is achieved not when there is nothing more to add, but rather when there is nothing more to take away. (Attributed to [Antoine de Saint-Exupéry](https://en.wikipedia.org/wiki/Antoine_de_Saint-Exup%C3%A9ry "Antoine de Saint-Exupéry"))
14. Any tool should be useful in the expected way, but a truly great tool lends itself to uses you never expected.
15. When writing gateway software of any kind, take pains to disturb the data stream as little as possible—and never throw away information unless the recipient forces you to!
16. When your language is nowhere near [Turing-complete](https://en.wikipedia.org/wiki/Turing_completeness "Turing completeness"), [syntactic sugar](https://en.wikipedia.org/wiki/Syntactic_sugar "Syntactic sugar") can be your friend.
17. A security system is only as secure as its secret. Beware of pseudo-secrets.
18. To solve an interesting problem, start by finding a problem that is interesting to you.
19. Provided the development coordinator has a communications medium at least as good as the Internet, and knows how to lead without coercion, many heads are inevitably better than one.

### [Care and Feeding of FOSS](https://web.archive.org/web/20081015010505/http://www.moonviewscientific.com/essays/software_lifecycle.htm)

TODO

### [The Emerging Economic Paradigm of Open Source](http://web.archive.org/web/20120724095330/http://perens.com/works/articles/Economic.html)

TODO

"Perche' una azienda dovrebbe pagare i propri dipendenti per sviluppare open source?"


### An empirical study of open-source and closed-source software products by J.W. Paulson et al.

https://ieeexplore.ieee.org/abstract/document/1274044

# Lezione 6 - Git - 14/10

Integra dal libro di Git

INSERISCI DA TABLET

# Lezione 7 - Git segue - 16/10

git merge
git reset
git amend

## git flow (AVH)

ESPANDERE GIT FLOW

<aside>git flow</aside>

main / master - cio' che viene consegnato al cliente
develop - dove si mettono le modifiche in comune con gli altri sviluppatori, da dove parto con lo sviluppo di una feature

`git flow feature start $featname`:
* `git checkout develop`
* `git branch $featname`
* `git checkout $featname`

Feature parte dell'ultima situazione stabile sul develop.

`git flow feature finish $featname`:
* `git checkout develop`
* `git merge --no-ff $featname` (--no-ff vuol dire forzare a fare un commit)
* `git branch -d $featname`
Il motivo per cui si usa `--no-ff` e' che nel branch di develop non ho i "rossi" che invece avvengono dentro la branch di feature.

`git flow release start ver`
Congelo le feature che dovranno esserci nella prossima release.
* `git checkout -b ver develop`

`git flow release finish ver`
* git checkout master
* git merge --no-ff release-ver
* git tag -a ver
* git checkout develop
* git merge --no-ff release-ver
* git branch -d release-ver
Non si puo' aprire una release se ce ne e' gia' una precedente aperta.

Riparazione veloce di difetti urgenti senza aspettare la prossima release
`git flow hotfix start ver`
* `git checkout -b ver master`

`git flow hotfix finish ver`
* `git checkout master`
* `git merge --no-ff ver`
* `git checkout develop`
* `git merge --no-ff ver`
* `git branch -d ver`

## Mancanze di Git

No livelli di autorizzazione (i commit possono essere firmati)
Soluzione: fork.

No livelli di review, c'e' `git request-pull <start> <url> [<end>]` per preparare cio'che poi mandero' perche' ne venga fatta review.

I grandi progetti non sono clonati lato Github, ma esistono una volta e poi sono linkati quando clonati.

Aggiungere Gerrit

## Build automation

Come mi proteggo da checkin di una versione non funzionante?
Automatizzo:
* compilazione
* testing

"Da cosa dipende il fatto che io possa eseguire il mio programma?"


# Lezione 8 - Progettazione - 16/10

<aside>visibilita' nei test</aside>

Avere una struttura di questo tipo

```
└── src
    ├── main
    │   └── java
    │       └── it
    │           └── unimi
    │               └── di
    │                   └── sweng
    │                       ├── DoublyLinkedList.java
    │                       └── Node.java
    └── test
        └── java
            └── it
                └── unimi
                    └── di
                        └── sweng
                            └── DoublyLinkedListTest.java
```

consente di sfruttare la visibilita' a livello di package dei componenti delle classi, per averli disponibili nel test.

INSERIRE delle note tra i processi per "Unified process"
https://youtu.be/jNqgozh_7x8?t=1999 (un po' prima di questo minuto)

Spostare queste due parti altrove e partire da 0 qua con la PROGETTAZIONE

Come si condivide l'informazione?

Metodi (TDD)
Design pattern
Principi - giudicare e capire se e' necessario modificare qualcosa

Un linguaggio per potersi definire ad oggetti deve avere:
* ereditarieta'
* polimorfismo
* collegamento dinamico

## Encapsulation and information hiding

[Encapsulation and information hiding](Encapsulation%20and%20information%20hiding.md)

# Lezione 9 - Pattern - 23/10/2024

## Collegamento dinamico / statico

Statico: il compilatore guarda il tipo dell'oggetto e chiama il metodo corretto
Dinamico: a runtime c'e' un collegamento all'implementazione concreta

ESPANDERE

## UML

![](uml.png)


`T` nel rettangolo tratteggiato rappresenta un type parameter.
static: sottolineato
abstract: corsivo

## Pattern

Situazioni ricorrenti di cui do una soluzione architetturale.

Meta pattern hanno due elementi di base:
* HookMethod, metodo astratto che determinato il comportamento specifico nelle sottoclassi: "dimmi tu come e' fatto nel tuo caso questo metodo"
* TemplateMethod: invariabile del pattern, coordina piu' hookmethod

Come si classificano i pattern in base alla relazione tra hook e template: unification, connection, recursive connection
![](metapattern.png)

Categorie di pattern in base all'output
* creazionali
* comportamentali
* strutturali

### Singleton

<aside>singleton</aside>

Voglio ottenere un oggetto e non una classe.
Voglio rendere la classe responsabile del fatto che non puo' esistere piu' di una istanza.

![](singleton-pattern.png)

Costruttore protected per dare l'opportunita' a chi sub-classa di creare una istanza.

In realta' questa e' l'implementazione migliore:

```java linenos:1
public enum MySingleton {
    INSTANCE;
    public void sampleOp() {...}
}

MySingleton.INSTANCE.sampleOp();
```

ESPANDI CON EFFECTIVE JAVA

### Iteratore

<aside>iterator</aside>

![](iterator-pattern.png)

Fornisce un modo di accedere agli elementi di un oggetto aggregatore in maniera sequenziale senza esporre la rappresentazione interna

ESPANDI JAva defaultr

# Lezione 10 - 28/10/2024

Nullability
 * contract based 
 * programmazione difensiva

ESPANERE

> Un codice non dovrebbe far uso di `null` nelle parti visibili, di interfaccia 

Fail fast: `Objects.requireNonNull` esplicita che non accetto qualcosa come `null`, gestendolo sollevando una `NullPointerException`

```java linenos:1
public Card(Rank rank, Suit suit) {
    this.rank = Object.requireNonNull(rank);
    this.suit = Object.requireNonNull(suit);
  }
}
```


Il seguente codice e' completamente sbagliato, perche' viene comunque creato un oggetto con le variabili d'istanza a `null`

```java linenos:1
public Card(Rank rank, Suit suit) {
  if (rank != null && suit != null) {
    this.rank = rank;
    this.suit = suit;
  }
}
```

Difensiva: gestione esplicita dei `null`, "sono capace di gestire `null`", programmazione difensiva

```java linenos:1
public Card(Rank rank, Suit suit) {
  if (rank == null || suit == null) {
    throw new IllegalArgumentException();
  }
  
  this.rank = rank;
  this.suit = suit;
}
```

Mi assicuro che in sviluppo non mi arrivi un `null`, ma non e' compito mio:

```java linenos:1
public Card(Rank rank, Suit suit) {
  assert rank != null && suit != null;
  this.rank = rank;
  this.suit = suit;
}
```

Intellij puo' generare `if` in questo caso, inoltre lascia aperta la porta a tool che potrebbero fare analisi statica grazie alla mia dichiarazione di intento:

```java linenos:1
private final @NotNull Rank rank;
private final @NotNull Suit suit;

public Card(@NotNull Rank rank, @NotNull Suit suit) {
  this.rank = rank;
  this.suit = suit;
}
```

`Optional`? Quasi, non e' molto differente da controllare se qualcosa e' `null`.

## Pattern

<aside>null object pattern</aside>

### Null Object Pattern

"Vogliamo creare un oggetto che corrisponda al concetti "nessun valore" o "valore neutro".

ESPANDERE https://youtu.be/i5yG8Q6u8ic?t=1831


# Lezione 11 - 01/11/2024

# Lezione 12 - 04/11/2024

# Lezione 13 - 06/11/2024

# Lezione 14 - 11/11/2024

`assert` vuol dire stabilire un contratto con il chiamante; per questo puo' sparire una volta in produzione.

# Lezione 15 - 13/11/2024

Stato concreto e astratto
* concreto dipende dalla sua implementazione, ad esempio se ho due interi e' $2^_{32} * 2^_{32}$
* astratto e' un sottoinsieme (meglio se significativo) degli stati concreti

Oggetti senza stato possono essere gli oggetti funzione.
Oggetti con un solo stato sono gli oggetti immutabili.

NB: una classe immutabile non ha un solo stato, sono le sue istanze che una volta create non possono piu' cambiarlo.

## State diagram

Un automa e' una n-upla <S, I, U; $\delta$, $\tau$, $s_0$>, dove
* S insieme finito e non vuoto degli stati
* I l'insieme finito dei possibili ingressi
* U l'insieme finito delle possibili uscite
* $\delta$ la funzione di transizione
* $\tau$ la funzione di uscita
* $S_0$ lo stato iniziale

La funzione di transizione definisce i passaggi da uno stato all'altro $\delta: S \times I \rightarrow S$, puo' essere una funzione parziale, cioe' non essere definita per tutte le possibili coppie.

Un automi a stati finiti non e' un buon metodo se abbiamo un alto numero di stati significativi.

ESPANDERE

Superstate

TODO fare musica maestro kata!!!!!

# Lezione 16 - 18/11/2024

# Lezione 17 - 20/11/2024
