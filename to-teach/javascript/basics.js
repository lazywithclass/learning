// risorsa simile: https://learnxinyminutes.com/docs/javascript/
// https://eloquentjavascript.net/


// types

// interi 5
// float 3.14
// NaN
// stringhe "5", "ciao", "Nel mezzo del cammin..."
// boolean true, false

// diverso comportamento dei tipi in diversi contesti
5 + 5 // 10
5 + "5" // "55"
true + 5 // 6, ma non affidatevi a questi comportamenti poco leggibili, in particolare
// quando state "facendo una operazione tra pere e mele" alzate le antenne
25 % 2 // 1 operatore resto

"a" < "b" // true
"Z" < "b" // true

5 || true // true
0 || true // true
0 || false // false
true || 0 // true

// or e and come operatori di selezione
false || 25 // vi ritorna, seleziona, 25

// falsey, tutto cio' che non e' qua sotto e' truthy
// [] e {} non sono falsey
0
null
undefined
NaN
""
false

// variables
let i = 0
const PI = 3.14
a = 0 // Non farlo mai! a finisce nello scope globale!

// scoping

// scope esterno NON vede dentro scope interno, scope interno vede scope esterno
// { e } definiscono i margini degli scope, occhio che la variabile definita nella
// posizione 1 nel for (inizializzazione variabile) ha come scope quello del for

// shadowing
// quando una variabile con nome n in uno scope piu' interno mette in ombra una variabile 
// con nome n presente in uno scope esterno
 
// control structures

// if

// quando l'espressione presente nella condizione dell'if restituisce valore truthy 
// allora il corpo dell'if viene eseguito
if (1) { console.log("SI!") }
if ("0") { console.log("SI!") }
if (0) { console.log("NO!") }
if ("") { console.log("NO!") }

// il ramo else di un if viene eseguito solamente se l'if non e' stato eseguito
if (false) {
    console.log("NO!")
} else {
    console.log("SI!")
}

if (false) {
    console.log(1)
} else if (false) {
    console.log(2)
} else if (false) {
    console.log(3)
} else {
    console.log(4)
}

// perche' usare else if?
let numero = 10
// mutualmente esclusivo!
if (numero < 20) {

} else if (numero < 15) {

} else {

}

// ... che e' molto diverso da
if (numero < 20) {

}
if (numero < 15) {

}
// else?

// ... che e' molto diverso da
if (numero < 20) {
    if (numero < 15) {

    }
} else {

}

// evitare annidamenti che portano alla "forma a freccia"
if (true) {
    if (true) {
        if (true) {
            console.log('urca!')
        }
        // ...
    }
    // ...
}

// for 

// il for viene utilizzato quando sapete esattamente quante volte 
// dovete iterare

// 1 inizializzazione indice
// 2 condizione di permanenza nel ciclo
// 3 modifica dell'indice
// 4 corpo
//       1        2      3 
for (let j = 0; j < 10; j++) {
    console.log(j) // 4
}
// ordine di esecuzione: 1 2 4 3 2 4 3 2 4...

// la modifica non e' detto che sia sempre i++
// potete saltare di due, di n, non farla, etc

// l'inizializzazione non e' detto che parta sempre da 0
// potete partire dalla fine, da meta', dal prossimo, etc

// possono mancare una o tutte le sezioni
// questo infatti e' un ciclo infinito
// for (;;) { }

// while

// fintanto che
// il while si differenzia dal for perche' non sai a priori quante volte verra' eseguito

let e = 0 // 1 del for
while (e < 10) { // 2 del for
    console.log(e) // 4 del for 
    e++ // 3 del for
}

// se dovessi tradurre questo con un for...
let interruttore = true
// while (interruttore) {
//     console.log('bip')
// }
// ...sarebbe:
// for (let interruttore2 = true; interruttore2;) {
//     console.log('bip')  
// }
// ci si apetta che interruttore e interruttore2 vengano modificati da "qualcuno"

// NB: non innestare mai tre for uno dentro l'altro se si vuole avere una esecuzione veloce!

// functions

// perche? servono a astrarre concetti
// quando scrivere una funzione? ogni volta che avete un compito ben preciso e deliminato 
// da assegnarle

// algoritmo per la preparazione di una torta
// let acquisti = spesa(lista)
// let preparato = mescolareIngredienti(uova, farina, zucchero, latte, sale, lievito) oppure mescolareIngredienti(ingredienti)
// versareNellImpasto(preparato)
// accendereFornoAllaGiustaTemperatura() oppure accendereFornoAllaTemperatura(temp)
// infornare(preparato)
// attendere(tempoInSecondi)
// let torta = sfornare()

