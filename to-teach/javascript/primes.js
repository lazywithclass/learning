// dobbiamo calcolare tutti i numeri primi tra 1 e n


// cosa e' un numero primo?
// un numero primo e' un numero che e' divisibile solo per se stesso
// e per l'unita' (1)



// let n = 100
// for (let m = 1; m <= n; m++) {
//   // codice che ci consente di controllare la divisibilita' di un numero m
//   // per tutti i numeri che vanno da 1 a n

//   // found rappresenta il concetto di "ho trovato un divisore"
//   let found = false
//   for (let i = 2; i < m; i++) {
//     // il numero m diviso i da resto?
//     // detto in un altro modo: m e' divisibile per i?
//     if (m % i == 0) {
//       found = true
//     }
//   }

//   // ho trovato un divisore per questo m?
//   // se si allora m non e' primo
//   // se no allora m e' primo

//   if (found) {
//     console.log(m + " non e' primo")
//   } else {
//     console.log(m + " e' primo")
//   }
// }



// devo trovare i primi 100 numeri primi
// quindi l'idea e' quella di avere un contatore che tiene traccia di
// quanti ne ho trovati "fino ad adesso"

// dichiarazione della funzione
function seHoTrovatoUnDivisore(m) {
  // found rappresenta il concetto di "ho trovato un divisore"
  let found = false
  for (let i = 2; i < m; i++) {
    // il numero m diviso i da resto?
    // detto in un altro modo: m e' divisibile per i?
    if (m % i == 0) {
      found = true
    }
  }
  return found
}


// potrei addirittura scrivere una funzione che dato un numero x in ingresso
// stampa tutti i primi minori di quell'x

function stampaTotPrimi(x) {
  let count = 0
  let m = 1
  while (count <= x) {
    if (!seHoTrovatoUnDivisore(m)) {
      console.log(m + " e' il " + count + " numero primo!")
      count = count + 1
    }
    m = m + 1
  }
}

// stampaTotPrimi(140)
	 
function accumulaTotPrimi(n) {
  let count = 0
  let m = 1
  let primi = []
  while (count <= n) {
    if (!seHoTrovatoUnDivisore(m)) {
      primi.push(m)
      count = count + 1
    }
    m = m + 1
  }
  return primi
}

let primi = accumulaTotPrimi(200)
console.log(primi)
