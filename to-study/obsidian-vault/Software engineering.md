---
cssclasses:
  - cornell-note
tags:
  - software-engineering
  - italian
---

> [!info]
> This will be mainly in italian

[[Software engineering#Lezione 1 - 25/09]]

## Lezione 1 - 25/09

Head first design patterns 
Fundamentals of software engineering
A handbook of software and systems engineering (+)

reti di petri

tdd metodologia di sviluppo sw, non di testing

lab
git
gradle
intellij
jdk 17 o 21

turno a matricole pari 
turno b matricole dispari

- reference escaping (che se presente invalida praticamente tutta la encapsulation)
- interface segregation
- nullability
- mocking
- fattorizzazione codice tramite pattern strategy o template

---

comunicazione tra sviluppatori e' un fattore importante nello sviluppo software
il software deve fare cio' che voglio, con qualita'
"per come ho creato il software ti garantisco che funziona"

voglio delle metriche che siano correlati rispetto al mio obiettivo di qualita'
next step, definisco un tool che lo faccia

### Problemi

* numero di persone coinvolte - il programmatore non e' il cliente -> comunicazione 
* dimensioni software, sia come linee di codice che come tempo uomo per scriverle
overhead comunicazione cresce al crescere del numero di persone che lavorano su un programma, quindi non basta tirare sviluppatori al problema
* software - la storia viene cambiata mentre la si sta scrivendo, come se in un libro al cap 9 un personaggio nel capitolo 1 morisse

Un buon processo puo' produrre un prodotto di qualita'

cmmi
livelli
0 - dipendenza dall'eroe, come se avessi Achille come programmatore, ma cosa succede se sparisce? Dipendo da lui?
...


### Qualita'

valore per gli stakeholder
scelte: "ottimizzo sullo spazio o sul tempo?"

vedi libro su qualita'

Deve 
funzionare
bello
farmi ricco

#### Funziona

funziona diverso da compila

correttezza: fa cio' che e' stato chiesto, confronto con le specifiche
requisiti - volatile, arrivato dall'esterno, desiderata del cliente
specifiche - scritto, cosa io ho capito che il cliente voleva, contratto tra cliente e programmatore

<aside>L1</aside>
R.Glass' Law (L1) vedi libro

affidabilita': mi posso fidare 

che non faccia male: innocuita' e robustezza (si comporta bene anche rispetto a cose non dette)


book test: tiro un libro sulla tastiera perche' cosi genero un input non aspettato, il software deve continuare a funzionare

#### Bello

usabilita': facile da usare 
adattabilita' all'esigenza dell'utente e' una caratteristica dell'usabilita'

<aside>L26</aside>
Nielsen-Norman's Law (L26) vedi libro

velocita'
efficienza nell'uso delle risorse

pulito
<aside>Punto richiesto all'esame</aside>
verificabilita, posso dimostrare che e' corretto

#### Farmi ricco

riusabilita' dei componenti
rivendo un software gia' fatto per un altro cliente, lo riuso cosi non impiego tempo
Mi porto dietro affidabilita' di quel software

<aside>L15</aside>
McIlroy's law (L15) vedi libro
Vedi incidente Ariane5, il riuso va bene ma di qualita'

manutenibilita'
semplificare gli interventi post consegna
riparabilita'
evolvibilita'

<aside>L27 L28</aside>
M Lehman's Law (L27 e L28) vedi libro
design for change

### Processo

funzionare
essere bello 
farmi diventare ricco


resistere agli imprevisti - robustezza

bello - sono veloce, produttivo

cogliere l'attimo - tempismo