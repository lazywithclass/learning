function sum(a, b) {
  return a + b
}

// solo numeri in input
// solo due input
// massimo numero di cifre
// no NaN e no Infinity

function test(actual, expected) {
  if (actual === expected) {
    console.log('.')
  } else {
    console.log(`Expected ${expected} found ${actual}`)
  }
}

test(sum(1, 2), 3)
test(sum(4, -3), 1)

// NO!
// test(sum(2, 0), 2)
