let persona = { name: "Alberto" }
console.log(persona.name)

// ma se invece la chiave arrivasse dall'esterno
function getValue(obj, key) {
  return obj[key] // devo per forza usare le quadre
  // perche' se usassi il punto, otterrei il risultato di cercare la chiave
  // chiamata "key" all'interno di obj
}



// abbiamo una stringa di caratteri
// sappiamo che una stringa puo' essere iterata esattamente come un array

// possiamo usare una mappa per rappresentare la relazione esistente tra
// i-esima lettera della stringa e suo numero di occorrenze

let occorrenze = {}

occorrenze.a = 0
occorrenze.a = 1
occorrenze.a = occorrenze.a + 1
occorrenze.a = occorrenze.a + 1

console.log(occorrenze)

// forti di questo
// posso fare che quando vedo una lettera mi chiedo
// la lettera e' presente nella mia mappa?
// se si allora aumento le sue occorrenze di 1
// se no allora imposto le sue occorrenze a 1

// digressione teorica
// come faccio a chiedere ad una mappa se ha una determinata chiave
// quando la chiave sta dentro una variabile?
// ammettiamo di avere la chiave dentro key e che la mappa di chiami map

let map = { a: "3" }
let key = "a"
// se scrivessi
console.log(map.a)
// otterrei "3"
// questo vuol dire vai a cercare la chiave chiamata "key" all'interno di map
console.log(map.key) // undefined
// non vuol dire vai a cercare la chiave dentro la variabile chiamata "key",
// all'interno di map
// quindi per ottenere il valore della chiave contenuta dentro "key" devo
// usare una sintassi diversa:
console.log(map[key]) // "3"
// quando uso questa sintassi sto dicendo: "prendi dentro map il valore
// associato alla chiave che sta dentro la variabile key"



let lettera = "a"
if (occorrenze[lettera]) {
  occorrenze[lettera] = occorrenze[lettera] + 1
} else {
  occorrenze[lettera] = 1
}


// cicliamo controllando ogni singola lettera

function calcolaOccorrenze(stringa) {
  let occorrenze = {}
  for (let i = 0; i < stringa.length; i++) {
    let lettera = stringa[i]
    if (occorrenze[lettera]) {
      occorrenze[lettera] = occorrenze[lettera] + 1
    } else {
      occorrenze[lettera] = 1
    }
  }
  return occorrenze
}

console.log(calcolaOccorrenze("abbaba"))
console.log(calcolaOccorrenze("Sono andato al mercato a comprare cose!"))
