import {
  scacchiera,
  prossimaPosizione,
  isOccupataDaAmico,
  rimuoviPosizioniOccupateDaAmici,
  isFuoriDallaScacchiera,
  isOccupataDaNemico,
} from "./Scacchi.js"

// pos = es. "d8", "h4"
//TODO parte di cattura
export function mosseLegaliPedone(pos) {
  let pedina = scacchiera[pos]
  if (pedina[0] !== "p") {
    return []
  }
  let isBianco = pedina[1] == "b"
  let isAllaPartenza =
    (isBianco && pos[1] == "2") || (!isBianco && pos[1] == "7")
  let isAllaFine = (isBianco && pos[1] == "8") || (!isBianco && pos[1] == "1")
  if (isAllaFine) {
    console.log("GESTIRE!")
    return
  }

  let riga = parseInt(pos[1], 10)
  if (isAllaPartenza) {
    if (isBianco) {
      let posizioni = []
      if (!isOccupataDaAmico(pedina, prossimaPosizione(pos, 0, 1))) {
        posizioni.push(pos[0] + (riga + 1))
      }
      if (!isOccupataDaAmico(pedina, prossimaPosizione(pos, 0, 2))) {
        posizioni.push(pos[0] + (riga + 2))
      }
      return posizioni
    } else {
      let posizioni = []
      if (!isOccupataDaAmico(pedina, prossimaPosizione(pos, 0, -1))) {
        posizioni.push(pos[0] + (riga - 1))
      }
      if (!isOccupataDaAmico(pedina, prossimaPosizione(pos, 0, -2))) {
        posizioni.push(pos[0] + (riga - 2))
      }
      return posizioni
    }
  }
  if (!isAllaPartenza) {
    if (isBianco) {
      let posizioni = []
      if (!isOccupataDaAmico(pedina, prossimaPosizione(pos, 0, 1))) {
        posizioni.push(pos[0] + (riga + 1))
      }
      return posizioni
    } else {
      let posizioni = []
      if (!isOccupataDaAmico(pedina, prossimaPosizione(pos, 0, -1))) {
        posizioni.push(pos[0] + (riga - 1))
      }
      return posizioni
    }
  }
}

export function mosseLegaliCavallo(pos) {
  let prima = prossimaPosizione(pos, 1, 2)
  let seconda = prossimaPosizione(pos, 2, 1)
  let terza = prossimaPosizione(pos, 2, -1)
  let quarta = prossimaPosizione(pos, 1, -2)
  let quinta = prossimaPosizione(pos, -1, -2)
  let sesta = prossimaPosizione(pos, -2, -1)
  let settima = prossimaPosizione(pos, -2, 1)
  let ottava = prossimaPosizione(pos, -1, 2)

  let possibiliMosseLegali = []
  if (prima != "") {
    possibiliMosseLegali.push(prima)
  }
  if (seconda != "") {
    possibiliMosseLegali.push(seconda)
  }
  if (terza != "") {
    possibiliMosseLegali.push(terza)
  }
  if (quarta != "") {
    possibiliMosseLegali.push(quarta)
  }
  if (quinta != "") {
    possibiliMosseLegali.push(quinta)
  }
  if (sesta != "") {
    possibiliMosseLegali.push(sesta)
  }
  if (settima != "") {
    possibiliMosseLegali.push(settima)
  }
  if (ottava != "") {
    possibiliMosseLegali.push(ottava)
  }
  return rimuoviPosizioniOccupateDaAmici(scacchiera[pos], possibiliMosseLegali)
}

