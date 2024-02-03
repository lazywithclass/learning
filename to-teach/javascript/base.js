// tipi di dato

// numeri
// 0 1 42 -5277
// algebra dei numeri + - * / %
// < > <= >=
5 % 2 // 1
42 % 2 // 0


// stringhe
// algebra delle stringhe + (concatenazione)
"Ciao " + "mondo!" // "Ciao mondo!"
"!"
"42"
"Questa e' un'altra stringa!"
"2" + 1 // "21"
1 + "2" // "12"
"2" - 1 // 1
"2" * 2 // 4


// booleani
true false
// && (and) || (or) ! (not)

// tabella di verita' dell'and
// scrivo T e F per non scrivere ogni volta true e false
// and - solo T && T da T
T && T -> T
T && F -> F
F && T -> F
F && F -> F

// or - "basta un operando"
T || T -> T
T || F -> T
F || T -> T
F || F -> F

// not
!T -> F
!F -> T

(false || true && (true && false)) || !false // true
true || (false && !true && false && !false || (true || false))

// come in algebra matematica se tu hai
// (1 + 2) * 3 e' profondamente diverso da 1 + (2 * 3)

1 && true
// per capire come valutare questa espressione booleana
// dobbiamo introdurre il concetto di truthy e falsey
0
false
NaN
undefined // assenza di valore / di dato
null // non ha un valore specificato
""
// tutto cio' che non e' tra questi sei e' truthy
// per valutare una espressione booleana cio' che dovete fare e'
// valutare "sinistra" e "destra"
1 && true // 1 e' "sinistra", true e' "destra"
"ciao" && "mondo"
"" || null


// variabili
// dichiarazione
let square
// dentro square c'e' una assenza di dato
// undefined
square = 6 * 6
// metto il risultato dell'operando "destra" dentro "sinistra",
// che e' una variabile
square = square + 1
square = square * square

let sottrazione = 40
sottrazione = sottrazione - 10
sottrazione = sottrazione - 10

let booleanExpression = (true || square) && (square || sottrazione)
// cosa c'e' dentro booleanExpression qua?

// let (assieme ad altre keyword del linguaggio) e' riservata quindi non
// potete chiamare una variabile cosi
// let let = 5 // no!
// let if = 4 // nemmeno!

// costrutto if
// if serve a valutare codice in determinate condizioni
// caso del cappello dell'ispettore Gadget
// quando piove si apre
let piove = // il valore lo prendiamo da sensori particolari
// da qui in poi dentro piove c'e' un booleano

if (piove) {
  // apro l'ombrello
}

if (42) {

}

let eta = 41
if (eta >= 18) {
  // scrivi a video che l'utente e' maggiorenne
}

let n = 50
if (n % 2 == 0) {

}

if (piove) {

} else {

}

// no! per questioni di logica: "altrimenti" cosa?
else {

}


// annidamento

if (piove) {
  if (sonoContento) {
    // fischietto
  }
}

// if puo' non avere le graffe, MA, e' una grammatica da non usare MAI!
// perche' e' di difficile lettura
if (piove)
  if (sonoContento) {
    // fischietto
  }
  console.log("piove!") // ATTENZIONE! questo viene eseguito sempre, non solo nel caso piove


// esercizio 0
if (piove) {
  if (sonoContento) {
    if (ceIlSole) {
      // a)
    }
  }
}

if (piove && sonoContento && ceIlSole) {
  // a)
}

// esercizio 1
if (piove) {
  if (sonoContento) {
    // a)
  } else {
    // b
  }
}

// ok
if (piove && sonoContento) {
  // a)
}
if (piove && !sonoContento) {
  // b)
}

// no! farsi uno schemino dei valori booleani per cui si arriva a
// a) e b) e' molto utile per chiarirsi le idee
if (piove && sonoContento) {
  // a)
} else {
  // b)
}

// ridondanza nell'algebra dei booleani
qualcosa || false
qualcosa && true
// suggerimento:
n + 0
n * 1


// = e' utilizzato per assegnamento ad una variabile
// == e' utilizzato per il confronto tra quantita'
// === e' utilizzato per il confronto tra quantita' tenendo conto del tipo

