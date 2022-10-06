const makeWindowArray = (array, windowLength, start = 0) => ({
  moveWindow: (shift = 0) => {
    start += shift
    return array.slice(start, start + windowLength)
  },
  setStart: newStart => start = newStart
})

let windowArray = makeWindowArray([3, 4, 1, 5, 7, 9, 0, 8], 4)
console.log(windowArray.moveWindow())
console.log(windowArray.moveWindow(1))
console.log(windowArray.moveWindow(-1))
console.log(windowArray.moveWindow(2))

windowArray.setStart(0)
console.log(windowArray.moveWindow())
