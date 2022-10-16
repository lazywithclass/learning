let names = [
  "Andrew", "Jose", "Josephine", "Elizabeth"
]

let mapped = names.map((name, index) => ({name, index}))

let mappedO = names.map(function(name, index) {
  return {
    name: name,
    index: index
  }
})

// console.log(mapped)
// console.log(mappedO)

let products = [
  { name: "shoes", price: 10 },
  { name: "tshirt", price: 15 },
  { name: "trousers", price: 25 },
  { name: "belt", price: 5 },
]

let mostExpensive = products.reduce((mostExp, item) => {
  return item.price > mostExp.price ? item : mostExp
}, { price: 0 })
// console.log(mostExpensive)

let total = products.reduce((tot, item) => item.price + tot, 0)
// console.log(total)


let products2 = [
  { name: "shoes", price: 10, onsale: true },
  { name: "tshirt", price: 15, onsale: false },
  { name: "trousers", price: 25, onsale: false },
  { name: "belt", price: 5, onsale: true },
]

let onsale = products2.filter(item => !item.onsale)
// console.log(onsale)

let reduce = function(arr, reducer, acc) {
  for (let i = 0; i < arr.length; i++) {
    acc = reducer(acc, arr[i])
  }
  return acc
}

let mostExp = reduce(products,
                     (acc, item) => item.price > acc.price ? item : acc,
                     { price: 0 })
console.log(mostExp)
