// approccio array

// il pezzo in posizione A2 puo' andare in posizione A3?

// movePawn assume che i suoi parametri siano gia' stati tradotti dal
// linguaggio scacchistico?
// Se si, perche'?

// assumiamo di avere dentro start la coordinata in numeri della posizione
// perche':
// * vogliamo tenere la difficolta' bassa
// * vogliamo che la funzione faccia una ed una sola "cosa"
function legalPawnMoves(chessboard, start) {
  // alla posizione start c'e' un pedone? Perche' se non c'e', c'e' un problema

  // da qui effettivamente alla pos start c'e' un pedone
  // nella prossima posizione legale per il pedone, c'e' qualcuno?
  //

  // ad esempio se io avessi 0,2
  // la posizione legale per il pedone e' 0,3
  let x = start[0]
  let y = start[1]

  let legalMoves = []

  // TODO promozione del pedone

  if (!isOutOfTheChessboard(x - 1, y + 1)) {
    let possibleEnemy = chessboard[x - 1][y + 1]
    if (possibleEnemy != '' && isEnemy(chessboard[x][y], possibleEnemy)) {
      legalMoves.push([x - 1, y + 1])
    }
  }

  if (!isOutOfTheChessboard(x + 1, y + 1)) {
    possibleEnemy = chessboard[x + 1][y + 1]
    if (possibleEnemy != '' && isEnemy(chessboard[x][y], possibleEnemy)) {
      legalMoves.push([x + 1, y + 1])
    }
  }

  // possibili opportunita'
  // []
  // [sx]
  // [dx]
  // [sx, dx]

  // se il pedone ha un ostacolo davanti non si puo' muovere in avanti
  if (chessboard[x][y + 1] !== '') {
    return legalMoves
  }

  // se il pedone e' alla posizione di partenza
  if (start[1] == 1 && isEmpty(chessboard, x, y + 2)) {
    legalMoves.push([start[0], start[1] + 1])
    legalMoves.push([start[0], start[1] + 2])
    return legalMoves
  }

  legalMoves.push([start[0], start[1] + 1])
  return legalMoves
}

function legalBishopMoves(chessboard, start) {
  let moves = [moveLeftUp, moveRightUp, moveDownRight, moveDownLeft]
  return legalBishopTowerMoves(chessboard, start, moves)
}

function legalTowerMoves(chessboard, start) {
  let moves = [moveLeft, moveUp, moveRight, moveDown]
  return legalBishopTowerMoves(chessboard, start, moves)
}

function legalBishopTowerMoves(chessboard, start, moves) {
  // l'algoritmo sottostante va ripetuto per tutte le direzioni

  // scelgo una direzione in cui muovermi
  // creo un array vuoto di mosse legali
  // fino a che non sono arrivato al bordo
  //     se la casella attuale e' vuota
  //         la aggiungo alle mosse legali
  //     se nella casella attuale c'e' un nemico
  //         la aggiungo alle mosse legali, ed ho finito
  //     se nella casella attuale c'e' un amico
  //         ho finito

  function checkDirection(move) {
    let legalMoves = []
    let x, y
    [x, y] = move(start[0], start[1])
    while (!isOutOfTheChessboard(x, y)) {
      if (isEmpty(chessboard, x, y)) {
        legalMoves.push([x, y])
      } else if (isEnemyAtPos(chessboard, start, [x, y])) {
        legalMoves.push([x, y])
        return legalMoves
      } else {
        // amico
        return legalMoves
      }

      [x, y] = move(x, y)
    }

    return legalMoves
  }

  let legalMoves = []
  for (let i = 0; i < moves.length; i++) {
    let move = moves[i]
    let legalMovesForDirection = checkDirection(move)
    legalMoves = legalMoves.concat(legalMovesForDirection)
  }

  return legalMoves
}

