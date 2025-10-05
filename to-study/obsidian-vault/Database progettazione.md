---
cssclasses: 
tags:
  - databases
  - italian
---

```table-of-contents
```

## Esercizi

Vedi [Eserciziario](Algebra%20relazionale.md#Eserciziario)

## Introduzione

TODO RICORDATI DI SEGNARE DA QUALCHE PARTE CHE DA SCHEMA logico
A ER DEVI METTERE (0,N) SUGLI ATTRIBUTI DOVE SERVE

chiave primaria + chiave esterna -> entita' debole

"type" di solito compare quando si riassume una gerarchia in una superclasse


Come costruire un database, come progettarlo.


![Attività di progettazione](attachments/Pasted%20image%2020250420190513.png)

## Modello ER

Entità sono rettangoli.\
Associazioni sono rombi, più importante il significato rispetto al suo nome, cioè quali entità associa.\
Per ogni relazione ci sono due cardinalità.

### Esempio

Si modelli un diagramma ER che descrive una realtà ospedaliera in cui i medici curano i pazienti 

![Schema ER di una realtà ospedaliera](attachments/Pasted%20image%2020250416210616.png)

* quali sono le entità che voglio rappresentare? Le chiamo al singolare, sto descrivendo un singolo oggetto di quel tipo. Uso il rettangolo per comunicare che sto mostrando una entità
* metto solamente quegli attributi che sono presenti nella realtà che sto modellando, se parlo di pazienti non avrò `id`, ma avrò `tessera_sanitaria`
* porre attenzione agli attributi composti come ad esempio `residenza`: non voglio concatenare tutto in una stringa
* esprimo la cardinalità con `(x, y)` dove `x` e' la cardinalità minima, `y` la cardinalità massima
	* la cardinalità minima non può essere `N`
	* la cardinalità minima e' sempre meno della massima
	* la cardinalità va espressa per entrambe le direzioni della relazione
	* la cardinalità della relazione la si capisce guardando la cardinalità massima sui due lati della relazione (esempio se avessi `(0,N)` e `(1,N)` sarebbe una cardinalità molti a molti)

### Altri esempi

Seguono alcuni esempi con richieste differenti

| Esempio                                                                                                   | Medico - Paziente | Paziente - Medico | Tipo |
| --------------------------------------------------------------------------------------------------------- | ----------------- | ----------------- | ---- |
| Un medico cura non più di 1000 pazienti, e un paziente non può avere più di 10 medici curanti             | (0, 1000)         | (1, 10)           | N N  |
| Un medico cura almeno 10 e non più di 1000 pazienti, e un paziente non può avere più di un medico curante | (10, 1000)        | (1, 1)            | 1 N  |

In una N a N una associazione viene tradotta con una tabella con un identificativo per parte.\
In una 1 a N la chiave esterna va nell'entità che ha 1 come cardinalità massima.

"Ogni medico ha un supervisore che e' un altro medico", produrra' due modi di leggere l'associazione:

* ha\_supervisore (1, 1)
* e'\_supervisore (0, N)

Ed e' una 1 a N.

"I medici lavorano in reparti all'interno di ospedali, ogni reparto ha un primario scelto tra i medici che vi lavorano"

Mettere `primario` come attributo di `Reparto` e' un errore, perché lo identificherei con la sua `matricola`, e quindi sarebbe un `Medico`, quindi modello come una associazione.

### Entità debole

Reparto da solo non e' in grado di descriversi univocamente.

La cardinalità minima e' sempre (1, 1).

![Esempio di entità debole](attachments/Pasted%20image%2020250422145718.png)

TODO prendi da appunti di lezione gli altri schemi, ad esempio dove msotra attributi sulle associazioni.

TODO mettere rimando al vincolo extra schema: non si puo' gestire con un elemento dello schema, verrebbe gestito da trigger.

Occhio che una entità debole non passa mai dalla associazione.

## Problema della storicizzazione dei dati

Si tratta di scegliere se far si che la base di dati mostri la situazione attuale, o tenga uno storico di come si e' evoluta la situazione nel tempo.

TODO vedi l'esempio dagli appunti del prof su come cambia lo schema ER

## Gerarchie di generalizzazione

Vincoli che riguardano delle specifiche entità.

"Negli ospedali lavorano diverse figure professionali, tra le quali distinguiamo: medici, infermieri, dirigenti, personale amministrativo"

Obiettivo e' avere una entità che generalizzi le diverse figure.

La gerarchia ha due caratteristiche, che vanno sempre specificate (una per coppia):

* totalità e parzialità "a quali sotto classi può appartenere?"
	* totale: un elemento appartiene ad almeno una delle sottoclassi (l'unione delle sottoclassi ricostruisce la superclasse)
	* parziale: esistono individui non appartenente ad alcuna delle sottoclassi
* esclusività e sovrapponibilità
	* esclusività: un individuo e' collocabile solo in una delle sottoclassi, intersezione tra le sottocategorie e' $\emptyset$
	* sovrapponibile: un individuo può appartenere a più sottoclassi

Le sottoclassi possono non avere identificatore, nel caso questo sia presente nella superclasse.

Quando ci sono attributi che sono in comune tra le relazioni e' bene chiedersi se si e' in presenza di una possibile gerarchia di generalizzazione.

### Esempi

"Si sappia che un Dipendente può essere Medico, Dirigente, Infermiere, Amministrativo e Custode"

TODO aggiungi questo esempio

TODO prendi ER dagli appunti del prof

TODO prova a fare lo schema sui dati immobiliari proposto dal prof e dopo guarda la soluzione

## Esercizio su caso d'uso Events

La base di dati dovrà memorizzare:

- dati relativi a clienti distinti in persone fisiche e giuridiche 
- dati relativi agli eventi organizzati, tra i quali si distinguono banchetti e convegni 
- dati relativi ai partecipanti agli eventi con eventuali intolleranze (si tenga presente che non è possibile inserire dati anagrafici dei partecipanti se non il nome e il cognome). Si memorizzino gli accompagnatori (il partecipante A può essere accompagnato da B e C) sapendo che un partecipante può avere più accompagnatori, ma può accompagnare solo un partecipante. 
- dati relativi ai ristoranti che gestiscono i banchetti. Tra i ristoranti si distinguono quelli caratteristici 
- dati relativi ai menu serviti ai banchetti. Un menu è un insieme di portate classificate come antipasto, primo, secondo, dessert. Tra le portate si distinguono le specialità che possono essere offerte solo in ristoranti caratteristici. Il costo di un menu è la somma dei costi delle portate incluse nel menu

TODO rifallo da solo e controlla sia rispetto alla versione dell'anno scorso che rispetto a quella di questo anno

Come abbiamo proceduto a lezione:

* per ogni entità ci chiediamo quali sono gli identificatori, eventualmente pensando se siamo in presenza di una entità debole
* identificato cliente, come gerarchia di persona fisica e persona giuridica (T, E)
* identificato evento, come gerarchi di banchetto e convegno (P, S)
* pensiamo a come collegare le entità con le associazioni
* ogni volta che ottengo "gruppi" di entità mi chiedo anche li come collegarli con le associazioni

## Progettazione logica

Da modello concettuale a modello logico.

TODO integra con il diagramma che avevi fatto e mettilo qua
TODO integra con gli esempi nel materiale del prof su Ariel

Partiamo da questo 

![Schema ER di una realtà ospedaliera](attachments/Pasted%20image%2020250416210616.png)

Chiavi primarie sono sottolineate con riga continua. TODO metterlo in latex.\
Attributi opzionali sono denotati da un $*$.

* Parto da una entità - $Medico$
* Scrivo i suoi attributi - $Medico(\underline{matricola},\ ruolo,\ cognome,\ nome,\ specialita')$

* Prossima entità - $Ambulatorio(\underline{numero}, telefono*, ubicazione)$

* Prossima entità (focus solo sugli attributi interessanti) - $Paziente(\underline{tessera\_sanitaria}, residenza, diagnosi)$
  Scegliendo $residenza$ come unica stringa allora renderò alcune query molto difficili da fare, dovrò fare il parsing, quindi dipende dal dominio; se invece voglio poter fare query allora li metto come attributi separati dell'entità<label class="sidenote-toggle sidenote-number"></label>
  <span class="sidenote">Gestione attributi composti</span>.
  Per un attributo multi-valore come $diagnosi$ la faccio diventare una entità. Che quindi diventa una associazione 1 a N, 1 perché l'attributo e' diventato entità debole.

  Quindi ottengo una nuova relazione - $Diagnosi(\underline{diagnosi,\ tessera\_sanitaria})$<label class="sidenote-toggle sidenote-number"></label>
  <span class="sidenote">1 a N: metto l'identificatore nel lato 1 che ho nel lato N</span>
  Con la seguente notazione specifico a quale chiave fa riferimento la chiave esterna: $Diagnosi.tessera\_sanitaria \rightarrow Paziente.tessera\_sanitaria$

* vedo se ci sono associazioni rimanenti
* parto da $Curano$, che diventa una nuova tabella perché ho bisogno di mantenere quale $Medico$ e' associato a quale $Paziente$: $Curano(\underline{medico,\ paziente})$, e quindi aggiungo le specifiche per la chiavi esterne\
  $Curano.medico \rightarrow Medico.matricola$\
  $Curano.paziente \rightarrow Paziente.tessera\_sanitaria$

* Prossima associazione $Visita$: $Visita(\underline{medico,\ paziente,\ ambulatorio,\ data\_visita})$, e quindi aggiungo le specifiche per la chiavi esterne\
  $Visita.medico \rightarrow Medico.matricola$\
  $Visita.paziente \rightarrow Paziente.tessera\_sanitaria$\
  $Visita.ambulatorio \rightarrow Ambulatorio.numero$

Abbiamo sbagliato il modello concettuale, manca $data\_visita$ dentro $Visita$, perché altrimenti non avrei potuto avere una visita tra lo stesso medico, lo stesso paziente, nello stesso ambulatorio, il che e' assurdo. L'attributo $data\_visita$ e' parte della chiave quando voglio $Visita$ come relazione storica.

$Visita.ambulatorio$ non e' rappresentato come 0, come dovrebbe essere secondo la cardinalità, ma siccome fa parte della chiave deve esserci, quindi quando non c'e' semplicemente non appare in $Visita$. E' con i `LEFT JOIN` che vado a pescare questi record quando mi serve.\
In realtà sono sbagliate le `(1,N)` perché vorrebbe dire che ogni volta che aggiungo un $Medico$ devo subito assegnargli un $Paziente$ il che può non essere vero.

Nel caso di entità deboli faccio prima l'entità a cui sono associate.

Per convenienza e' possibile mettere un $id$ dove oltre agli identificatori naturali uso un identificatore "utile", perché cosi ad esempio le `JOIN` non sono un delirio di `ON` a causa dei molteplici attributi coinvolti nella chiave.\
E' una scelta fatta in fase di ristrutturazione.

Per una associazione con N entità devo prendere tutte le chiavi di ogni entità partecipante, per poi metterla nella nuova tabella.

### Gestione gerarchie

![Esempio di gerarchia di partenza](attachments/Pasted%20image%2020250506215301.png)

#### Accorpamento su entità padre (verso l'alto)

Si può sempre fare, con ogni tipo di gerarchia.\
La gerarchia collassa in un'unica entità, che e' quella padre e:

* si aggiunge un tipo enumerativo che identifica le figlie, nella gerarchia totale tipo e' obbligatorio, nella gerarchia parziale tipo e' opzionale; occhio che nelle sovrapposte il tipo deve considerare tutte le possibili combinazioni (può diventare poco maneggevole)
* si aggiungono eventuali attributi delle entità figlie, che arrivano come opzionali

![Accorpamento verso l'alto](attachments/Pasted%20image%2020250506215342.png)

#### Eliminazione dell'entità padre (verso il basso)

Non e' praticabile con una gerarchia parziale.\
La gerarchia viene spostata verso il basso, tutti gli attributi del padre vengono dati ai figli.\
Può essere scomoda se, ad esempio, non sappiamo se una persona e' fisica o giuridica e quindi dobbiamo fare una `UNION` per cercarla dappertutto.

![Scomposizione verso il basso](attachments/Pasted%20image%2020250506215404.png)

#### Mantenimento di tutte le entità

La gerarchia viene scomposta in associazioni binarie ($e'\_tipo$).\
I figli sono sempre entità deboli rispetto al padre.

![Mantenimento di tutte le entità](attachments/Pasted%20image%2020250506215512.png)

## Reverse engineering

TODO integrare con esempi e riflessioni dagli appunti e dall'html 
TODO chiedere al prof se i miei due schemi ER e relazionale per ottenere gli schemi sono corretti?


Costruire il concettuale (l'ER) che l'ha generata a partire dal relazionale.

Essenziale capire quali sono le chiavi esterne, che comunque vengono date (siccome abbiamo il relazionale).

* cerco le tabelle che non hanno chiavi esterne
* cerco le tabelle che hanno chiavi esterne
* le entità deboli hanno come chiave una chiave esterna (in alcune situazioni e' possibile che l'entità debole sia in realtà un attributo multi-valore)
* le molti a molti hanno le chiavi esterne che puntano a diverse tabelle (a volte ci può essere qualche attributo in più)
* eventuali associazioni ricorsive si riconoscono perché le chiavi esterne fanno riferimento alla tabella stessa

Alla fine del reverse engineering, per vedere se si e' lavorato bene si può cercare di tornare allo schema logico, se si riesce allora tutto ok.

## Esercizi

### Ospedale

![ER ospedale](attachments/Pasted%20image%2020250501182308.png)

Si ottiene:

$$
\begin{align}
&\ Medico(\underline{matricola},\ cognome,\ nome,\ specialita',\ ruolo,\ email) \newline
&\ Paziente(\underline{tessera\_sanitaria},\ cognome,\ nome,\ data\_nascita,\ cartella\_clinica, residenza) \newline
&\ Diagnosi(\underline{diagnosi,\ paziente,\ data}) \newline
&\ Ambulatorio(\underline{numero},\ ubicazione,\ telefono*) \newline
\newline
&\ Cura(\underline{medico,\ paziente}) \newline
&\ Visita(\underline{medico,\ paziente,\ ambulatorio,\ data\_visita}) \newline
\end{align}
$$

Chiavi esterne:

* Cura.medico -> Medico.matricola
* Cura.paziente -> Paziente.tessera_sanitaria
* Paziente.tessera_sanitaria -> Diagnosi.paziente
* Visita.medico -> Medico.matricola
* Vista.paziente -> Paziente.tessera_sanitaria
* Vista.ambulatorio -> Ambulatorio.numero

### Medico paziente

![ER medico paziente](attachments/Pasted%20image%2020250501195013.png)

Si ottiene lo stesso schema di prima, ciò che cambia e' che siccome ora e' una relazione da 1 a tot allora vuol dire che per un $Paziente$ ho solo un $Medico$, quindi posso mettere la chiave del $Medico$ nel $Paziente$.

Nel caso in cui non fosse stata `(1, 1)` ma fosse stata `(0, 1)` allora avrei avuto la stessa cosa, solo che la chiave esterna di $Medico$ dentro $Paziente$ sarebbe stata con un asterisco, per catturare il fatto che la cardinalità minima e' ora `0`.

### Medico ricorsiva

![ER medico ricorsiva](attachments/Pasted%20image%2020250501200939.png)

Non cambia niente, si ottiene:

$$
\begin{align}
&\ Medico(\underline{matricola},\ cognome,\ ...,\ supervisore) \newline
\end{align}
$$

Notare come non e' necessario dire altro perché se un $Medico$ ha un supervisore (come ogni $Medico$ deve avere grazie alla cardinalità minima 1) allora c'e' il campo $supervisore$, ma e' anche possibile che un $Medico$ non sia supervisore di nessuno, quindi non comparirà mai nel campo $supervisore$.

Chiavi esterne:

* Medico.supervisore -> Medico.matricola

### Medico reparto ospedale

![ER medico reparto ospedale](attachments/Pasted%20image%2020250501202544.png)

Si ottiene:

$$
\begin{align}
&\ Medico(\underline{matricola},\ ...) \newline
&\ Reparto(\underline{nome,\ nome\_ospedale,\ citta'\_ospedale},\ primario) \newline
&\ Ospedale(\underline{nome,\ citta'},\ piva,\ sede\_legale) \newline
&\ \newline
&\ Lavora(\underline{medico,\ nome\_reparto,\ nome\_ospedale,\ citta'\_ospedale }) \newline
\end{align}
$$

Chiavi esterne:

* Lavora.medico -> Medico.matricola
* Lavora.nome_reparto, Lavora.nome_ospedale, Lavora.citta_ospedale -> Reparto.nome, Reparto.nome_ospedale, Reparto.citta_ospedale
* Reparto.nome_ospedale, Reparto.citta_ospedale -> Ospedale.nome, Ospedale.citta
* Reparto.primario -> Medico.matricola

### Esempio con users e users_data

![Esempio di relazione 1 a 1](attachments/Pasted%20image%2020250506215626.png)

$$
\begin{align}
&\ Users(\underline{username},\ password,\ enabled) \newline
&\ UserData(\underline{user}, nome,\ cognome,\ ruolo) \newline
\end{align}
$$

Chiavi esterne:

* UserData.user -> Users.username

### Esempio 1 a 1 senza entità debole

![Esempio 1 a 1](attachments/Pasted%20image%2020250506220624.png)

$$
\begin{align}
&\ A(\underline{A_1},\ A_2,\ A_3) \newline
&\ B(\underline{B_1},\ A_1,\ B2) \newline
\end{align}
$$

Chiavi esterne:

* B.A1 -> A.A1

### lol

![ER piuttosto ingombrante](attachments/Pasted%20image%2020250506221924.png)

$$
\begin{align}
&\ Country(\underline{iso3},\ name) \newline
&\ Cinema(\underline{city,\ name},\ address,\ phone^*)) \newline
&\ Genre(\underline{name,\ movie}) \newline
&\ Material(\underline{id},\ description,\ language^*,\ content^*,\ runtime^*,\ resolution^*,\ type^*,\ url^*,\ movie)) \newline
&\ Movie(\underline{id},\ budget^*,\ year^*,\ length^*,\ plot^*,\ official\_title^*,\ genre^*) \newline
&\ Person(\underline{id},\ birth\_date^*,\ death\_date^*,\ given\_name,\ bio^*) \newline
&\ Rating(\underline{check\_date,\ source,\ movie},\ score,\ votes,\ scale) \newline
&\ \newline
&\ Location(\underline{country,\ person},\ d\_role,\ city^*,\ region) \newline
&\ Produced(\underline{country,\ movie}) \newline
&\ Released(\underline{country,\ movie},\ released^*,\ title^*) \newline
&\ Show(\underline{cinema\_city,\ cinema\_name,\ movie},\ showtime) \newline
&\ Similar(\underline{movie\_a,\ movie\_b},\ cause,\ score) \newline
&\ Crew(\underline{movie,\ person},\ p\_role,\ character^*) \newline
\end{align}
$$

Siccome non sappiamo che tipo di gerarchia coinvolge $Material$ (sto facendo l'esercizio inverso rispetto a quello fatto in classe), faccio un accorpamento verso il padre, perché non ho sbatti di mettere altre relazioni per la divisione in entità

Chiavi esterne:

* Genre.movie -> Movie.id
* Rating.movie -> Movie.id
* Location.country -> Country.iso3
* Location.person -> Person.id
* Produced.country -> Country.iso3
* Produced.movie -> Movie.id
* Released.country -> Country.iso3
* Released.movie -> Movie.id
* Show.cinema_city -> Cinema.city
* Show.cinema_name -> Cinema.name
* Show.movie -> Movie.id
* Material.movie -> Movie.id
* Similar.movie_a -> Movie.id
* Similar.movie_b -> Movie.id
* Crew.person -> Person.id
* Crew.movie -> Movie.id