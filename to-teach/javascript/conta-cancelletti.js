// contare tutti i cancelletti presenti dentro una stringa data in
// input alla funzione

// cosa ci serve?
// un posto dove mettere il conteggio -> variabile
// dovremo controllare tutti i caratteri della stringa -> ciclo
// dovremo controllare se l'i-esimo carattere e' un # -> if


function contaCancelletti(stringa) {
  let conteggio = 0
  for (let i = 0; i < stringa.length; i++) {
    if (stringa[i] === "#") {
      conteggio = conteggio + 1
    }
  }
  return conteggio
}

console.log(contaCancelletti("--###-##-######--"))
console.log(contaCancelletti("--"))





// creo una variabile MAX
// creo una variabile CURRENT
// ciclo su tutta la stringa
//     se il carattere corrente e' un #
//         aumento di 1 CURRENT
//     se il carattere corrente e' un -
//         se CURRENT > MAX
//             imposto MAX al valore di CURRENT
//         (qui sono fuori dall'if direttamente qua sopra)
//         imposto CURRENT a 0
// ritorno MAX

function countMax(string) {
  let max = 0
  let current = 0
  for (let i = 0; i < string.length; i++) {
    if (string[i] === "#") {
      current = current + 1
    }
    if (string[i] === "-") {
      if (current > max) {
        max = current
      }
      current = 0
    }
  }
  if (current > max) {
    max = current
  }
  return max
}

console.log(countMax("--#-##-###--"))
