// A -> Z
// pattern
// candidates
//
// filtrare i candidati in base al pattern


// sfruttare le espressioni regolari

// # -> .
function hashToDot(s) {
  let res = ''
  for (let i = 0; i < s.length; i++) {
    // if (s[i] === '#') {
    //   res += '.'
    // } else {
    //   res += s[i]
    // }
    res += s[i] === '#' ? '.' : s[i]
  }
  return res
}

function hashToDotRealWorld(s) {
  return s.replaceAll('#', '.')
}

function filter(strings, pattern) {
  let res = []
  for (let i = 0; i < strings.length; i++) {
    let string = strings[i]
    if (string.match(pattern)) {
      res.push(string)
    }
  }
  return res
}

function filterRealWorld(strings, pattern) {
  return strings.filter(string => string.match(pattern))
}


console.log(filter(['PAZZO', 'PEZZO', 'CANE'], hashToDot('P#ZZO')))
console.log(filterRealWorld(['PAZZO', 'PEZZO', 'CANE'], hashToDot('P#ZZO')))
