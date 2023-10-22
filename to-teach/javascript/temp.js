let departments = `
Cancelleria
500
40
60

Servizi igienici
1000
1
200

Vendite
0
`

// pseudocodice
// splittiamo su \n per ottenere un array
// ciclare su tutti gli elementi
//     riconosciamo l'inizio di un reparto
//     mettiamo il nome del reparto in un oggetto
//     riconosciamo l'inizio delle spese
//     sommiamo tutte le spese fino a che non troviamo stringa vuota
//     man mano aggiornando l'oggetto
//
// ciclare su tutti gli oggetti
//     mi tengo da parte il min e il max
//
// ritorno min e max

function getMinMax(input) {
  let departments = []
  let department = { name: '', tot: 0 }

  let split = input.split('\n')
  for (let i = 0; i < split.length; i++) {
    let parsed = parse(split[i])
    if (split[i] === '') {
      if (department.name !== '') {
        departments.push(department)
      }
      department = { name: '', tot: 0 }
      continue
    }
    if (parsed[0]) {
      department.tot += parsed[1]
    } else {
      department.name = split[i]
    }
  }
  if (department.name !== '') {
    departments.push(department)
  }

  // assumo che ci sia sempre almeno un reparto
  let result = [ departments[0], departments[0] ]
  for (let i = 0; i < departments.length; i++) {
    if (departments[i].tot > result[0].tot) {
      result[0] = departments[i]
    }
    if (departments[i].tot < result[1].tot) {
      result[1] = departments[i]
    }
  }

  return result
}

// ritorna un array, dove
// * in prima posizione c'e' un booleano che ci dice se string e' un numero
// * in seconda posizione, opzionalmente c'e' un numero
function parse(string) {
  let parsed = parseInt(string, 10)
  return isNaN(parsed) ? [false] : [true, parsed]
}

// let minMax = getMinMax(departments)
// console.log(JSON.stringify(minMax, null, '  '))



// pseudocodice
// isMad(s)
//     ritorna true se uno tra
//         non include Lui Lei Egli Ella
//         se finisce con "?!?"
//         include "Cthulhu"
//         prima e ultima parola finiscono in "are" "ere" "ire"
//         inizia con della punteggiatura
//     ritorna false altrimenti
//
// isNeverMad(s)
//     ritorna true se
//         include Church
//         include mare
//     altrimenti ritorna false
//
//
// se isNeverMad(s)
//     return false
// return isMad(s)

function isMad(string) {
  let ends = string.endsWith('?!?')
  let oldOne = string.includes('Cthulhu')

  let first = string.split(' ')[0]
  let last = string.split(' ')[1]
  let areEreIre = ['are', 'ere', 'ire'].some(s => first.endsWith(s)) &&
      ['are', 'ere', 'ire'].some(s => last.endsWith(s))

  let punct = ',.!?:;-~'.split('')
  let startsWithPunct = punct.indexOf(string[0]) > -1

  return !subject || ends || oldOne || areEreIre || startsWithPunct
}

function isNeverMad(string) {
  // return string.includes('Church') || string.includes('mare')
  return ['Church', 'mare'].some(s => string.includes(s))
}

function madness(string) {
  if (isNeverMad(string)) {
    return false
  }
  return isMad(string)
}

console.log(madness('.Quando guardi a lungo nell’abisso, l’abisso ti guarda dentro.'))
console.log(madness('Andare a rimirare'))
console.log(madness('~ Pensava sempre al mare come a la mar, come lo chiamano in spagnolo quando lo amano ~'))
console.log(madness('~ Papa’, come sta Church? ~'))
