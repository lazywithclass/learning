---
cssclasses: 
tags:
  - databases
  - normalization
---

Permette di stabilire la qualità di uno schema.

TODO mettere l'immagine esempio 

TODO inseire come fare una normalizzazione dalle slide

TODO inserire indice all'inizio del file


$$
\begin{align}
&\ R(person,\ first\_name,\ last\_name,\ given\_name,\ movie,\ title,\ year,\ character) \newline
\end{align}
$$

Cosa non va in questa tabella?

* ci sono attributi che descrivono diverse entità del contesto cinematografico (ad esempio riguardo persone e film)
	* questo porta alla duplicazione se ad esempio qualcuno recita in diverse pellicole


## Dipendenza funzionale

Una dipendenza funzionale $x \rightarrow y$ tra due sottoinsiemi di attributi $x$ e $y$ di una stessa relazione R stabilisce un vincolo sulle ennuple he possono formare uno stato di relazione r di R. $y$ dipende funzionalmente da $x$. "Dato un x avrò lo stesso y?"

$X \rightarrow Y$ non implica $Y \rightarrow X$

Il vincolo stabilisce che... TODO integra da slide

Vuol dire che se prendo due qualunque record $t_1$ e $t_2$ che appartengono alla relazione $R$, allora $t_1[X] = t_2[X] \rightarrow t1[Y] = t_2[Y]$.

Nell'esempio ci sono queste dipendenze:

* $movie \rightarrow title$, prendendo dalla definizione $t_1[movie] = t_2[movie] \rightarrow t_1[title] = t_2[movie]$ 
* $person \rightarrow first\_name,\ last\_name,\ given\_name$
* $first\_name,\ last\_name \rightarrow person$ (dipendenza non corretta)
* $person,\ movie \rightarrow character$
* $movie \rightarrow year$

Ci aiutano a evidenziare quali sono le entità.

Vogliamo ottenere uno schema che <label class="sidenote-toggle sidenote-number"></label><span class="sidenote">Obiettivo della normalizzazione</span>:

* garantisca join senza perdita - se da $R_1$ e $R_2$ faccio join devo ritornare a $R$ (ricostruzione di una relazione dalle sue parti decomposte), non voglio nemmeno ottenere righe che prima non c'erano
* garantisca la conservazione delle dipendenze - se nel decomporre $R$ gli attributi delle dipendenze funzionali finiscono almeno in una entità

### Regole di inferenza

Permettono di evidenziare dipendenze funzionali latenti.

Riflessiva $Y \subseteq X$ ($Y$ contiene $X$) allora X -> Y
X = movie, title
Y = title
movie, title -> title

Arricchimento
$movie \rightarrow year$ allora $movie,\ title \rightarrow year,\ title$

Transitiva
$X \rightarrow Y$ e $Y \rightarrow Z$ allora $X \rightarrow Z$

Decomposizione
$X \rightarrow YZ$ allora $X \rightarrow Y$ e $X \rightarrow Z$

Unione
$X \rightarrow Y$ e $X \rightarrow Z$ allora $X \rightarrow YZ$

Pseudo transitiva
$X \rightarrow Y$ e $WY \rightarrow Z$ allora $WX \rightarrow Z$

$F^+$ e' La chiusura di $F$ e' l'insieme TODO completare da slide 

## Forme normali

Strettamente legate al concetto di dipendenza funzionale.\
Normalizzare vuol dire... TODO completare da slide

Sono proprietà definite sulle relazione e basate sulle dipendenze funzionali. Danno la conferma che non ci siano anomalie.

Date le dipendenze funzionali, raggiungo le forme normali.

