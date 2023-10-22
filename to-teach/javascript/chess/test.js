const {
  legalPawnMoves,
  legalTowerMoves,
  legalKnightMoves,
  legalBishopMoves
} = require('./chess.js')


let chessboard = [
  ['bT',  'bP', '', '', '', '', 'nP', 'nT'],
  ['bC',  'bP', '', '', '', '', 'nP', 'nC'],
  ['bA',  'bP', '', '', '', '', 'nP', 'nA'],
  ['bR',  'bP', '', '', '', '', 'nP', 'nR'], // 3
  ['bRe', 'bP', '', '', '', '', 'nP', 'nRe'],
  ['bA',  'bP', '', '', '', '', 'nP', 'nA'],
  ['bC',  'bP', '', '', '', '', 'nP', 'nC'],
  ['bT',  'bP', '', '', '', '', 'nP', 'nT'],
]

// let lpm = legalPawnMoves(chessboard, [3,3])
// console.log('Pawn:',lpm)

// let ltm = legalTowerMoves(chessboard, [3,4])
// console.log('Tower:', ltm)


let chessboardForKnight = [
  ['', '', '', '', '', '', '', ''],
  ['',   '', '', '', '', '', '', ''],
  ['',   '', '', '', '', '', '', ''],
  ['',   '', '', '', '', '', '', ''], // 3
  ['',   '', '', 'bC', '', '', '', ''],
  ['',   '', '', '', '', '', '', ''],
  ['',   '', '', '', '', '', '', ''],
  ['',   '', '', '', '', '', '', ''],
]

// let lkm = legalKnightMoves(chessboardForKnight, [4,3])
// console.log('Knight:', lkm)


// Come esercizi nel caso non finisca
// regina
// re
// sistemare bug
// arrocco

let chessboardForBishop = [
  ['', '', '', '',   '', '', '', ''],
  ['', '', '', '',   '', '', '', ''],
  ['', '', '', '',   '', '', '', ''],
  ['', '', '', 'bA', '', '', '', ''], // 3
  ['', '', '', '',   '', '', '', ''],
  ['', '', '', '',   '', '', '', ''],
  ['', '', '', '',   '', '', '', ''],
  ['', '', '', '',   '', '', '', ''],
]

let lbm = legalBishopMoves(chessboardForBishop, [3,3])
console.log('Alfiere:', lbm)