export function mosseLegaliTorre(pos) {
  let possibiliMosseLegali = []
  let num = 1

  while (
    !isOccupataDaAmico(scacchiera[pos], prossimaPosizione(pos, 0, num)) &&
    !isFuoriDallaScacchiera(pos, 0, num)
  ) {
    if (isOccupataDaNemico(scacchiera[pos], prossimaPosizione(pos, 0, num))) {
      possibiliMosseLegali.push(prossimaPosizione(pos, 0, num))
      break
    }
    possibiliMosseLegali.push(prossimaPosizione(pos, 0, num))
    num++
  }
  num = -1
  while (
    !isOccupataDaAmico(scacchiera[pos], prossimaPosizione(pos, 0, num)) &&
    prossimaPosizione(pos, 0, num) != ""
  ) {
    if (isOccupataDaNemico(scacchiera[pos], prossimaPosizione(pos, 0, num))) {
      possibiliMosseLegali.push(prossimaPosizione(pos, 0, num))
      break
    }
    possibiliMosseLegali.push(prossimaPosizione(pos, 0, num))
    num--
  }
  num = 1
  while (
    !isOccupataDaAmico(scacchiera[pos], prossimaPosizione(pos, num, 0)) &&
    !isFuoriDallaScacchiera(pos, num, 0)
  ) {
    if (isOccupataDaNemico(scacchiera[pos], prossimaPosizione(pos, num, 0))) {
      possibiliMosseLegali.push(prossimaPosizione(pos, num, 0))
      break
    }
    possibiliMosseLegali.push(prossimaPosizione(pos, num, 0))
    num++
  }
  num = -1
  while (
    !isOccupataDaAmico(scacchiera[pos], prossimaPosizione(pos, num, 0)) &&
    !isFuoriDallaScacchiera(pos, num, 0)
  ) {
    if (isOccupataDaNemico(scacchiera[pos], prossimaPosizione(pos, num, 0))) {
      possibiliMosseLegali.push(prossimaPosizione(pos, num, 0))
      break
    }
    possibiliMosseLegali.push(prossimaPosizione(pos, num, 0))
    num--
  }
  return possibiliMosseLegali
}

export function mosseLegaliAlfiere(pos) {
  let possibiliMosseLegali = []
  let numN = 1
  let numL = 1
  while (
    !isOccupataDaAmico(scacchiera[pos], prossimaPosizione(pos, numL, numN)) &&
    !isFuoriDallaScacchiera(pos, numL, numN)
  ) {
    if (
      isOccupataDaNemico(scacchiera[pos], prossimaPosizione(pos, numL, numN))
    ) {
      possibiliMosseLegali.push(prossimaPosizione(pos, numL, numN))
      break
    }
    possibiliMosseLegali.push(prossimaPosizione(pos, numL, numN))
    numL++
    numN++
  }
  numN = -1
  numL = 1
  while (
    !isOccupataDaAmico(scacchiera[pos], prossimaPosizione(pos, numL, numN)) &&
    !isFuoriDallaScacchiera(pos, numL, numN)
  ) {
    if (
      isOccupataDaNemico(scacchiera[pos], prossimaPosizione(pos, numL, numN))
    ) {
      possibiliMosseLegali.push(prossimaPosizione(pos, numL, numN))
      break
    }
    possibiliMosseLegali.push(prossimaPosizione(pos, numL, numN))
    numL++
    numN--
  }
  numL = -1
  numN = -1
  while (
    !isOccupataDaAmico(scacchiera[pos], prossimaPosizione(pos, numL, numN)) &&
    !isFuoriDallaScacchiera(pos, numL, numN)
  ) {
    if (
      isOccupataDaNemico(scacchiera[pos], prossimaPosizione(pos, numL, numN))
    ) {
      possibiliMosseLegali.push(prossimaPosizione(pos, numL, numN))
      break
    }
    possibiliMosseLegali.push(prossimaPosizione(pos, numL, numN))
    numL--
    numN--
  }
  numN = 1
  numL = -1
  while (
    !isOccupataDaAmico(scacchiera[pos], prossimaPosizione(pos, numL, numN)) &&
    !isFuoriDallaScacchiera(pos, numL, numN)
  ) {
    if (
      isOccupataDaNemico(scacchiera[pos], prossimaPosizione(pos, numL, numN))
    ) {
      possibiliMosseLegali.push(prossimaPosizione(pos, numL, numN))
      break
    }
    possibiliMosseLegali.push(prossimaPosizione(pos, numL, numN))
    numL--
    numN++
  }
  return possibiliMosseLegali
}

export function mosseLegaliRegina(pos) {
  let possibiliMosseLegali = mosseLegaliTorre(pos).concat(
    mosseLegaliAlfiere(pos),
  )
  return possibiliMosseLegali
}
// non si possa scavalcare i propri amici
// tranne il cavallo
//
