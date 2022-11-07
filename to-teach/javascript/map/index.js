let result = [
  1,2,3,4
].map(n => n * 2)
console.log(result)

function map(array, funx) {
  let res = []
  for (let i = 0; i < array.length; i++) {
    let n = array[i]
    res[i] = funx(n)
  }
  return res
}

console.log(map([1,2,3,4], n => n * 2))
