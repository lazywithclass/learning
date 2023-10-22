// // fino ad adesso
// function qualcosa(text) {
//   for (let i = 0; i < 100; i++) {
//     console.log(i)
//   }
//   console.log(text)
// }

// // l'esecuzione va dall'alto al basso seguendo il fluire del codice
// qualcosa('FATTO!')


// // async
// // higher order
// setTimeout(() => {
//   console.log('5 sec sono passati')
// }, 5000)

// console.log('non aspetta...')

// // funzione che aspetta 3 secondi e poi stampa tutti i numeri tra 0 e 100
// setTimeout(() => {
//   qualcosa('FINITO!')
// }, 3000)

// console.log('...proprio per niente')


// voglio scrivere una funzione che accetta in ingresso
// due stringhe, e due numeri n e m che rappresentano i millisecondi
// da attendere
//
// voglio ottenere che
// * la prima stringa viene stampata dopo che sono passati n millisecondi
// * la seconda stringa viene stampata dopo che sono passati i primi n
// millisecondi e anche m millisecondi
function f(s1, s2, n, m) {
  setTimeout(function() {
    console.log(s1, new Date().getTime()) // ms since Unix epoch + n
    setTimeout(function () {
      console.log(s2, new Date().getTime()) // ms since Unix epoch + n + m
    }, m)
  }, n)
}

// f('uno', 'due', 2000, 4000)

function f2(s1, s2, n, m) {
  let start
  setTimeout(function() {
    start = new Date().getTime()
    console.log(s1, start) // ms since Unix epoch + n
  }, n)

  setTimeout(function() {
    console.log(s2, new Date().getTime() - start) // ms since Unix epoch + n + m
  }, n + m)
}

// f2('uno', 'due', 2000, 4000)

// usiamo le Promise

let string = "string"

function sleep(ms) {

  function resolver(resolve, reject) {
    setTimeout(() => {
      resolve('FINITO!')
    }, ms)
  }

  return new Promise(resolver)
}

// sleep(3000).then(string => {
//   console.log(string)
// })


async function funzAsyncMostruosa() {
  // sleep(3000).then(result => {
  //   sleep(3000).then(result2 => {
  //     sleep(3000).then(result3 => {
  //       console.log("HO FINITO!!!!")
  //     })
  //   })
  // })

  // non attende perche' la funzione setTimeout non lavora a livello Promise
  await setTimeout(() => 42, 5000)


  let result = await sleep(3000)
  let result2 = await sleep(3000)
  let result3 = await sleep(3000)
}

// fetch esiste solo nel browser
async function fetchData() {
  let res = await fetch('https://fakestoreapi.com/products')
  let json = await res.json()
  console.log(json)
}

fetchData()
