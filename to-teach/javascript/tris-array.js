// limitazione
// dobbiamo utilizzare un array per rappresentare la griglia


let griglia = ["", "", "", "", "", "", "", "", ""]

function random(min, max) { // min and max included
  return Math.floor(Math.random() * (max - min + 1) + min);
}

function next(currentPlayer) {
  // questo era il codice che sequenzialmente prende la prima
  // posizione libera, ma non ci serve piu' perche' useremo un
  // metodo che randomicamente sceglie la posizione
  // for (let i = 0; i < griglia.length; i++) {
  //   if (griglia[i] === "") {
  //     griglia[i] = currentPlayer
  //     break
  //   }
  // }

  let posLibere = []
  for (let i = 0; i < griglia.length; i++) {
    if (griglia[i] === "") {
      posLibere.push(i)
    }
  }

  let rand = random(0, posLibere.length - 1)
  let posLibera = posLibere[rand]
  griglia[posLibera] = currentPlayer
}

function print(round) {
  let row = ""
  console.log()
  console.log(`Round ${round}`)
  console.log("------------------")
  for (let i = 0; i < griglia.length; i++) {
    row = row + (griglia[i] || " ")
    if ((i + 1) % 3 === 0) {
      console.log(row)
      row = ""
    } else {
      // aggiungere il separatore solo se sappiamo che arrivera'
      // un nuovo elemento dopo di questo
      row = row + " | "
    }
  }
}

function trisOnRow(index) {
  return griglia[index] !== "" && griglia[index] === griglia[index+1] &&
     griglia[index+1] === griglia[index+2]
}

function trisOnColumn(index) {
  return griglia[index] !== "" && griglia[index] === griglia[index+3] &&
    griglia[index+3] === griglia[index+6]
}

function isGameFinished() {
  if (trisOnRow(0) || trisOnRow(3) || trisOnRow(6) ||
      trisOnColumn(0) || trisOnColumn(1) || trisOnColumn(2) ||
      (griglia[0] !== "" && griglia[0] === griglia[4] &&
       griglia[4] === griglia[8]) ||
      (griglia[2] !== "" && griglia[2]=== griglia[4] &&
       griglia[4] === griglia[6])) {
    return true
  }

  for (let i = 0; i < griglia.length; i++) {
    if (griglia[i] === "") {
      return false
    }
  }
  return true
}

function game() {
  let currentPlayer = "X"
  let round = 1
  while (!isGameFinished()) {
    next(currentPlayer)
    // chiama la funzione print, passale round, e poi incrementa round
    print(round++)
    // incrementa round, e poi passa round all'invocazione della funzione print
    // print(++round)
    currentPlayer = currentPlayer === "X" ? "O" : "X"
  }
}

game()
