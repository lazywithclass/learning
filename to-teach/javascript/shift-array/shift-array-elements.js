// write a function that accepts an array as argument
// it should shift all elements of the array by two to the right
// all elements "exiting" from the array should re-enter
// on the left

function shiftToTheRight(array) {
  let first = array[array.length - 2]
  let second = array[array.length - 1]

  for (let i = array.length - 3; i >= 0; i--) {
    array[i + 2] = array[i]
  }

  array[0] = first
  array[1] = second
}

let arr = [ 1, 7, 9, 4, 5, 0 ]
shiftToTheRight(arr)
console.log(arr)

shiftToTheRight(arr)
console.log(arr)

shiftToTheRight(arr, 4)
shiftToTheRight(arr, 1)
shift(arr, -5)
shift(arr, 3)
