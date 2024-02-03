let arr = [1,2,3,0,5,2,6,7,8,9,10]
counter = 0
max = 0

for (let i = 0; i < arr.length; i++) {
  if (i == 0) {
    // sono nella prima iterazione
    counter++
  } else {
    // sono nelle successive iterazioni, TRANNE che nella prima
    if (arr[i] > arr[i - 1]) {
      counter++
    } else {
      if (counter > max) {
        max = counter
      }
      counter = 1
    }
  }
}

console.log(counter)

// ----------------------------------

// dichiarazione
function longestSequence(arr) {
  let counter = 0
  let max = 0
  for (let i = 0; i < arr.length; i++) {
    if (i == 0) {
      // sono nella prima iterazione
      counter++
    } else {
      // sono nelle successive iterazioni, TRANNE che nella prima
      if (arr[i] > arr[i - 1]) {
        counter++
      } else {
        if (counter > max) {
          max = counter
        }
        counter = 1
      }
    }
  }

  return counter
}

let longest = longestSequence([1,2,3,0,5,2,6,7,8,9,10])
console.log(longest)



let x = 42
function qualcosa(x) {
  console.log(x)
}

qualcosa(30) // stampa 30

// dichiarazione funzione + invocazione funzione + variabile



// ricevero' due numeri in ingresso, a e b
// creo un array vuoto
// ciclo partendo dal numero successivo ad a fermandomi al numero prima di b
//   per ogni iterazione inserisco l'indice corrente dentro l'array
// ritorno l'array

function between(a, b) {
  let arr = []
  for (let i = a + 1; i < b; i++) {
    arr.push(i)
  }
  return arr
  // qua non si puo' scrivere nulla
}

let nums = between(2, 10)

// prendere tutti i numeri tra 1 e 50
// di questi prendere solo quelli pari
// stamparne gli elementi sommando 1 al numero, quindi se si avesse [1,2,3] stamperesti
// 2
// 3
// 4

function filterEven(arr) {
  let newArr = []
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] % 2 == 0) {
      newArr.push(arr[i])
    }
  }
  return newArr
}

function printAdd1(arr) {
  for (let i = 0; i < arr.length; i++) {
    console.log(arr[i] + 1)
  }
}


let numbers = between(1, 50) // da 2 a 49 dentro l'array
let even = filterEven(numbers) // dentro even c'e' ad esempio [2,4,6,8,...]
printAdd1(even)

// potremmo anche scrivere
let even = filterEven(between(1, 50))
printAdd1(even)

// che e' la stessa cosa di fare...
printAdd1(filterEven(between(1, 50)))

// sbagliando:

// facciamo che riscrivo between per fare esattamente cio' che mi serve
// quindi non ho piu' between originale
function betweenAndFilterEven(a, b) {
  let arr = []
  for (let i = a + 1; i < b; i++) {
    if (i % 2 == 0) {
      arr.push(i)
    }
  }
  return arr
}

let even = betweenAndFilterEven(1, 100)
printAdd1(even)

// e se ora mi servisse between per ottenere i numeri tra 100 e 1000?
// devo riscrivere between, ma allora avrei una duplicazione

// questa e' la composizione di funzioni


let res = betweenAndFilterEven(100, 1000)

// e se volessi una funzione che filtra i dispari?


function filterOdd(arr) {
  let newArr = []
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] % 2 != 0) {
      newArr.push(arr[i])
    }
  }
  return newArr
}

function filterEvenOdd(arr, even) {
  let newArr = []
  for (let i = 0; i < arr.length; i++) {
    if (even) {
      if (arr[i] % 2 == 0) {
        newArr.push(arr[i])
      }
    } else {
      if (arr[i] % 2 != 0) {
        newArr.push(arr[i])
      }
    }
  }
  return newArr
}

filterEvenOdd([1,2,3,4,5,6], true)
filterEvenOdd([1,2,3,4,5,6], false)


// scrivere una funzione che date due stringhe in ingresso ritorni la piu' lunga

// la funzione prende in ingresso due variabili
// stringa 1 e' piu' lunga di stringa 2 ?
//   se si ritorno stringa 1
// altrimenti
//   ritorno stringa 2

function longerString(s1, s2) {
  if (s1.length > s2.length) {
    return s1
  }
  return s2
}

function longerString(s1, s2) {
  if (s1.length > s2.length) {
    return s1
  } else {
    return s2
  }
}

function longerString(s1, s2) {
  return s1.length > s2.length ? s1 : s2
}


// scrivere una funzione chiamata countNoSpaces che
// data una stringa come parametro
// ritorni un intero n che rappresenti i caratteri presenti nella
// string passata, senza contare gli spazi


// stessa traccia scritta piu' amichevole
// scrivere una funzione che conta tutti i caratteri in una stringa tranne gli spazi

// pseudocodice
// tenere traccia di quanti caratteri ho visto, partendo da 0
// cicliamo sulla stringa passata
//   il carattere attuale non e' uno spazio?
//     se si aumentiamo il contatore di 1
// ritorniamo il numero di caratteri che ho visto

function countNoSpaces(string) {
  let count = 0
  for (let i = 0; i < string.length; i++) {
    if (string[i] != " ") {
      count++
    }
  }
  return count
}

console.log(countNoSpaces("questa e' una frase"))


// scrivere una funzione che dice se un numero e' pari o meno
function isEven(n) {
  return n % 2 == 0
}

// quindi immaginiamo di scrivere filterEven per la prima volta
// possiamo utilizzare isEven per rendere il codice piu' leggibile

// mi aspetto che arr sia un array di numeri
function filterEven(arr) {
  let newArr = []
  for (let i = 0; i < arr.length; i++) {
    if (isEven(arr[i])) {
      newArr.push(arr[i])
    }
  }
  return newArr
}
