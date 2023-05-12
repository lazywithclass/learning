let obj = {
  key1: 'value1',
  key2: 'value2'
}

function first(o) {
  o.key1 = 'MODIFIED'

  return {
    ...o,
    key1: '1'
  }
}

function second(o) {
  o = { k: 1 }
}

console.log("Before", obj)
first(obj)
console.log("After first", obj)

// doesnt change!
second("After second", obj)
console.log(obj)
