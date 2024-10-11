// limitazione
// abbiamo 9 variabili da poter utilizzare per il tris
// non possiamo usare altre strutture dati per rappresentare la griglia


let c1 = ""
let c2 = ""
let c3 = ""
let c4 = ""
let c5 = ""
let c6 = ""
let c7 = ""
let c8 = ""
let c9 = ""


function isGameFinished() {
  // casi di vittoria
  // 1) stesso simbolo su ogni riga (3)
  // 2) stesso simbolo su ogni colonna (3)
  // 3) stesso simbolo su ogni diagonale (2)

  // 1)
  if ((c1 === c2 && c2 === c3 && c3 === "X") ||
      (c4 === c5 && c5 === c6 && c6 === "X") ||
      (c7 === c8 && c8 === c9 && c9 === "X") ||

      (c1 === c4 && c4 === c7 && c7 === "X") ||
      (c2 === c5 && c5 === c8 && c8 === "X") ||
      (c3 === c6 && c6 === c9 && c9 === "X") ||

      (c1 === c5 && c5 === c9 && c9 === "X") ||
      (c3 === c5 && c5 === c7 && c7 === "X") ||

      (c1 === c2 && c2 === c3 && c3 === "O") ||
      (c4 === c5 && c5 === c6 && c6 === "O") ||
      (c7 === c8 && c8 === c9 && c9 === "O") ||

      (c1 === c4 && c4 === c7 && c7 === "O") ||
      (c2 === c5 && c5 === c8 && c8 === "O") ||
      (c3 === c6 && c6 === c9 && c9 === "O") ||

      (c1 === c5 && c5 === c9 && c9 === "O") ||
      (c3 === c5 && c5 === c7 && c7 === "O")) {
    return true
  }

  return c1 != "" && c2 != "" && c3 != "" && c4 != "" && c5 != "" && c6 != "" && c7 != "" &&c8 != "" && c9 != ""
}

function random(min, max) { // min and max included
  return Math.floor(Math.random() * (max - min + 1) + min);
}

// cerco la prima cella vuota
// metto dentro quella cella il simbolo del giocatore corrente
function next(currentPlayer) {
  let rand = random(1, 9)
  if (c1 === "" && rand === 1) {
    c1 = currentPlayer
    return
  }
  if (c2 === "" && rand === 2) {
    c2 = currentPlayer
    return
  }
  if (c3 === "" && rand === 3) {
    c3 = currentPlayer
    return
  }
  if (c4 === "" && rand === 4) {
    c4 = currentPlayer
    return
  }
  if (c5 === "" && rand === 5) {
    c5 = currentPlayer
    return
  }
  if (c6 === "" && rand === 6) {
    c6 = currentPlayer
    return
  }
  if (c7 === "" && rand === 7) {
    c7 = currentPlayer
    return
  }
  if (c8 === "" && rand === 8) {
    c8 = currentPlayer
    return
  }
  if (c9 === "" && rand === 9) {
    c9 = currentPlayer
    return
  }

  // molto inefficiente ma il fix piu' semplice da implementare per
  // avere la soluzione al problema di "se il rand e la casella vuota non
  // combaciano"

  // console.log("SIAMO SFIGATI")

  next(currentPlayer)
}

function print(round) {
  console.log()
  console.log("ROUND " + round)
  console.log("-------------")
  console.log(printCell(c1), "|", printCell(c2), "|", printCell(c3))
  console.log(printCell(c4), "|", printCell(c5), "|", printCell(c6))
  console.log(printCell(c7), "|", printCell(c8), "|", printCell(c9))
}

// funzione che stampa " " se il contenuto della cella e' ""
// altrimenti stampa il contenuto della cella
function printCell(c) {
  if (c === "") {
    return " "
  }
  return c
}


// TODO implementare win condition dentro is gameFinished
// perche' il gioco finisce anche quando uno vince

function game() {
  let round = 1
  let currentPlayer = "X"
  while (!isGameFinished()) {
    next(currentPlayer)
    if (currentPlayer === "X") {
      currentPlayer = "O"
    } else {
      currentPlayer = "X"
    }
    // operatore ternario
    // currentPlayer = currentPlayer === "X" ? "O" : "X"
    print(round)
    round++
  }
}


game()
