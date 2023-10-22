const {
    legalPawnMoves,
    printBetterChessboard
} = require('./chess.js')


let chessboard = {
    'a8': 'nT', 'b8': 'nC', 'c8': 'nA', 'd8': 'nR', 'e8': 'nRe', 'f8': 'nA', 'g8': 'nC', 'h8': 'nT',
    'a7': 'nP', 'b7': 'nP', 'c7': 'nP', 'd7': 'nP', 'e7': 'nP' , 'f7': 'nP', 'g7': 'nP', 'h7': 'nP',
    'a6': ''  , 'b6': ''  , 'c6': ''  , 'd6': ''  , 'e6': ''   , 'f6': ''  , 'g6': ''  , 'h6': ''  ,
    'a5': ''  , 'b5': ''  , 'c5': ''  , 'd5': ''  , 'e5': ''   , 'f5': ''  , 'g5': ''  , 'h5': ''  ,
    'a4': ''  , 'b4': ''  , 'c4': ''  , 'd4': ''  , 'e4': ''   , 'f4': ''  , 'g4': ''  , 'h4': ''  ,
    'a3': ''  , 'b3': ''  , 'c3': ''  , 'd3': ''  , 'e3': ''   , 'f3': ''  , 'g3': ''  , 'h3': ''  ,
    'a2': 'bP', 'b2': 'bP', 'c2': 'bP', 'd2': 'bP', 'e2': 'bP' , 'f2': 'bP', 'g2': 'bP', 'h2': 'bP',
    'a1': 'bT', 'b1': 'bC', 'c1': 'bA', 'd1': 'bR', 'e1': 'bRe', 'f1': 'bA', 'g1': 'bC', 'h1': 'bT'
}

// function turno(chessboard, color) {
//     let coorPezzi = getPiecesByColor(chessboard, color)
//     let pezziConMosse = []
//     for (let i = 0; i < coorPezzi.length; i++) {
//         const coorPezzo = coorPezzi[i];
//         let mosse = getLegalMoves(chessboard, coorPezzo)
//         if (mosse.length !== 0) {
//             pezziConMosse.push(coorPezzo)
//         }
//     }
//     let coorPezzoCasuale = randomElement(pezziConMosse)
//     let mossePezzoCasuale = getLegalMoves(chessboard, coorPezzoCasuale)
//     let coorMossaCasuale = randomElement(mossePezzoCasuale)
//     return updateChessboard(chessboard, coorPezzoCasuale, coorMossaCasuale)
// }

// function playGame (chessboard) {
//     let color = 'b'
//     for (let i = 0; i < 10; i++) {
//         if (i % 2 === 0) {
//             color = 'b'
//         }else {
//             color = 'n'
//         }
//         chessboard = turno(chessboard, color)
//         printBetterChessboard(chessboard)
//         console.log('**************')
//     }

// }
// playGame(chessboard)

function randomElement(array){
  return array[Math.floor((Math.random()*array.length))]
}

console.log(legalPawnMoves(chessboard, 'c4'))