function legalKnightMoves(chessboard, start) {
  function upLeft(start) {
    let movement = moveN(start[0], start[1], moveUp, 2)
    movement = moveLeft(movement[0], movement[1])
    return movement
  }

  function upRight(start) {
    let movement = moveN(start[0], start[1], moveUp, 2)
    movement = moveRight(movement[0], movement[1])
    return movement
  }

  function rightUp(start) {
    let movement = moveN(start[0], start[1], moveRight, 2)
    movement = moveUp(movement[0], movement[1])
    return movement
  }

  function rightDown(start) {
    let movement = moveN(start[0], start[1], moveRight, 2)
    movement = moveDown(movement[0], movement[1])
    return movement
  }

  function downRight(start) {
    let movement = moveN(start[0], start[1], moveDown, 2)
    movement = moveRight(movement[0], movement[1])
    return movement
  }

  function downLeft(start) {
    let movement = moveN(start[0], start[1], moveDown, 2)
    movement = moveLeft(movement[0], movement[1])
    return movement
  }

  function leftDown(start) {
    let movement = moveN(start[0], start[1], moveLeft, 2)
    movement = moveDown(movement[0], movement[1])
    return movement
  }

  function leftUp(start) {
    let movement = moveN(start[0], start[1], moveLeft, 2)
    movement = moveUp(movement[0], movement[1])
    return movement
  }

  // facciamo la sua mossa
  // la casella di arrivo e' vuota?
  //     se si aggiungo la posizione alle mosse legali
  // la casella di arrivo contiene un nemico?
  //     se si aggiungo la posizione alle mosse legali

  let legalMoves = []
  let moves = [
    upLeft, upRight,
    rightUp, rightDown,
    downRight, downLeft,
    leftDown, leftUp
  ]
  for (let i = 0; i < moves.length; i++) {
    let move = moves[i]
    let target = move(start)

    // guard
    if (isOutOfTheChessboard(target[0], target[1])) {
      continue
    }

    if (isEmpty(chessboard, target[0], target[1])) {
      legalMoves.push(target)
    } else if (isEnemyAtPos(chessboard, start, target)) {
      legalMoves.push(target)
    }
  }

  return legalMoves
}

function legalQueenMoves(chessboard, start) {
  let moves = [moveLeft, moveUp, moveRight, moveDown, moveLeftUp, moveRightUp, moveDownRight, moveDownLeft]
  return legalBishopTowerMoves(chessboard, start, moves)
}

// le posizioni tra il re e la torre a sinistra sono libere?
// se sono in questa situazione
//     allora posso arroccare
//
// Pseudocodice scritto sbagliato! I test ci aiutano? Si!
//
// Lo pseudocodice dovrebbe tenere conto anche della presenza di
// re e torre nelle corrette posizioni

// assumiamo che color sia 'b' o 'n' e che l'arrocco sia possibile farlo
// solo "verso l'alto" della nostra chessboard
function canCastle(chessboard, color) {
  if (color === 'b') {
    let positionsAreEmpty = isEmpty(chessboard, 1, 0) &&
      isEmpty(chessboard, 2, 0) &&
      isEmpty(chessboard, 3, 0)

    if (!positionsAreEmpty) {
      return false
    }

    let isTower = chessboard[0][0] === 'bT'
    let isKing = chessboard[4][0] === 'bRe'
    return isTower && isKing
  }
  if (color === 'n') {
    let positionsAreEmpty = isEmpty(chessboard, 1, 7) &&
      isEmpty(chessboard, 2, 7) &&
      isEmpty(chessboard, 3, 7)

    if (!positionsAreEmpty) {
      return false
    }

    let isTower = chessboard[7][7] === 'nT'
    let isKing = chessboard[4][7] === 'nRe'
    return isTower && isKing
  }
}

// higher order function
function moveN(x, y, move, n) {
  for (let i = 0; i < n; i++) {
    [x, y] = move(x, y)
    // oppure
    // let movement = moveLeft(x, y)
    // x = movement[0]
    // y = movement[1]
  }
  return [x, y]

}

function moveLeft(x, y) {
  return [x - 1, y]
}

function moveUp(x, y) {
  return [x, y + 1]
}

function moveRight(x, y) {
  return [x + 1, y]
}

function moveDown(x, y) {
  return [x, y - 1]
}

function moveLeftUp(x, y) {
  return [x - 1, y + 1]
}

function moveRightUp(x, y) {
  return [x + 1, y + 1]
}

function moveDownRight(x, y) {
  return [x + 1, y - 1]
}

function moveDownLeft(x, y) {
  return [x - 1, y - 1]
}

function isEmpty(chessboard, x, y) {
  return chessboard[x][y] === ''
}

// chiamata cosi: isEnemyAtPos([x,y], [x,y])
function isEnemyAtPos(chessboard, posMe, posOther) {
  let me = chessboard[posMe[0]][posMe[1]]
  let other = chessboard[posOther[0]][posOther[1]]
  return isEnemy(me, other)
}

// chiamata cosi: isEnemy('bP', 'nA')
function isEnemy(me, other) {
  return me[0] != other[0]
}

// se almeno uno tra x e y e' fuori allora il punto e' fuori
function isOutOfTheChessboard(x, y) {
  return x < 0 || x > 7 || y < 0 || y > 7
}


module.exports = {
  legalPawnMoves,
  legalTowerMoves,
  legalKnightMoves,
  legalBishopMoves,
  legalQueenMoves,
  canCastle
}
