//inizializzo un contatore a 0, colonna
//inizializzo un contatore a 0 per sapere a che riga sono, riga
//cicliamo da 0 ad a/b
  //se riga è pari
    //stampo da sx a dx
  //se riga è dispari
    //stampo da dx a sx

// a 25, b 5
// 1  2   3  4  5
// 10 9   8  7  6
// 11 12 13 14 15
// 20 19 18 17 16
// 21 22 23 24 25

function serpente(a, b) {
  let prossimaPartenza = 1

  // assumiamo che a sia divisibile per b
  for (let riga = 0; riga < a/b; riga++) {
    // devo stampare da sx a dx, o da dx a sx?
    if (riga % 2 === 0) {
      stampaDaSx(prossimaPartenza, b)
    } else {
      stampaDaDx(prossimaPartenza, b)
    }
    prossimaPartenza = prossimaPartenza + b
  }
}

function stampaDaSx(partenza, quante) {
  let arrivo = partenza + quante
  let riga = ""
  for (let i = partenza; i < arrivo; i++) {
    riga = riga + "\t" +  i
  }
  console.log(riga)
}

function stampaDaDx(partenza, quante) {
  let arrivo = partenza + quante - 1
  let riga = ""
  for (let i = arrivo; i >= partenza; i--) {
    riga = riga + "\t" +  i
  }
  console.log(riga)
}


// serpente(25, 5)

function serpenteArray(a, b) {
  let matrix = []
  let prossimo = 1
  for (let i = 0; i < a/b; i++) {
    let riga = []
    for (let j = prossimo; j < prossimo + b; j++) {
      riga.push(j)
    }
    prossimo = prossimo + b
    matrix.push(riga)
  }

  for (let i = 0; i < matrix.length; i++) {
    if (i % 2 === 1) {
      matrix[i].reverse()
    }
  }

  console.log(matrix)
}

// serpenteArray(25, 5)


// approccio funzionale

function* createLines(total, length, lineNumber) {
  if (lineNumber * length === total) {
    return
  }

  const line = Array.from({length}, (_, i) => i + 1 + (lineNumber * length))
  yield lineNumber % 2 === 0 ? line : line.reverse()
  yield* createLines(total, length, lineNumber + 1)
}

function buildSnake(total, length) {
  const lines = createLines(total, length, 0)
  for (let i = 0; i < total/length; i++) {
    console.log(lines.next().value)
  }
}

buildSnake(25, 5)
