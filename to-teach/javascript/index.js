function encode(array) {
  let result = ''
  array.sort((a, b) => a - b)
  let first = array[0]
  let last = array[0]
  for (let i = 1; i < array.length; i++) {
    if (last + 1 == array[i]) {
      last = array[i]
    } else if (first < last) {
      result += `${first}->${last}-`
      first = array[i]
      last = array[i]
    } else {
      result += `${first}-`
      first = array[i]
      last = array[i]
    }
  }

  return result + (first == last ? first : `${first}->${last}`)
}

// console.log(encode([2,8,7,6,10,3,5,1,4,12,14,16,15,20]))
// console.log(encode([4,8,6]))

function tokenise(string) {
  let ranges = string
      .match(/\d+->\d+/g)
      .map(r => r.split('->'))
      .map(ns => ns.map(n => parseInt(n, 10)))
  let singles = string
      .match(/\d+-\d+/g)
      .map(s => s.split('-'))
      .map(ns => ns.map(n => parseInt(n, 10)))
  return [ranges, singles]
}

function decode(string) {
  let [ranges, singles] = tokenise(string)

  let result = ranges.reduce((result, range) => {
    for (let i = range[0]; i <= range[1]; i++) {
      result.add(i)
    }
    return result
  }, new Set())

  result = singles.reduce((result, single) => {
    for (let i = 0; i < single.length; i++) {
      result.add(single[i])
    }
    return result
  }, result)

  return Array.from(result).sort((a, b) => a - b)
}

console.log(decode('1->8-10-12-14->16-20'))
