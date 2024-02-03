const fs = require('fs')

let paperi = require('./node-write-file.json')
const paperino = { name: 'Paperino' }
paperi.push(paperino)
fs.writeFile('./node-write-file.json', JSON.stringify(paperi), () => {
  console.log('fatto')
})

//fs.writeFileSync('path', data)


function fib(n) {
  if (n == 2) {
    return 2
  }
  if (n == 1) {
    return 1
  }

  return fib(n - 1) + fib(n -2)
}
