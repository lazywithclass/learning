function makeWindowArray(array, windowLength) {
  let start = 0
  return function(shift) {
    start += shift
    return array.slice(start, start + windowLength);
  }
}

let moveWindow = makeWindowArray([ 3, 4, 1, 5, 7, 9, 0, 8 ], 4)
// console.log(moveWindow(1))
// console.log(moveWindow(1))
// console.log(moveWindow(1))
// console.log(moveWindow(1))
// console.log(moveWindow(-2))

function moveWindow2(array, start, windowLength) {
  return array.slice(start, start + windowLength);
}

let start = 0
console.log(moveWindow2([ 3, 4, 1, 5, 7, 9, 0, 8 ], start, 4))
start += 2
console.log(moveWindow2([ 3, 4, 1, 5, 7, 9, 0, 8 ], start, 4))
