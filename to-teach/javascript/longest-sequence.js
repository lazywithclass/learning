// dato un array di soli numeri
// scrivere in output la lunghezza della serie crescente piu' lunga
// si dice serie crescente una sequenza di numeri ininterrotta dove ogni numero
// successivo e' piu' grande del numero precedente
//
// [1,2,3,0,1,7,9] => 4
// [] => 0
// [1,2,3,3,4,5,6,7,8] => 6


// mi preparo
// * una variabile che rappresenta il contatore della serie, partendo da 0
// * una variabile che rappresenta il precedente, partendo da undefined
//
// visitare tutti gli elementi
//   il precedente e' undefined?
//     se si lo imposto al corrente e passo all'iterazione successiva
//   il numero attuale e' maggiore del precedente?
//   se si
//     aumento di 1 il contatore della serie
//   se no
//     se counter maggiore di max
//       travasiamo counter dentro max
//     lo imposto a 0
//   imposto il precedente al corrente

// BUG:
// 3) contatore e' "off by 1" cioe' dista di 1 dal desiderata


let arr = [1,2,0,1,7,9]

let counter = 0
let max = 0
let previous

// NB: undefined == null => true

for (let i = 0; i < arr.length; i++) {
  if (previous == undefined) {
    // sono nella prima iterazione
    counter++
    previous = arr[i]
  } else {
    if (arr[i] > previous) {
      counter++
    } else {
      if (counter > max) {
        max = counter
      }
      counter = 1
    }
  }
  previous = arr[i]
}

console.log(counter)

// soluzione in cui non usiamo la variabile precedente


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
