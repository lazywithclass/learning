---
tags:
  - italian
  - sweng
---

![Demetra, dea greca della natura, dei raccolti, e delle messi<label class="sidenote-toggle sidenote-number"></label><span class="sidenote">Cosi come i raccolti crescono in campi appositi, i moduli nel software devono operare all’interno dei loro campi di interazione, minimizzando le dipendenze, riducendo il diffondersi della conoscenza del loro funzionamento interno</span>](attachments/Pasted%20image%2020250121212823.png)

##  Obiettivo

Obiettivo e' ridurre l'accoppiamento tra gli oggetti e promuovere la modularità del codice.
E' anche chiamato [principle of least knowledge](https://en.wikipedia.org/wiki/Law_of_Demeter), o in modo colloquiale riassunto con "gli amici dei miei amici non sono miei amici".
Anche citato in "tell don't ask".

Maggiore e' il numero di oggetti con cui parli, maggiore e' la probabilità di rompersi quando questi oggetti cambiano.

> The fundamental goal [...] is to write shy code, code that doesn’t reveal too much of itself to anyone else and doesn’t talk to others any more than is necessary. Shy code keeps to itself, not like that gossipy neighbor who’s involved in everyone else’s comings and goings.

-- https://media.pragprog.com/articles/jan_03_enbug.pdf
## Istruzioni

Limitare le interazioni tra gli oggetti ad un numero ristretto di amici stretti.
Meno conosco gli altri oggetti piu' e' facile che possano cambiare.
Il codice di un metodo dovrebbe accedere solo<label class="sidenote-toggle sidenote-number"></label><span class="sidenote">Limitazioni analoghe a quella che in programmazione funzionale e' una funzione pura</span> a:

* `this`
* i parametri
* oggetti creati all'interno del metodo
* (solo se proprio necessario) oggetti disponibili globalmente

Un esempio di non applicazione potrebbe essere: "Faccio un `get`, manipolo cio' che ho ottenuto, e faccio un `set`."