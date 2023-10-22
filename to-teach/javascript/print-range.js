let a = 10
let b = 0

// let n = a
// let m = b
// if (a > b) {
//   n = b
//   m = a
// }
// for (; n < m; n++) {
//   console.log(n)
// }

// with tmp
if (a > b) {
  let tmp = a
  a = b
  b = tmp
}
for (; a < b; a++) {
  console.log(a)
}
