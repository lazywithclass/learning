let numbers = [2,3,4,7,6]
let sum = numbers.reduce((tot, n) => tot + n)
// console.log(sum)

let reduce = (arr, reducer, acc) => {
  for (let i = 0; i < arr.length; i++) {
    acc = reducer(acc, arr[i])
  }
  return acc
}
// console.log(reduce(numbers, (tot, n) => tot + n, 0))

// greater than 5
let gt = numbers.reduce((acc, n) => {
  if (n > 5) {
    acc.push(n)
  }
  return acc
}, [])
console.log(gt)

// all elements minus 1
let min1 = numbers.reduce((acc, n) => {
  acc.push(n - 1)
  return acc
}, [])
console.log(min1)
