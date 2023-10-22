// function sum(a, b) {
//   return a + b
// }

// // let sum = function(a,b) {
// //   return a + b
// // }

// // scrivere una funzione che raddoppia tutti gli elementi di un array

// function double(arr) {
//   let res = []
//   for (let i = 0; i < arr.length; i++) {
//     res.push(arr[i] * 2) // differenza
//   }
//   return res
// }

// function plus1(arr) {
//   let res = []
//   for (let i = 0; i < arr.length; i++) {
//     res.push(arr[i] + 1) // differenza
//   }
//   return res
// }

// function plus42(arr) {
//   let res = []
//   for (let i = 0; i < arr.length; i++) {
//     res.push(arr[i] + 42) // differenza
//   }
//   return res
// }

// // refactor

// function sum1(n) {
//   return n + 1
// }

// function mul3(n) {
//   return n * 3
// }

// function map(arr, funx) {
//   let res = []
//   for (let i = 0; i < arr.length; i++) {
//     res.push(funx(arr[i])) // nessuna differenza
//   }
//   return res
// }

// console.log(map([1,2,3], sum1))
// console.log(map([1,2,3], mul3))

// console.log(map([1,2,3], function(n) { return n * n }))
// console.log(map([1,2,3,4,5,6], n => n % 2 == 0))

// // funzione anonima

// let isEven = function(n) {
//   if (n % 2 == 0) {
//     return true
//   } else {
//     return false
//   }
// }

// // semplificato

// let isEvenSimplified = function(n) {
//   return n % 2 == 0
// }

// let isEvenArrow = n => n % 2 == 0

// console.log(map([1,2,3], n => n + 5))

// // definizione variabili
// // definizione funzione
// // invocazione funzione


// console.log([1,2,3].map(n => n - 1))


// function even(arr) {
//   let res = []
//   for (let i = 0; i < arr.length; i++) {
//     if (arr[i] % 2 == 0) {
//       res.push(arr[i])
//     }
//   }
//   return res
// }

// function positive(arr) {
//   let res = []
//   for (let i = 0; i < arr.length; i++) {
//     if (arr[i] > 0) {
//       res.push(arr[i])
//     }
//   }
//   return res
// }

// function filter(arr, predicate) {
//   let res = []
//   for (let i = 0; i < arr.length; i++) {
//     if (predicate(arr[i])) {
//       res.push(arr[i])
//     }
//   }
//   return res
// }

// console.log(filter([1,2,3,4, -4,-4,-1, 0], n => n > 0))
// console.log(filter([1,2,3,4, -4,-4,-1, 0], n => n == -4))

// console.log(filter([
//   "Alberto", "Giovanni", "Filippo", "Francesco"
// ], s => s.startsWith("F")))

// console.log([
//   "Alberto", "Giovanni", "Filippo", "Francesco"
// ].filter(s => s.endsWith("o")))



// // fold
// function reduce(arr, init, funx) {
//   let res = init
//   for (let i = 0; i < arr.length; i++) {
//     res = funx(res, arr[i])
//   }
//   return res
// }

// let arr = [1,2,3]
// let init = 0
// let funx = function(res, n) {
//   return res + n
// }

// console.log(reduce(arr, init, funx))

// console.log(reduce([1,2,3], [], (acc, n) => {
//   acc.push(n * 2)
//   return acc
// }))

// console.log(reduce([-3,-2,-1,0,1,2,3], [], (acc, n) => {
//   if (n < 0) {
//     acc.push(n)
//   }
//   return acc
// }))


// funzione ricorsiva
function loop(n) {
  if (n == 0) {
    return
  }

  console.log(n)
  n--

  loop(n)
}

loop(10)


function sum(arr, i) {
  if (i == arr.length) {
    return 0
  }

  return arr[i] + sum(arr, i + 1)
}

console.log(sum([1,2,3,4,5], 0))


function sumNO(arr) {
  let tot = 0
  for (let i = 0; i < arr.length; i++) {
    tot += arr[i]
  }
  return to
}

function isPalindrome(arr, sx, dx) {
  if (sx >= dx) {
    return true
  }

  return arr[sx] === arr[dx] && isPalindrome(arr, sx + 1, dx - 1)
}

console.log(isPalindrome([], 0, 0))
console.log(isPalindrome([1], 0, 0))
console.log(isPalindrome([1,2,3,2,1], 0, 4))
console.log(isPalindrome([1,2,2,1], 0, 3))
