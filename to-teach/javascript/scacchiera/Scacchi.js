// t = torre
// c = cavallo
// a = alfiere
// q = regina
// k = re
// p = pedone
// b = bianco
// n = nero

// qualsiasi relazione non presente indica una posizione vuota
export let scacchiera = {
  a1: "tb",
  b1: "cb",
  c1: "ab",
  d1: "qb",
  e1: "kb",
  f1: "ab",
  g1: "cb",
  h1: "tb",
  a2: "pb",
  b2: "pb",
  c2: "pb",
  d2: "pb",
  e2: "pb",
  f2: "pb",
  g2: "pb",
  h2: "pb",

  a8: "tn",
  b8: "cn",
  c8: "an",
  d8: "qn",
  e8: "kn",
  f8: "an",
  g8: "cn",
  h8: "tn",
  a7: "pn",
  b7: "pn",
  c7: "pn",
  d7: "pn",
  e7: "pn",
  f7: "pn",
  g7: "pn",
  h7: "pn",
}

let traduzioneGrafica = {
  qb: "♕",
  kb: "♔",
  ab: "♗",
  cb: "♘",
  tb: "♖",
  pb: "♙",
  qn: "♛",
  kn: "♚",
  an: "♝",
  cn: "♞",
  tn: "♜",
  pn: "♟︎",
}

export function stampaScacchiera() {
  let pos = "a8"
  let riga = ""
  let contatore = 0
  while (pos) {
    let pedina = scacchiera[pos]
    riga = riga + (traduzioneGrafica[pedina] || " ")
    contatore++
    if (contatore == 8) {
      console.log(riga)
      riga = ""
      contatore = 0
    }
    pos = prossimaPosizionePerStampa(pos)
  }
}
export function rimuoviPosizioniOccupateDaAmici(pedina, posizioni) {
  let posizioniLegali = []
  for (let i = 0; i < posizioni.length; i++) {
    if (!isOccupataDaAmico(pedina, posizioni[i])) {
      posizioniLegali.push(posizioni[i])
    }
  }
  return posizioniLegali
}

export function isOccupataDaAmico(pedina, pos) {
  let altraPedina = scacchiera[pos]
  if (!altraPedina) {
    return false
  }

  let pedinaColore = pedina[1]
  let altraPedinaColore = altraPedina[1]
  return pedinaColore == altraPedinaColore
}

export function isOccupataDaNemico(pedina, pos) {
  let altraPedina = scacchiera[pos]
  if (!altraPedina) {
    return false
  }

  let pedinaColore = pedina[1]
  let altraPedinaColore = altraPedina[1]
  return pedinaColore != altraPedinaColore
}

export function prossimaPosizione(
  posAttuale,
  variazioneLettera,
  variazioneNumero,
) {
  let lettera = prossimaLettera(posAttuale, variazioneLettera)
  let numero = prossimoNumero(posAttuale, variazioneNumero)
  if (lettera == "" || numero == -1) {
    return ""
  }
  return lettera + numero
}

export function isFuoriDallaScacchiera(
  posAttuale,
  variazioneLettera,
  variazioneNumero,
) {
  return (
    prossimaPosizione(posAttuale, variazioneLettera, variazioneNumero) == ""
  )
}

function prossimoNumero(pos, num) {
  let prox = parseInt(pos[1], 10) + num
  if (prox >= 1 && prox <= 8) {
    return prox
  }
  return -1
}

function prossimaLettera(pos, num) {
  let lettere = ["a", "b", "c", "d", "e", "f", "g", "h"]
  let lettera = pos[0]
  let indice = lettere.indexOf(lettera)
  indice = indice + num
  if (indice >= 0 && indice < lettere.length) {
    return lettere[indice]
  }
  return ""
}

export function elementoCasuale(array) {
  return array[Math.floor(Math.random() * array.length)]
}

function prossimaPosizionePerStampa(pos) {
  if (pos[1] == "0") {
    return ""
  }
  if (pos[0] == "a") {
    return "b" + pos[1]
  }
  if (pos[0] == "b") {
    return "c" + pos[1]
  }
  if (pos[0] == "c") {
    return "d" + pos[1]
  }
  if (pos[0] == "d") {
    return "e" + pos[1]
  }
  if (pos[0] == "e") {
    return "f" + pos[1]
  }
  if (pos[0] == "f") {
    return "g" + pos[1]
  }
  if (pos[0] == "g") {
    return "h" + pos[1]
  }
  if (pos[0] == "h") {
    return "a" + (parseInt(pos[1], 10) - 1)
  }
}
