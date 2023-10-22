function alternatingPrints(a, b) {
  let res = ""
  while (a > 0 && b > 0) {
    res = res + a + b + " "

    // always at the end!
    a = a - 1
    b = b - 2
  }

  return res
}

console.log(alternatingPrints(4, 7))
console.log(alternatingPrints(13, 20))
console.log(alternatingPrints(1, 3))
