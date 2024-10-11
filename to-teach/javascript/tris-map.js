// limitazione
// dobbiamo utilizzare una mappa per rappresentare la griglia


let grid = {
  c1: "",
  c2: "",
  c3: "",
  c4: "",
  c5: "",
  c6: "",
  c7: "",
  c8: "",
  c9: ""
}

function isGameFinished() {

  if ((grid.c1 === grid.c2 && grid.c2 === grid.c3 && grid.c3 === "X") ||
      (grid.c4 === grid.c5 && grid.c5 === grid.c6 && grid.c6 === "X") ||
      (grid.c7 === grid.c8 && grid.c8 === grid.c9 && grid.c9 === "X") ||

      (grid.c1 === grid.c4 && grid.c4 === grid.c7 && grid.c7 === "X") ||
      (grid.c2 === grid.c5 && grid.c5 === grid.c8 && grid.c8 === "X") ||
      (grid.c3 === grid.c6 && grid.c6 === grid.c9 && grid.c9 === "X") ||

      (grid.c1 === grid.c5 && grid.c5 === grid.c9 && grid.c9 === "X") ||
      (grid.c3 === grid.c5 && grid.c5 === grid.c7 && grid.c7 === "X") ||

      (grid.c1 === grid.c2 && grid.c2 === grid.c3 && grid.c3 === "O") ||
      (grid.c4 === grid.c5 && grid.c5 === grid.c6 && grid.c6 === "O") ||
      (grid.c7 === grid.c8 && grid.c8 === grid.c9 && grid.c9 === "O") ||

      (grid.c1 === grid.c4 && grid.c4 === grid.c7 && grid.c7 === "O") ||
      (grid.c2 === grid.c5 && grid.c5 === grid.c8 && grid.c8 === "O") ||
      (grid.c3 === grid.c6 && grid.c6 === grid.c9 && grid.c9 === "O") ||

      (grid.c1 === grid.c5 && grid.c5 === grid.c9 && grid.c9 === "O") ||
      (grid.c3 === grid.c5 && grid.c5 === grid.c7 && grid.c7 === "O")) {
    return true
  }

  // itero su tutta la griglia e mi chiedo se esiste almeno una position
  // che ha come valore "", se si vuol dire che la griglia non e' tutta
  // piena, quindi il gioco non e' finito
  let positions = Object.keys(grid)
  for (let i = 0; i < positions.length; i++) {
    let position = positions[i]
    if (grid[position] === "") {
      return false
    }
  }
  return true
}

function random(min, max) { // min and max included
  return Math.floor(Math.random() * (max - min + 1) + min);
}

function next(currentPlayer) {
  let rand = random(0, 8)
  let positions = Object.keys(grid)
  for (let i = 0; i < positions.length; i++) {
    let position = positions[i]
    if (grid[position] === "" && rand === i) {
      grid[position] = currentPlayer
      break
    }

    // soluzione estremamente inefficiente
    if (i === positions.length - 1) {
      rand = random(0, 8)
      i = -1
    }
  }

  // a causa del break, arriva qua
}

function print(round) {
  console.log()
  console.log("Round " + round)
  console.log("-----------")
  console.log(grid.c1 || " ", " | ", grid.c2 || " ", " | ", grid.c3 || " ")
  console.log(grid.c4 || " ", " | ", grid.c5 || " ", " | ", grid.c6 || " ")
  console.log(grid.c7 || " ", " | ", grid.c8 || " ", " | ", grid.c9 || " ")
}

function game() {
  let currentPlayer = "X"
  let round = 1
  while (!isGameFinished()) {
    next(currentPlayer)
    print(round)
    currentPlayer = currentPlayer === "X" ? "O" : "X"
    round = round + 1
  }
}

game()