* BCNF (Boyce-Codd)
* 3NF (un buono schema e' qua)
* 2NF
* 1NF

BCNF e' il massimo ottenibile. Alcuni schemi non possono raggiungere la BCNF.

In generale nella decomposizioni e' bene tenere presenti questioni di opportunità: ad esempio in alcuni casi e' bene favorire un minor numero di join.


******************todo
TODO inserisci esempi di relazioni
***todo

Quando si controlla in che NF e' la relazione e' bene partire dall'alto e controllare andando verso il basso, piuttosto che dal basso e andando verso l'alto.

### Boyce-Codd NF

TODO aggiungi le pk agli esempi

Ogni volta che sussiste in $R$ una dipendenza funzionale non banale $X \rightarrow A$, $X$ e' una superchiave.

---

$person$ e $movie$ sono la chiave.

La relazione non e' in BCNF perché abbiamo dipendenze parziali dalla chiave, quindi decompongo:

* $R_1(movie,\ title)$
* $R_2(movie,\ year)$
* $R_3(person,\ first\_name,\ last\_name)$
* $R_4(person,\ given\_name)$
* $R_5(person,\ movie,\ character)$

Abbiamo pero' iper-normalizzato, ad esempio per $R_1$ e $R_2$, tramite la [Regole di inferenza](Normalizzazione%20di%20schemi.md#Regole%20di%20inferenza) unione posso ottenere

* $R_1(movie,\ title,\ year)$
* $R_2(person,\ first\_name,\ last\_name,\ given\_name)$
* $R_3(person,\ movie,\ character)$

---

Consideriamo ora questa $R(person,\ country,\ movie)$

* country e' la sede della produzione del film, coincide con il paese di residenza dell'attore durante le riprese

Dipendenze funzionali:

* $movie \rightarrow country$
* $person \rightarrow country$ 

Chiave, che va ottenuta guardando i dati e non le dipendenze funzionali, e' composta da $movie$ e $person$

Normalizzazione:

* $R_1(movie,\ country)$
* $R_2(person,\ country)$

Provando a fare 

```language-sql
SELECT * 
FROM R1 JOIN R2 ON R1.country = R2.country
```

perderei la caratteristica di avere join senza perdita. Quindi dobbiamo trovare una strada alternativa.\
E' sempre possibile verificare se arriviamo ad avere join senza perdita <label class="sidenote-toggle sidenote-number"></label>
<span class="sidenote">Verifica di join senza perdita</span>.

Data $R$ e $R_1$ e $R_2$ come decomposizione, e' senza perdita se:

* $R_1 \cup R_2 = R$
* $R_1 \cap R_2 = R'$ dove $R'$ e' chiave in $R_1$ o in $R_2$, cioè l'attributo di join deve essere chiave di uno dei due

Strada alternativa:

* $R_1(movie,\ country)$
* $R_2(person,\ country)$
* $R_3(person,\ movie)$

Unione degli attributi delle 3 mi porta agli attributi della relazione iniziale, l'intersezione invece tra ognuna ha sempre un attributo che e' chiave.

Anche questa decomposizione sarebbe sbagliata:

* $R_1(person,\ country)$ PK person
* $R_2(person,\ movie)$ PK person, movie

Unione delle 2 mi porta alla relazione iniziale, l'intersezione va bene; ma non e' BCNF perché ho perso $movie \rightarrow country$.

---

Consideriamo ora $R(movie,\ country,\ agency)$, che descrive un film con i relativi paesi in cui e' stato distribuiti e per ciascuno di essi l'agenzia di distribuzione. Si sappia che ogni agenzia e' attiva in un solo paese.

Chiave e' $movie,\ country$

Le dipendenze sono:

* $movie,\ country \rightarrow agency$
* $agency \rightarrow country$

Quindi BCNF non e' rispettata.

Normalizziamo:

* $R_1(agency,\ country)$
* $R_2(movie,\ country)$

Qualunque tipo di ricostruzione mi porta ad avere un problema, oltretutto il join avverrebbe su un attributo non chiave.

Non possiamo arrivare a BCNF, perché tutti gli attributi sono parte di una dipendenza funzionale <label class="sidenote-toggle sidenote-number"></label>
<span class="sidenote">Irraggiungibilità di BCNF</span>.

TODO finire da materiale del prof

### Terza forma normale

Rilassamento di BCNF. Uno schema e' in 3NF se per ogni dipendenza funzionale non banale $X \rightarrow A$ di $R$, e' soddisfatta almeno una delle seguenti condizioni:

* $X$ contiene una chiave di $R$ ($X$ e' superchiave)
* $A$ appartiene ad almeno una chiave di $R$

(in sostanza mette le due condizioni in OR invece che in AND)

Per $R(movie,\ country,\ agency)$, saremmo già in 3NF perché

* $movie,\ country \rightarrow agency$ rispetta BCNF
* $agency \rightarrow country$ appartiene ad almeno una chiave di $R$

La 3NF e' sempre raggiungibile.

---

Consideriamo $R(person,\ movie,\ birthdate)$

$birthdate$ dovrebbe essere in una relazione dedicata. Le dipendenze funzionali possono mettere in luce questa problematica.

Chiave di questa relazione? $person$ e $movie$.\
Dipendenze funzionali su $R$:

* $person \rightarrow birthdate$

Non siamo in 3NF, decomponiamo:

* $R_1(person,\ birthdate)$ PK $person$
* $R_2(person,\ movie)$ PK $movie$, $person$

La differenza rispetto al solito e' che ora non abbiamo altre dipendenze, quindi scegliamo di creare una ulteriore relazione (la seconda), che non era dipendenza funzionale.

Conservazione delle dipendenze: ok.\
join senza perdita: ok.

---

Consideriamo $R(person,\ city,\ country)$, che descrive il luogo di nascita di una persona in termini di città e paese, dove le città hanno nome univoco/

Chiave: $person$\
Dipendenze funzionali: 

* $person \rightarrow city$
* $city \rightarrow country$

La seconda dipendenza non soddisfa 3NF. Siamo in 2NF; decomposizione:

* $R_1(person,\ city)$ PK $person$
* $R_2(city,\ country)$ PK $city$

E arriviamo in BCNF.

Conservazione delle dipendenze: ok.\
join senza perdita: ok.

### Seconda forma normale

Dipendenza funzionale $X \rightarrow Y$ e' completa se la rimozione di qualsiasi attributo $A$ da $X$ comporta che la dipendenza non sia più valida.

TODO aggiungi testo dalla slide

Non voglio che un attributo dipenda da un pezzo della chiave, come nell'esempio sopra della $birthdate$ TODO aggiungi rimando e spiegazione

Quindi vuol dire che se la chiave e' composta da un solo attributo siamo sempre in 2NF.

---

Consideriamo $R(squadra,\ allenatore,\ citta,\ giocatore)$, dove:

* un giocatore può giocare in una sola squadra (o nessuna)
* un allenatore può allenare una sola squadra (o nessuna)
* una squadra ha un solo allenatore
* una squadra ha diversi giocatori
* una squadra appartiene ad una sola città

Chiave: $giocatore$\
Dipendenze funzionali:

* $squadra \rightarrow allenatore$
* $allenatore \rightarrow squadra$
* $giocatore \rightarrow squadra$
* $squadra \rightarrow citta'$

Siamo in 2NF, decomponiamo:

* $R_1(squadra,\ allenatore)$ PK $squadra$
* $R_2(giocatore,\ squadra)$ PK $giocatore$
* $R_3(squadra,\ citta')$ PK $squadra$

Siamo in 3NF e non possiamo arrivare in BCNF, perché ci sono due dipendenze reciproche.

Conservazione delle dipendenze: ok.\
join senza perdita: ok.

Potremmo anche avere:

* $R_1(squadra,\ allenatore,\ citta')$ PK $squadra$
* $R_2(giocatore,\ squadra)$ PK $giocatore$

Che mantiene tutte le caratteristiche dell'altra.

### Prima forma normale

Tutti gli attributi sono valori atomici.

