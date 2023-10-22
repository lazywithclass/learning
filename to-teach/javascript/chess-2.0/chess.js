// ad esempio dato a1 e data il movimento in alto
// quale e' il risultato?
//
// dato pos prendo la parte di destra, anche chiamata riga
// trasformo dx da stringa in numero
// sommo 1 alla parte di destra
// prendo sinistra e la concateno a destra appena sommata
//
// assumiamo che guardare su voglia SEMPRE dire verso l'alto dove il basso e'
// occupato dai pezzi bianchi
function lookUp(pos) {
  return lookVertically(pos, 1)
}

function lookDown(pos) {
  return lookVertically(pos, -1)
}

// esempio di raccolta a fattor comune, astraendo cio' che cambia
// nella variabile direction
function lookVertically(pos, move) {
  let sx = pos[0]
  let dx = pos[1]
  dx = Number(dx) + move
  return sx + dx
}

// assumo che pos sia 'c4'
// prendo da pos la parte sx
// prendo da pos la parte dx
// se sx e' a allora mi salvo b come nuova sx
// se sx e' b allora mi salvo c come nuova sx
// ...
// concateno sx e dx
function lookRight(pos) {
  // let sx = pos[0]
  // let dx = pos[1]
  // if (sx === 'a') sx = 'b'
  // if (sx === 'b') sx = 'c'
  // if (sx === 'c') sx = 'd'
  // if (sx === 'd') sx = 'e'
  // if (sx === 'e') sx = 'f'
  // if (sx === 'a') sx = 'g'
  // if (sx === 'g') sx = 'h'
  // return sx + dx

  // OPPURE
  //
  // let letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
  //                                  // ^
  // // data la e, come prendo la prossima lettera?
  // let sx = pos[0]
  // let dx = pos[1]
  // let currentIndex = letters.indexOf(sx)
  // sx = letters[currentIndex + 1]
  // return sx + dx
  //
  // OPPURE

  // sapendo che:
  // console.log('c'.charCodeAt(0))
  // console.log(String.fromCharCode(100))
  let sx = pos[0]
  let dx = pos[1]
  let sxCharCode = sx.charCodeAt(0)
  sx = String.fromCharCode(sxCharCode + 1)
  return sx + dx
}

function lookLeft(pos) {
  let sx = pos[0]
  let dx = pos[1]
  let sxCharCode = sx.charCodeAt(0)
  sx = String.fromCharCode(sxCharCode - 1)
  return sx + dx
}

// pseudocodice
// quand'e' che siamo fuori dai limiti?
// se noi stabiliamo quali sono i limiti, poi allora ci bastera' vedere
// se i valori che per ora abbiamo chiamato sx e dx sono all'interno di questi
// limiti
// prendiamo due strutture dati che rappresentino bene questi limiti
// array!
//
// creiamo un array di valori legali per le righe
// creiamo un array di valori legali per le colonne
// se sx sta dentro alle righe e destra sta dentro alle colonne
//     siamo nei limiti
// altrimenti
//     no
function isOutsideBoundaries(chessboard, pos) {
  // pero' passando la chessboard
  let keys = Object.keys(chessboard)
  return keys.indexOf(pos) === -1

  // oppure non troppo bello
  // let sx = pos[0].charCodeAt(0)
  // let dx = parseInt(pos[1], 10)
  // return sx < rows[0].charCodeAt(0) || sx > rows[7].charCodeAt(0) ||
  //   dx < 1 || dx > 8
  //
  // oppure meglio ma con duplicazione di coordinate
  // let rows = ['a','b','c','d','e','f','g','h']
  // let columns = ['1','2','3','4','5','6','7','8']
  // return rows.indexOf(pos[0]) === -1 || columns.indexOf(pos[1]) === -1
}

// assumiamo pos = 'a1'
function getColor(chessboard, pos) {
  return chessboard[pos][0]
}

// assumiamo che chiamiamo sempre isWhite su un pezzo e mai sullo spazio vuoto
function isWhite(chessboard, pos) {
  let color = getColor(chessboard, pos)
  return color === 'b'
}

