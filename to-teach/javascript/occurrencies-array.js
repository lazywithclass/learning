// data una stringa calcolare il numero di occorrenze di ogni vocale

// per esempio nella stringa abituate
// ci sono 2 a, 1 i, 1 u, 1 e


// mi creo 5 variabili, una per vocale
// poi faccio un ciclo in modo da visitare tutte le lettere della stringa
// controllo se la lettera corrente e' una delle 5 vocali
// se lo e' aumento la relativa variabile

let input = "abituate"

let a = 0
let e = 0
let i = 0
let o = 0
let u = 0

for (let index = 0; index < input.length; index++) {
  if (input[index] == "a") {
    a++
  } else if (input[index] == "e") {
    e++
  } else if (input[index] == "i") {
    i++
  } else if (input[index] == "o") {
    o++
  } else if (input[index] == "u") {
    u++
  }
}

console.log(a, e, i, o, u)

// potrei utilizzare anche un array
// adotto la convenzione che la a e' in posizione 0, la e in posizione 1, etc

// problema: dobbiamo crearci un array inizializzato a 0

// soluzione 1
let vowels = []
for (let i = 0; i < 5; i++) {
  vowels[i] = 0
}

// soluzione 2
vowels = Array(5).fill(0)

for (let index = 0; index < input.length; index++) {
  if (input[index] == "a") {
    vowels[0]++
  } else if (input[index] == "e") {
    vowels[1]++
  } else if (input[index] == "i") {
    vowels[2]++
  } else if (input[index] == "o") {
    vowels[3]++
  } else if (input[index] == "u") {
    vowels[4]++
  }
}
console.log(vowels)


// oppure ma meno leggibile
vowels = []
for (let index = 0; index < input.length; index++) {
  if (input[index] == "a") {
    vowels[0] = vowels[0] ? vowels[0] + 1 : 1
  } else if (input[index] == "e") {
    vowels[1] = vowels[1] ? vowels[1] + 1 : 1
  } else if (input[index] == "i") {
    vowels[2] = vowels[2] ? vowels[2] + 1 : 1
  } else if (input[index] == "o") {
    vowels[3] = vowels[3] ? vowels[3] + 1 : 1
  } else if (input[index] == "u") {
    vowels[4] = vowels[4] ? vowels[4] + 1 : 1
  }
}
console.log(vowels)

