---
cssclasses: 
tags:
  - databases
  - security
---
TODO vedere le dispense extra messe su Ariel

Obiettivi:

* segretezza - vogliamo prevenire accessi non autorizzati
* integrità - proteggere dati da azioni che ne alterino il contenuto in maniera non lecita
* disponibilità - il software contenuto nella base dati deve essere utilizzabili da tutti coloro che lo devono usare (e' un problema se l'utente autorizzato non riesce a lavorare)

Tecniche per fare ciò:

 * autenticazione - porta di ingresso del DBMS, verifica dell'identità dell'utente
 * controllo dell'accesso - l'utente e' all'interno del perimetro, dobbiamo verificare la coerenza dei permessi in base a ciò che l'utente vuole fare
 * crittografia - protezione dei dati su disco


Concedere accesso e 


## Controllo dell'accesso

Regole che vengono verificate ogni volta che c'e' un accesso ai dati. Ci focalizzeremo su questa parte.

Reference monitor: meccanismo di controllo che ha il compito di stabilire se l'utente e' autorizzato (parzialmente o totalmente) a compiere l'accesso.

TODO integra da slide

Politiche di sicurezza:

* politiche di amministrazione della sicurezza - chi e' il soggetto che concede i diritti
	* centralizzata
	* decentralizzata
	* ownership - chi crea l'oggetto ne gestisce le autorizzazioni 

PostgreSQL applica entrambe, centralizzata e decentralizzata.


Politiche per il controllo dell'accesso:

 * need to know - permette ad ogni utente l'accesso solo ai dati strettamente necessari per eseguire le proprie attività (sistema chiuso)
 * maximized sharing - consente massimo accesso alle informazioni nella base di dati, mantenendo comunque informazioni riservate (sistema aperto)

Sistema aperto: accesso consentito a meno che non sia esplicitamente negato.\
Sistema chiuso: accesso permesso solo se esplicitamente autorizzato.

Granularità - livello minimo a cui arriviamo quando specifichiamo privilegi (possiamo arrivare sino ai singoli attributi).


Tipologie di controllo:

* todo

Politiche discrezionali:

 * todo


I nostri sono sistemi discrezionali.

TODO metti assolutamente per carita' del signore l'attacco di Troia

Ci si salva da questo attacco con una politica mandatoria (che tutelano il movimento - TODO controlla questa cosa tra parentesi).

## Il sistema R

Il comando `GRANT`

TODO integra da slide

`GRANT OPTION` consente ad altri di fare `GRANT` anche ad altri.

`REVOKE` revoca permessi precedentemente concessi, e ricorsivamente anche ad altri ai quali e' stato concesso, a meno che lo stesso privilegio non sia stato concesso anche da altri.

Un utente puo' solo revocare privilegi che esso stesso ha concesso. 

"Un DBA può revocare i privilegi ad un altro DBA" ?
`create user stefano with superuser, password stefano`


---

```language-sql
-- userA (owner del database imdb) segue
GRANT SELECT ON imdb.movie TO userB WITH GRANT OPTION;

-- userB non puo' revocare il privilegio SELECT all'userA
```



## Sysauth e syscolauth

Sono tabelle del catalogo relazionale.


La tabella con Y N etc Riassume i privilegi attivi su tutti gli ogetti della base dati

nome e' grantee, creator e' grantor
T dice se e' Relazione o Vista

E' normale che il creatore dia a se stesso tutti i privilegi, viene messo in automatico alla sua `GRANT`.



Revoca riorsiva

Tolgo all'utente bersaglio, e ricosrivamente a tutti gli utenti che lo avevano ricevuto 

Una buona idea e' pensarla come se fosse una wuery su una tabella

Prendo come grantor quello che ha appena perso il privilegio, e come tabella e privilegio sono quelli appena revocati: se li trovo li levo.



Quando ci sono gli 0 vuol dire che non gli ha concesso l'azione, altrimenti c'e' il tempo al quale e' stato dato.

Per riuscire a risolvere la situazione facciamo un grafo per ogni action (Insert, Select, Delete):

* prendo una riga
* aggiungo il grantor e il grantee
* li collego scrivendo sull'arco il tempo al quale e' stata effettuata la concessione

Poi valuto

* rimuovo gli archi con numero maggiore del tempo della revoke

TODO inserisci grafo disegnato dal prof