function isEmpty(chessboard, pos) {
  return chessboard[pos] === ''
}

// pseudocodice
// ritorno se alla posizione che sto guardando c'e' un pezzo con colore diverso
// dal mio
// assumiamo che passiamo coordinate
function isEnemy(chessboard, me, other) {
  return !isEmpty(chessboard, me) && !isEmpty(chessboard, other) &&
    getColor(chessboard, me) !== getColor(chessboard, other)
}

// obiettivo e' far muovere il pedone secondo le sue regole:
// * avanti di uno
// * avanti di due se e' alla riga di partenza
// * diagonale di uno a sx se li c'e' un nemico
// * diagonale di uno a dx se li c'e' un nemico
// * promozione
//
// pseudocodice
// se non c'e' qualcosa davanti (amico o nemico)
//     il pedone puo' muoversi in avanti di uno
// se non c'e' qualcosa davanti di due (amico o nemico) e il pedone e' in
// posizione di partenza
//     il pedone puo' muoversi in avanti di due
// se in diagonale a sx c'e' un nemico
//     il pedone puo' muoversi in diagonale a sx, catturandolo
// se in diagonale a dx c'e' un nemico
//     il pedone puo' muoversi in diagonale a dx, catturandolo
function legalPawnMoves(chessboard, pos) {

  function lookOneStepForward(white, pos) {
    return white ? lookUp(pos) : lookDown(pos)
  }

  let legalMoves = []
  let white = isWhite(chessboard, pos)
  let oneStep = lookOneStepForward(white, pos)

  // il pedone appena arriva al bordo DEVE essere promosso, quindi in teoria
  // qui non dovremmo mai arrivare
  if (isOutsideBoundaries(chessboard, pos)) {
    return []
  }

  if (isEmpty(chessboard, oneStep)) {
    legalMoves.push(oneStep)
  }

  let twoStep = lookOneStepForward(white, oneStep)
  let isAtStartingPosition = white ? pos[1] === '2' : pos[1] === '7'
  if (isEmpty(chessboard, oneStep) && isEmpty(chessboard, twoStep) && isAtStartingPosition) {
    legalMoves.push(twoStep)
  }

  let oneStepLeft = lookLeft(oneStep)
  if (!isOutsideBoundaries(chessboard, oneStepLeft) && isEnemy(chessboard, pos, oneStepLeft)) {
    legalMoves.push(oneStepLeft)
  }

  let oneStepRight = lookRight(oneStep)
  if (!isOutsideBoundaries(chessboard, oneStepRight) && isEnemy(chessboard, pos, oneStepRight)) {
    legalMoves.push(oneStepRight)
  }

  return legalMoves
}

function printBetterChessboard(chessboard) {
  let chessIconsMap = {
    'bT': '♖',
    'bC': '♘',
    'bA': '♗',
    'bR': '♕',
    'bRe': '♔',
    'bP': '♙',
    'nT': '♜',
    'nC': '♞',
    'nA': '♝',
    'nR': '♛',
    'nRe': '♚',
    'nP': '♟︎'
  }

  // pseudocodice
  // ciclare su tutte le chiavi della chessboard
  // per ognuna
  //     prendo la relativa icona del pezzo
  //     la metto in una stringa
  //     ogni 8 stampo la stringa
  //     ri-inizializzo la stringa a ''
  //

  let keys = Object.keys(chessboard)
  // metto un valore arbitrario davanti cosi ho gli indici ai valori corretti
  keys.unshift(0)
  let row = ''
  for (let i = 1; i < keys.length; i++) {
    let key = keys[i]
    let piece = chessboard[key]
    row += chessIconsMap[piece] ? ` ${chessIconsMap[piece]}` : ' '
    if (i % 8 == 0) {
      console.log(row)
      row = ''
    }
  }
}

// TODO promozione pedone
// alla fine del movimento
// se il pedone e' "a fine corsa"
// if (pos[1] === '8' || pos[1] === '1') { }
//     scegliamo casualmente uno tra torre, cavallo, alfiere, regina
//     lo mettiamo al posto del pedone


module.exports = {
  legalPawnMoves,
  printBetterChessboard
}
