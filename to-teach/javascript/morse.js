// vogliamo tradurre da italiano a morse
// intuizione: possiamo pensarla come ad una relazione che mette in
// collegamento una lettera dell'alfabeto italiano con i relativi
// simboli dell'alfabeto morse


let mappa = {
  "a": ".-",
  "b": "-...",
  "c": "-.-.",
  "d": "-..",
  "e": ".",
  "f": "..-.",
  "g": "--.",
  "h": "....",
  "i": "..",
  "l": ".-..",
  "m": "--",
  "n": "-.",
  "o": "---",
  "p": ".--.",
  "q": "--.-",
  "r": ".-.",
  "s": "...",
  "t": "-",
  "u": "..-",
  "v": "...-",
  "z": "--.."
}

function traduciInMorse(stringa) {
  let tradotta = ""
  for (let i = 0; i < stringa.length; i++) {
    let lettera = stringa[i]
    if (lettera !== " ") {
      tradotta = tradotta + mappa[lettera]
    }
  }
  return tradotta
}

console.log(traduciInMorse("una frase in morse"))

// qui si vede la potenza delle funzioni
// perche' potrei passare la mappa alla funzione e allora, posto che
// i linguaggi che tratto siano tutti traducibili lettera per lettera,
// quindi ottenere

function traduci(traduzione, stringa) {
  let tradotta = ""
  for (let i = 0; i < stringa.length; i++) {
    let lettera = stringa[i]
    if (lettera !== " ") {
      tradotta = tradotta + traduzione[lettera]
    }
  }
  return tradotta
}

console.log(traduci(mappa, "una frase in morse"))

let mappa2 = {
  "a": "aa",
  "b": "b",
  "c": "c",
  "d": "d",
  "e": "ee",
  "f": "f",
  "g": "g",
  "h": "h",
  "i": "ii",
  "l": "l",
  "m": "m",
  "n": "n",
  "o": "oo",
  "p": "p",
  "q": "q",
  "r": "r",
  "s": "s",
  "t": "t",
  "u": "uu",
  "v": "v",
  "z": "z"
}

console.log(traduci(mappa2, "una frase in morse"))
