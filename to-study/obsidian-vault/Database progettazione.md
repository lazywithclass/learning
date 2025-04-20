---
cssclasses: []
tags:
  - databases
  - italian
---

Come costruire un database, come progettarlo.

![Attività di progettazione](Pasted%20image%2020250420190513.png)

## Esempi

Si modelli un diagramma ER che descrive una realtà ospedaliera in cui i medici curano i pazienti 

![Schema ER di una realtà ospedaliera](Pasted%20image%2020250416210616.png)

* quali sono le entità che voglio rappresentare? Le chiamo al singolare, sto descrivendo un singolo oggetto di quel tipo. Uso il rettangolo per comunicare che sto mostrando una entità
* metto solamente quegli attributi che sono presenti nella realtà che sto modellando, se parlo di pazienti non avrò `id`, ma avrò `tessera_sanitaria`
* porre attenzione agli attributi composti come ad esempio `residenza`: non voglio concatenare tutto in una stringa
* esprimo la cardinalità con `(x, y)` dove `x` e' la cardinalità minima, `y` la cardinalità massima
	* la cardinalità minima non può essere indeterminata
	* la cardinalità minima e' sempre meno della massima
	* la cardinalità va espressa per entrambe le direzioni della relazione
	* la cardinalità della relazione la si capisce guardando la cardinalità massima sui due lati della relazione (esempio se avessi `(0,N)` e `(1,N)` sarebbe una cardinalità molti a molti)