// declaration
function nome(argomento1, argomento2) {
    console.log(argomento1, argomento2)
}

// dichiarazione di una funzione anonima (anche detta lambda) e suo assegnamento 
// ad una variabile
let fn = function() {
    console.log('lambda')
}

// arrow function
let T = () => true
// che tradotta usando la "vecchia" sintassi e'
let T1 = function() { return true }

// utilita' dell'arrow function
console.log([1,2,3,4,5,6].reduce((s, n) => s + n, 0))

// differenza arrow function e "quell'altra"
function quellAltra() {

}
const arrowFunction = () => "arrow"
const circleArea = r => 3.14 * r * r

// importanza delle parentesi graffe, sempre!
let qualcosa = ""
if (qualcosa)
    console.log(qualcosa)
    console.log(qualcosa)
// con della stanchezza sulle spalle questo potrebbe essere interpretato come 
if (qualcosa) {
    console.log(qualcosa)
    console.log(qualcosa)
}

// invocation
nome("ciao", "mondo")
fn()

let tabellinaMigliore = n => {
    let res = []
    for (let i = 1; i <= 10; i++) {
        res.push(n * i)
    }
    return res
}
tabellinaMigliore(3) 

// data structures

// array

// struttura contigua di elementi (potenzialmente) eterogenei, ognuno dei quali identificato da un indice, che parte da 0 e arriva 
// a lunghezza dell'array - 1

// segue un esempio di array:
//          0   1  2
let arr = [ 9, 42, 98 ]

// in un array posso metterci anche funzioni, segue esempio "inutile"
let tabellina = [
    n => n * 1,
    n => n * 2,
    n => n * 3,
    n => n * 4,
    n => n * 5,
    n => n * 6,
    n => n * 7,
    n => n * 8,
    n => n * 9,
    n => n * 10
]

// da molti a 1
[1,2,3].reduce((s, n) => s + n, 0) // 6
// esempio di reduce implementata da noi
function reduce(arr, fn, initial) {
    const res = initial
    for (let i = 0; i < arr.length; i++) {
        res = fn(arr[i], res)
    }
    return res
}

// da molti a molti, non cambia mai la dimensione dell'array
[1,2,3].map(n => n * 2) // [2,4,6]
// esempio di map implementata da noi
function map(arr, fn) {
    const res = []
    for (let i = 0; i < arr.length; i++) {
        res.push(fn(arr[i]))
    }
    return res
}

// da molti a range 0 -> molti
[1,2,3].filter(n => n % 2 == 0) // [2]
function filter(arr, fn) {
    const res = []
    for (let i = 0; i < arr.length; i++) {
        if (fn(arr[i])) {
            res.push(arr[i])
        }
    }
    return res
}


// map

// rappresentano una relazione tra la chiave e il valore
// ad esempio quante volte ogni carattere appare in una stringa
// ogni volta che sentite "occorrenze" ricordavi che probabilmente vi servira' una mappa

// in JavaScript mappa e' sinonimo di oggetto

let map = new Map()

// metodi essenziali

// per sapere se nella mappa esiste una relazione tra "chiave" e qualcosa
// e restituisce un booleano
map.has("chiave") 

// per ottenere l'altra parte della relazione, vi restituisce il valore
map.get("chiave") 

// per impostare un valore alla chiave specificata
map.set("chiave", "valore")
// se eseguita un'altra volta semplicemente va in override con la precedente
// quindi avro' sempre una sola "chiave" che punta ad un solo "valore"
map.set("chiave", "valore")
// se invece faccio
map.set("chiave", "valore2")
// allora ottengo un aggiornamento del valore associato a "chiave"

// oggetti

let persona = {
    nome: 'Nikola',
    surname: 'Tesla',
    greeting: () => "BZZZZZ!!!"
}
console.log(persona.greeting())

console.log(persona.nome)
// oppure in modo equivalente
console.log(persona['name'])
// che e' utile quando non conoscete il valore della chiave, perche' ad esempio e' in una variabile

function stampaDettaglioPersona(chiave) {
    console.log(persona.chiave) // ritorna undefined, perche' non esiste alcuna chiave di nome "chiave"
    console.log(persona[chiave])
}

// set

// rappresenta il concetto di insieme, nel senso matematico del termine
// simile agli array, unica differenza e' che non accetta duplicati:
let nomi = new Set()
nomi.add('Luca')
nomi.add('Giorgia')
nomi.add('Luca') // non aggiunge due volte 'Luca', ma non e' un errore