42 == 41 // false
"42" == 42 // true, perche' cerca di trasformare sinistra e destra in quantita'
// dello stesso tipo
"42" === 42 // false perche' uno e' una stringa e l'altro e' un numero


// iterazione
// for

// sommiamo i primi 10 numeri
let n = 0
n = n + 1
n = n + 2
n = n + 3
n = n + 4
...

// definisco una variabile somma e la inizializzo a zero
let somma = 0
// creo un ciclo che vada 0 a 10 (escluso), aumentando
// di 1 ad ogni iterazione
// per ogni iterazione sommo un totale al valore corrente di i
for (let i = 0; i < 10000; i = i + 1) {
  somma = somma + i
}

// scrivere del codice che sommi i numeri pari da 0 a 20
// challenge:
// 1) come faccio a capire se il numero corrente nel ciclo e' pari?
// 2) come faccio a mettermi nella condizione trovata al punto 1)?

// soluzione 1)
let somma = 0
for (let i = 0; i <= 20; i = i + 2) {
  somma = somma + i
}

// soluzione 2)
let somma = 0
for (let i = 0; i <= 20; i = i + 1) {
  if (i % 2 == 0) {
    somma = somma + i
  }
}

// scrivere del codice che:
// * sommi 1 se il numero e' pari
// * sommi 2 se il numero e' dispari
// * partendo da 100 e arrivando a 0 (entrambi, 0 e 100, sono inclusi nel range di iterazione)

let somma = 0
for (let i = 100; i >= 0; i = i - 1) {
  let pari = i % 2 == 0
  if (pari) {
    somma = somma + 1
  } else {
    somma = somma + 2
  }
}

// operatore ternario
// voglio mettere dentro la var pari "si" se n e' pari, "no" se n e' dispari
let n = ?
let pari = n % 2 == 0 ? "si" : "no"
//                        ^ equivalente del ramo "then"
//                               ^ equivalente del ramo "else"

let somma = 0
for (let i = 100; i >= 0; i = i - 1) {
  somma = i % 2 == 0 ? somma + 1 : somma + 2
}

// operatore ternario poco si presta a gestire situazioni "annidate"
let stato = ""
if (piove) {
  if (sonoContento) {
    stato = "fischietta"
  } else {
    stato = "non fischietta"
  }
}
stato = piove && (sonoContento ? "fischietta" : "non fischietta")
// ... che pero' e' un disastro da leggere e in realta' non produce proprio lo stesso effetto


// cosa intendo con "proteggere"?
// immaginiamo di avere m e n ottenuti dall'utente
// proteggiamo l'operazione di divisione utilizzando un if
let m = ?
let n = ?
if (n != 0) {
  let risultato = m / n
}

if (qualcosa) {

} else if (altro) {

} else if (altroAncora) {

}

let direction = ?
if (direction == "north") {

} else if (direction == "south") {

} else if (direction == "west") {

} else if (direction == "est") {

} else {
  // errore!
}

// quale sarebbe la differenza, se c'e', con questa implementazione?
if (direction == "north") {

}
if (direction == "south") {

}
if (direction == "west") {

}
if (direction == "est") {

}
// le differenze stanno nella mutua esclusivita'
// e nel fatto che non c'e' un else, ma, a causa della natura
// dei controlli fatti negli if questi due codici sono simili


// questa scrittura...
if (qualcosa) {

} else if (altro) {

}
// ...e questa scrittura
// sono equivalenti
if (qualcosa) {

} else {
  if (altro) {

  }
}

// nota: riguardo i = i + 1
// si puo' scrivere in due modi diversi equivalenti
i++ (i--)
i += 1 (i *= 2, i += 2, ...)

contatore = contatore + 1 // che diventa contatore += 1


// while
// iterazione

while (condizione) {

}

// esercizio
// emulare il comportamento del for seguente utilizzando un while
for (let i = 0; i < 10; i++) {
  // istruzione1
  // istruzione2
}

let i = 0
while (i < 10) {
  // istruzione1
  // istruzione2
  i++
}


