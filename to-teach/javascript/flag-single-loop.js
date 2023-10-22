let input = 10
let rows = (2 * input) -1
let turningPoint = input

let xsToDraw = 1
let crossedHalfway = false

while (rows > 0) {
  let xs = "-"
  for (let i = 0; i < xsToDraw; i++) {
    xs += "x"
  }
  console.log(xs)

  if (xsToDraw < turningPoint && !crossedHalfway) {
    xsToDraw++
  } else {
    crossedHalfway = true
    xsToDraw--
  }
  rows--
}
