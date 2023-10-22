let arr = [ 1, 3, 4, 0, 5, 7, 8 ]
let n = 5

// for (let i = 0; i < arr.length - 1; i++) {
//   let current = arr[i]
//   let next = arr[i + 1]
//   if (current + next === n) {
//     console.log(current, next)
//     break
//   }
// }


for (let i = 0; i < arr.length - 1; i++) {
  for (let j = i + 1; j < arr.length; j++) {
    if (arr[i] + arr[j] === n) {
      console.log(arr[i], arr[j])
    }
  }
}
