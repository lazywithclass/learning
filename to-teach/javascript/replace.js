// data una stringa in ingresso s
// data una stringa in ingresso replace
// data una stringa in ingresso replacer
//
// sostituire replace con replacer dentro s



// metodo coi puntatori
// pseudocodice
//
// creo una variabile che conterra' la stringa di ritorno, RES
// ciclo su S
//     mi chiedo se il carattere su cui sono con il ciclo
//     e' uguale al primo carattere di replace
//
//     se si allora inizio un altro ciclo in cui controllo
//         se tutti i caratteri di questa sottostringa che sto guardando
//         sono uguali a replace
//             se si allora metto replacer dentro RES
//             se no metto quella originale
//     se no metto dentro RES tutta questa sottostringa su cui sto avanzando
//     che in sostanza vuol dire arrivare allo spazio
//
// ritorno RES
//

function replaceString(s, replace, replacer) {
  let res = ''

  for (let i = 0; i < s.length; i++) {
    if (s[i] === replace[0]) {
      let fromS = ''
      let all = true
      for (let j = 0; j < replace.length; j++) {
        fromS += s[j]
        if (s[i + j] !== replace[j]) {
          all = false
        }
      }

      if (all) {
        res += replacer + ' '
      } else {
        res += fromS
      }
      i += replace.length
    } else {
      res += s[i]
    }
  }

  return res
}

// let res = replaceString("ciao sono io", "sono", "saro'")
// console.log(res)



// ci riproviamo
// assumiamo che si possano sostituire solo parole intere
// dove una parola e' una stringa separata da spazi
//
// divido i problemi
//
// primo sottoproblema: trovare i candidati alla sostituzione
// candidati alla sostituzione sono tutte le stringhe che sono separate da
// spazio
//
// mi creo una variabile che conterra' i candidati, CANDIDATI
// ciclo sulla stringa
//     il carattere attuale e' uno spazio?
//         se si
//             allora metto CURR dentro CANDIDATI
//             svuoto CURR
//         se no
//             metto il carattere corrente dentro CURR
// metto CURR dentro CANDIDATI
// ritorno CANDIDATI

// assumo che non ci sia punteggiatura
function candidates(s) {
  let candidates = []
  let curr = ''
  for (let i = 0; i < s.length; i++) {
    if (s[i] === ' ') {
      candidates.push(curr)
      curr = ''
    } else {
      curr += s[i]
    }
  }
  candidates.push(curr)
  return candidates
}

// secondo sottoproblema:
// l'ennesimo candidato e' da sostituire?
//
// creo una variabile che contiene la stringa finale, RES
// ciclo tutti i candidati
//     prendo il candidato corrente, CANDIDATO
//     CANDIDATO e' uguale a REPLACE?
//         se si
//             metto REPLACER dentro RES con uno spazio dopo
//         se no
//             metto CANDIDATO dentro RES con uno spazio dopo
// ritorno RES

function substitute(candidates, replace, replacer) {
  let res = ''
  for (let i = 0; i < candidates.length; i++) {
    let candidate = candidates[i]
    if (candidate === replace) {
      res += replacer + ' '
    } else {
      res += candidate + ' '
    }
  }
  return res
}

// let cand = candidates("ciao sono io")
// let res = substitute(cand, "sono", "saro'")
// console.log(res)


// pseudocodice
// voglio ottenere tutte possibili stringhe che uno vorrebbe sostituire
// ad esempio da ciao voglio ottenere
// c ci cia ciao i ia iao o
//
// lavoro su candidates che gia' mi dava una buona base
// per ognuno uso due puntatori
// con il primo parto dall'inizio
// con il secondo scorro il resto della stringa
// accumulo i caratteri dentro una stringa e poi li metto in un risultato
function allCandidates(candidates) {
  let all = []
  for (let i = 0; i < candidates.length; i++) {
    let candidate = candidates[i]

    let current = []
    for (let j = 0; j < candidate.length; j++) {
      for (let k = j; k < candidate.length; k++) {
        current.push(candidate.substring(j, k+1))
      }
    }
    all.push(current)
  }
  return all
}

// let cand = candidates("ciao sono io")
// let all = allCandidates(cand)
// console.log(all)





// pseudocodice
// ciclare su tutti gli oggetti
//     prendo la proprieta' type dell'oggetto
//     in base alle regole
//     aggiorno l'altra chiave
//
// come prendo l'altra chiave?
// sono nella situazione di avere due chiavi, se conosco una delle due
// beh... l'altra... e' proprio l'altra

// dato un array di chiavi a lunghezza 2, ritorno il nome della chiave
// che non si chiama "type"
function choose(keys) {
  // if (keys[0] === 'type') {
  //   return keys[1]
  // } else {
  //   return keys[0]
  // }
  return keys[0] === 'type' ? keys[1] : keys[0]
}

function update(arr) {
  for (let i = 0; i < arr.length; i++) {
    let current = arr[i]
    let type = current.type
    let key = choose(Object.keys(current))

    if (current[key] !== null) {
      continue
    }

    if (type === 'boolean') {
      current[key] = false
    } else if (type === 'string') {
      current[key] = ''
    } else if (type === 'number') {
      current[key] = 0
    } else if (type === 'array') {
      current[key] = []
    } else if (type === 'object') {
      current[key] = {}
    }
  }
}

let arr = [
  { maggiorenne: null, type: 'boolean' },
  { nome: null, type: 'string' },
  { cognome: 'Rossi', type: 'string' },
  { parents: null, type: 'array' },
  { data: null, type: 'object' }
]
update(arr)
console.log(arr)
