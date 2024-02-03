let triangle = [
  [1],      // 0
  [1,1],    // 1
  [1,2,1],  // 2
      // ?
]

// algoritmo per la generazione della riga 3
// creo un nuovo array
// la prima cifra deve essere 1
// la seconda cifra e' la somma tra la prima e la seconda cifra
// della riga precedente
// la terza cifra e' ... (uguale)
// l'ultima cifra deve essere 1

triangle = []
let rows = 5

for (let row = 0; row < rows; row++) {
  // quanto e' lunga la riga corrente?
  // tanto quanto vale row
	
  // come identifico la prima e l'ultima riga?
  // la prima e' 0
  // l'ultima e' rows
	
  // come accedo alla riga precedente?
  // row - 1
  // nel momento in cui row e' 1 devo pushare l'array che contiene 1
  // nel momento in cui row e' 2 devo pushare l'array che contiene 1,1

  if (row == 0) {
    triangle.push([1])
  } else if (row == 1) {
    triangle.push([1,1])
  } else {
    let numbers = []
    for (let pos = 0; pos <= row; pos++) {
      if (pos == 0 || pos == row) {
        numbers.push(1)
      } else {
	// nella riga attuale, alla posizione attuale devo mettere
	// la somma dei valori alla riga precedente
	// 1) prendo la riga precedente: row - 1
        // 2) prendo il valore precedente a quello attuale nella riga precedente
	// 3) prendo il valore sopra a quello attuale nella riga precedente
        let prev = triangle[row - 1]
	let sum = 0
	let prevLeft = prev[pos - 1]
	let prevUp = prev[pos]
	sum = prevLeft + prevUp
	numbers.push(sum)
      }
    }
    triangle.push(numbers)
  }
}

let spacesNum = rows - 1
for (let i = 0; i < triangle.length; i++) {
  let row = triangle[i]

  let spaces = ""
  for (let j = 0; j < spacesNum; j++) {
    spaces += " "
  }

  let line = ""
  for (let j = 0; j < row.length; j++) {
    line += row[j] + " "
  }

  console.log(spaces + line)
  spacesNum--
}

