let string = "~~~ お前はもう死んでいる - e' la frase da utilizzare! ~~~"

// creiamo una mappa
// per visitare tutti i caratteri della stringa dobbiamo ciclare su questa
// per ogni carattere incontrato
// c'e' dentro la mappa?
//   se si aumento il conteggio di 1
// se no imposto il conteggio a 1

let occurrencies = {}

for (let i = 0; i < string.length; i++) {
  let character = string[i]
  if (occurrencies[character]) { // se questo carattere e' nella mappa
    occurrencies[character] = occurrencies[character] + 1
  } else {
    occurrencies[character] = 1
  }
}
console.log(occurrencies)


occurrencies = {}
for (let i = 0; i < string.length; i++) {
  let character = string[i]
  occurrencies[character] = occurrencies[character] ? 
    occurrencies[character] + 1 : 1
}
console.log(occurrencies)
