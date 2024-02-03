// scrivere del codice che stampi "solo vocali" se nella stringa ci sono
// solo vocali, "mix" altrimenti


let input = "aeioc"

// 1)
// salviamo il numero di vocali e alla fine del lavoro controlleremo se
// il numero di vocali e' uguale al numero di lettere

let vocali = 0
for (let i = 0; i < input.length; i++) {
  if (input[i] == "a" || input[i] == "e" ||input[i] == "i" ||input[i] == "o" || input[i] == "u") {
    vocali = vocali + 1
    // vocali++
    // vocali += 1
  }
}

if (vocali == input.length) {
  console.log("solo vocali")
} else {
  console.log("mix")
}


// 2)
// creo una variabile che contiene il numero di lettere nella parola
// ciclo sulla parola
// ogni volta che trovo una vocale decremento la variabile
// alla fine se la variabile e' 0 la parola conteneva solo vocali

let numLettere = input.length
for (let i = 0; i < input.length; i++) {
  if (input[i] == "a" || input[i] == "e" ||input[i] == "i" ||input[i] == "o" || input[i] == "u") {
    numLettere--
  }
}

if (numLettere == 0) {
  console.log("solo vocali")
} else {
  console.log("mix")
}
