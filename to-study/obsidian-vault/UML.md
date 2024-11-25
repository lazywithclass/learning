---
cssclasses:
  - cornell-note
tags:
  - italian
---

Questa parte e' devastante.
Espandere appena hai la forza. Auguri.

# Use case

E' il dialogo con il cliente.
Fornisce un insieme di scenari relazionati tra loro

* diverse modalita' di fare un compito
* interazione normale e possibili eccezioni
* pre e post condizioni
* flusso di esecuzione
* eccezioni e loro trattamento

### Utilizzo

* identificazione attori
* denominazione del tipo di interazione
* collegamenti attore - attore e caso d'uso - caso d'uso

#### Attore

E' una entita' esterna al sistema, non per forza un umano, che interagisce col sistema.
E' fonte o destinatario del sistema.

"Attore non e' lo studente avente matricola XYZ, e' 'lo studente'".

### Identificazione

Per identificare gli use case posso partire dagli attori
* cosa fanno?
* cosa vogliono?

### Associazioni

* uno use case deve essere associato ad almeno un attore
* un attore deve essere associato ad almeno uno use case
* un attore detto primario ha il compito di far partire lo use case

Ne esistono tra use case:
* inclusione: una parte dello use case
* estensione: preso uno use case ne faccio una estensione

#### Generalizzazione

Permette di esplicitare relazioni tra ruoli, es uno `StaffMember` e' anche un `LibraryMember`.

Simile a estensione, ma sostituisco alcune parti della descrizione, mentre ne eredito altre.

## Scenari

Descrizioni di come il sistema e' usato in pratica

* utili nella raccolta dei requisiti, casi specifici
* posso essere usati come esempi

# Diagramma delle classi

Recuperare dalle slide

# Diagramma degli stati

Recuperare dalle slide

# Diagramma di sequenza

Recuperare dalle slide

# Diagramma delle attivita'

Usato per 
* flusso all'interno di un metodo
* flusso di uno use-case
* logica all'interno di un business process

Gli stati sono attivita'.

## Sincronizzazione

![|300](uml-sincronizzazione.png)

Barre nere sono sincronizzazione.
Faccio prima Activity1 e poi Activity2 e Activity3 possono partire contemporaneamente on in pseudo-concorrenza non deterministica.
Quando entrambe terminano parto con un'altra attivita'.
Ha sempre inizio e fine.

## Decisioni

![|300](uml-decisioni.png)

## Swim lane

![|300](uml-swimlane.png)

Rappresentano le responsabilita' sulle singole attivita'

# Diagrammi dei componenti

![|300](component-diagram.png)

Permette di raggruppare ragionando in termini di componenti fisici

# Diagramma di deploy