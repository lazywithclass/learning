---
cssclasses:
  - cornell-note
tags:
  - italian
---

Principio del minimo sapere. "Gli amici dei miei amici non sono miei amici".

Non farti dare "qualcosa", ma chiedi quel "qualcosa" (tell don't ask).

Limitare le interazioni tra gli oggetti ad un numero ristretto di amici stretti.
Meno conosco gli altri oggetti piu' e' facile che possano cambiare.

<aside>obiettivo</aside>
Obiettivo e' ridurre l'accoppiamento tra gli oggetti e promuovere la modularita' del codice.

Il codice di un metodo dovrebbe accedere solo a:
* `this`
* i parametri
* oggetti creati all'interno del metodo
* (solo se proprio necessario) oggetti disponibili globalmente

# Esempio di non applicazione

Faccio un `get`, manipolo cio' che ho ottenuto, e faccio un `set`.