// esercizio
// emulare il comportamento del while seguente utilizzando un for
while (true) {

}
// suggerimento per arrivare alla soluzione
// 1) tutte le posizioni del for sono opzionali, posso essere omesse (; rimangono pero')
// 2) collegamento tra punto 2 del for e condizione del while

// soluzione
// for (; true;) {
//
// }

// esercizio
// sommare tutti i numeri dispari tra 1 e 10 compresi usando un while

// spezzare il problema, divide et impera!
// 1) ciclo tra 1 e 10
// 2) capisco come identificare quando il numero attuale e' dispari
// 3) sommo

let somma = 0
let contatore = 1
while (contatore < 10) {
  if (contatore % 2 == 1) {
    somma = somma + contatore
  }
  contatore++
}

// esercizio
// sommare tutti i numeri tra 1 e 10, 10 volte
// spezzando il problema
// 1) mi creo un modo per fare una operazione 10 volte
for (let i = 0; i < 10; i++) {
}
// 2) sommo tutti i numeri tra 1 e 10
for (let i = 0; i <= 10; i++) {
  somma = somma + i
}
// 3) li metto assieme

let somma = 0
for (let i = 0; i < 10; i++) {
  for (let i = 0; i <= 10; i++) {
    somma = somma + i
  }
}

// esercizio
// costruire una stringa che contenga tutti i numeri da 1 a 10, separati dal carattere "-"
// "1-2-3-4-5-6-7-8-9-10"
// divide et impera
// 1) costruire la stringa mettendo il - tra i numeri
// 2) iterazione
// suggerimento ricordatevi dell'algebra sulle stringhe, quale era l'unica operazione per le stringhe?


// esercizio
// stampare la sequenza di numeri da 1 a 10, e
// stampare la sequenza di numeri da 10 a 1,
// 10 volte
// divide et impera
// 1) for 1->10
// 2) for 10->1
// 3) for per farlo 10 volte

// 3)
// scrivo l'implementazione di un for semplice che va da 0 a 10 escluso
for (let i = 0; i < 10; i++) {

}
// fatto
// 1)
for (let i = 1; i <= 10; i++) {
  console.log(i)
}
// fatto
// 2)
for (let i = 10; i >= 1; i--) {
  console.log(i)
}
// fatto
// ora devo mettere assieme

for (let i = 0; i < 10; i++) {
  for (let j = 1; j <= 10; j++) {
    console.log(j)
  }
  for (let k = 10; k >= 1; k--) {
    console.log(k)
  }
}

// esercizio
// creare una stringa che contenga i numeri da 1 a 10 e poi da 10 a 1
//  1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1
// accetto anche questo
//  1 2 3 4 5 6 7 8 9 10 10 9 8 7 6 5 4 3 2 1
// divide et impera
// 1) ciclare da 1->10 accumulando in una stringa
// 2) ciclare da 10->1 accumulando in una stringa
// 3) mettere assieme i due risultati


// esercizio
// calcolare il fattoriale di un numero
// 4! = 4 * 3! = 4 * 3 * 2! = 4 * 3 * 2 = 24
// 2! = 2
// 1! = 1
// 6! = 6 * 5! = 6 * 5 * 4! = 6 * 5 * 4 * 3! = 6 * 5 * 4 * 3 * 2! = 6 * 5 * 4 * 3 * 2 = 720
// divide et impera
// 1) ciclo, da dove parto? Quando mi fermo?
// 2) cosa accumulo per ottenere il risultato?
// 2.1) dove metto l'accumulazione?
// 2.2) come scrivo l'accumulazione?
let fattoriale = 1
let n = 7
for (let i = n; i >= 2; i--) {
  fattoriale = fattoriale * i
  // fattoriale *= i
}

// esercizio
// dato un numero che scegliete voi, tra 1 e 10, stampare la tabellina per quel numero
// esempio per 5 dovete ottenere questo output:
// 0 * 5 = 0
// 1 * 5 = 5
// 2 * 5 = 10
// ...
// 10 * 5 = 50
// divide et impera
// 1) sicuramente ci vuole un ciclo perche' stiamo facendo una operazione 10 volte
// 2) dovrete fare una moltiplicazione e costruire una stringa da passare a console.log

