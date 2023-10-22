let input = 15
let xsToDraw = 0


for (let row = 0; row < input; row++) {
  xsToDraw++
  printXs(xsToDraw)
}

for (let row = input - 1; row > 0; row--) {
  xsToDraw--
  printXs(xsToDraw)
}

function printXs(xsToDraw) {
  let xs = ""
  for (let i = 0; i < xsToDraw; i++) {
    xs += "x"
  }
  console.log(xs)
}
