function somma(a, b) {
  return a + b
}

// console.log(somma(1, 41))

function stampaTuttiTra(a, b) {
  for (let i = a; i < b; i++) {
    console.log(i)
  }
}

// stampaTuttiTra(1, -100)



// voglio stampare un triangolo di altezza definita dal chiamante
// cioe' da chi chiama la funzione
// scompongo il problema come al solito

// un altro modo per arrivare alla soluzione, cioe' ad un codice 
// scomposto in funzioni "elementari" e' chiedersi:
// "quanto sarebbe bello avere una bacchetta magica che mi risolva il 
// problema elementare"
// o altrimenti detto
// "quanto sarebbe bello avere una funzione che mi stampa una riga di X 
// asterischi"

// quello che fate e' ASSUMERE di avere quella funzione GIA' SCRITTA

function stampaRiga(x) {
  let riga = ""
  for (let i = 0; i < x; i++) {
   riga = riga + "#"
  }
  console.log(riga)
}

function stampaTriangolo(altezza) {
  for (let i = 1; i <= altezza; i++) {
    stampaRiga(i)
  }
}

// stampaTriangolo(5)

function stampaPiramide(altezza) {
  for (let i = 1; i <= altezza; i++) {
    stampaRiga(i)
  }
  for (let i = altezza - 1; i > 0; i--) {
    stampaRiga(i)
  }
}
// stampaPiramide(10)


// Extra, ciclare senza cicli

// O_O
function stampaWow(n, m) {
  if (n < m) {
    stampaRiga(n)
    stampaWow(n+1, m)
    stampaRiga(n)
  }
}
stampaWow(1, 10)

// !!!
function stampaRic(n) {
  if (n > 0) {
    stampaRiga(n)
    stampaRic(n-1)
    stampaRiga(n)
  }
}
stampaRic(10)