let n = 5
for (let i = 0; i <= 10; i++) {
  let riga = i + " * " + n + " = " + (n * i)
  console.log(riga)
}

// esercizio
// dato un numero scelto da voi, stampare a video un triangolo che abbia questa forma
// per 4
// *
// **
// ***
// ****
// per 5
// *
// **
// ***
// ****
// *****

// idea delle "proprieta' interessanti"
// che in questo caso sono:
// 1) l'altezza del triangolo e' proprio n
// 2) il numero di asterischi aumenta di 1 ad ogni linea (cioe' iterazione)

let n = 5
for (let i = 0; i < n; i++) {
  let asterischi = ""
  asterischi = asterischi + "*"
  console.log(asterischi)
}


// tipi di dato complessi

// array
// strutture dati in cui i valori sono contigui e identificati da un indice
// l'indice va da 0 fino alla lunghezza dell'array - 1

// quando sono utili?
// devo tenere traccia di piu' elementi e mantenerne l'ordine

let arr = [1,2,3,4,5,6]

// combinando array e ciclo for riusciamo a visitare tutti gli elementi
// dell'array

// usando array.length questo codice rimane invariato a prescindere dall'array
// che sto considerando!
for (let i = 0; i < arr.length; i++) {
  console.log(arr[i])
}

// metodi di utilita'

array.length // lunghezza
array.push(1) // mette 1 alla fine dell'array
array.pop()  // toglie l'ultimo elemento dell'array

let array = [1,2,3,4]
array.slice(0, 2) // array adesso e' [1, 2]

array.unshift(0) // array adesso e' [0, 1, 2]
array.shift() // array adesso e' [1, 2]

// digressione, invertire un array
let arr = [1,2,3,4]
let newArr = []
for (let i = arr.length - 1; i >= 0; i--) {
  newArr.push(arr[i])
}

// altrimenti
for (let i = 0; i < arr.length; i++) {
  newArr.unshift(arr[i])
}


// mappe
// strutture dati non ordinate che rappresentano una relazione tra elementi

// ad esempio il nome di una classe e la quantita' di studenti al suo interno
let numerosita = { "3c": 27, "1b": 33, "5a": 10 }

// metodi di utilita'

Object.keys(map) // ritorna le chiavi di map come array
Object.values(map) // ritorna i valori di map come array

// nota riguardo le chiavi

let map = { a: 1 }
map.a // restituisce il valore associato alla chiave a
map["a"] // restituisce il valore associato alla chiave a, che in questo caso
// e' una stringa
let b = "a"
map[b] // restituisce il valore associato alla chiave che si trova dentro
// la variabile b, quindi la stringa "a"

// quindi se scrivessi
map.b
// allora non starei prendendo la chiave contenuta dentro la variabile b
// ma starei prendendo la chiave b

// scope

// la variabile che sto guardando, che valore assume in questo contesto?

for (let i = 0; i < 10; i++) {
  for (let i = 0; i < 10; i++) {
    console.log(i)
  }
}
console.log("ciao")

// la variabile nel loop interno "e' in shadowing" rispetto alla variabile nel
// loop esterno
// variabili con lo stesso nome in scope diversi

// considerate questo
for (let i = 0; i < 10; i++)
  for (let i = 0; i < 10; i++)
    console.log(i)
    console.log("ciao")

// e' equivalente al for innestato precedente, ma e' piu' difficile capire dove
// sono i limiti degli scope

for (let i = 0; i < 10; i++)
  for (let i = 0; i < 10; i++)
    console.log(i)

console.log("ciao")

// scope e livelli

let str = "valore" // scope esterno
for (let i = 0; i < 10; i++) {
  console.log(str + " " + i) // scope interno
}

// lo scope interno ha sempre visibilita' sullo scope esterno
// NON vale il contrario

let str = "valore" // scope esterno
for (let i = 0; i < 10; i++) {
  console.log(str + " " + i) // scope interno
}
console.log(i) // scope esterno
// non posso stampare la i alla riga precedente perche' appartiene ad uno scope
// piu' interno rispetto a quello che sto valutando adesso
