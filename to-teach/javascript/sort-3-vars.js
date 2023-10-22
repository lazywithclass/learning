function sort(a, b, c) {
  if (a > b && a > c) {
    // a > ? > ?
    if (b > c) {
      return [a,b,c]
    } else {
      return [a,c,b]
    }
  }

  if (b > a && b > c) {
    // b > ? > ?
    if (a > c) {
      return [b,a,c]
    } else {
      return [b,c,a]
    }
  }

  if (c > a && c > b) {
    // c > ? > ?
    if (a > b) {
      return [c,a,b]
    } else {
      return [c,b,a]
    }
  }
}

console.log(sort(43,6,1000))
console.log(sort(4300,-60,100))
