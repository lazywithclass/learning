// scrivere una funzione che dato un array, sia in grado di 
// invertirlo

// [1,2,3] -> [3,2,1]
// [] -> []
// [1] -> [1]

function reverse(arr) {
  let reversed = []
  for (let i = arr.length - 1; i >= 0; i--) {
    console.log(reversed)
    reversed.push(arr[i])   
  }
  return reversed
}

// console.log(reverse([1,2,3,4,5,6]))


function reverse2(arr) {
  for (let sx = 0, dx = arr.length - 1; sx < dx; sx++, dx--) {
    let temp = arr[dx]
    arr[dx] = arr[sx]
    arr[sx] = temp
  }
  return arr
}

console.log(reverse2([1,2,3,4,5,6]))
console.log(reverse2([1,2,3,4,5]))
