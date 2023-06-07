function change(a) {
  ++a
}

let n = 1
change(n)
console.log(n)


function increment(o) {
  o.id++
}
let a = { id: 1 }
increment(a)
console.log(a)
