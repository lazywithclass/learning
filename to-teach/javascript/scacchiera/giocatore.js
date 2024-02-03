import { elementoCasuale, scacchiera } from "./Scacchi.js"
import {
  mosseLegaliPedone,
  mosseLegaliTorre,
  mosseLegaliAlfiere,
  mosseLegaliCavallo,
  mosseLegaliRegina,
} from "./pedine.js"

// in base al colore prendiamo pedina casuale
// in base alla pedina presa ne prendiamo un movimento legale casuale

// chiedere alla scacchiera tutte le chiavi
// accumuliamo tutte le chiavi relative alle pedine del colore cercato
function posizioniPedinePerColore(colore) {
  let posizioni = Object.keys(scacchiera)
  let coloreChiavi = []
  for (let i = 0; i < posizioni.length; i++) {
    let posizione = posizioni[i]
    if (scacchiera[posizione][1] == colore) {
      coloreChiavi.push(posizione)
    }
  }
  return coloreChiavi
}

function tutteLeMosseLegaliPerColore(colore) {
  let posizioni = posizioniPedinePerColore(colore)
  let mosseLegali = []

  for (let i = 0; i < posizioni.length; i++) {
    let pos = posizioni[i]
    let pedina = scacchiera[pos]

    if (pedina[0] == "p") {
      mosseLegali = mosseLegali.concat(mosseLegaliPedone(pos))
    }
    if (pedina[0] == "c") {
      mosseLegali = mosseLegali.concat(mosseLegaliCavallo(pos))
    }
    if (pedina[0] == "t") {
      mosseLegali = mosseLegali.concat(mosseLegaliTorre(pos))
    }
    if (pedina[0] == "a") {
      mosseLegali = mosseLegali.concat(mosseLegaliAlfiere(pos))
    }
    if (pedina[0] == "q") {
      mosseLegali = mosseLegali.concat(mosseLegaliRegina(pos))
    }
  }
  return mosseLegali
}

export function mossa(colore) {
  let posizioni = tutteLeMosseLegaliPerColore(colore)
  let pos
  let pedina
  let mosseLegali
  for (let i = 0; i < posizioni.length; i++) {
    pos = posizioni[i]
    pedina = scacchiera[pos]
    mosseLegali = []
    if (pedina[0] == "p") {
      mosseLegali = mosseLegaliPedone(pos)
    }
    if (pedina[0] == "c") {
      mosseLegali = mosseLegaliCavallo(pos)
    }
    if (pedina[0] == "t") {
      mosseLegali = mosseLegaliTorre(pos)
    }
    if (pedina[0] == "a") {
      mosseLegali = mosseLegaliAlfiere(pos)
    }
    if (pedina[0] == "q") {
      mosseLegali = mosseLegaliRegina(pos)
    }
    if (mosseLegali.length > 0) {
      break
    }
  }
  if (mosseLegali.length == 0) {
    console.log("non ci sono mosse legali!")
  } else {
    let nuovaPosizione = elementoCasuale(mosseLegali)
    if (nuovaPosizione) {
      delete scacchiera[pos]
      scacchiera[nuovaPosizione] = pedina
    }
  }
}
