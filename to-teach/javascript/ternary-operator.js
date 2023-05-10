if (condizione) {
    // qualcosa
} else {
    // altro
}

// non si puo' fare, errore di sintassi
// let risultato = if (condizione) { 10 } else { 20 }

// ritorna 10 se la condizione e' verificata, 20 altrimenti
// quindi alla fine della valutazione dentro risultato ci sara' un numero
let risultato = condizione ? 10 : 20

// ...che tradotto con if then else e'
let risultato2
if (condizione) {
    risultato2 = 10
} else {
    risultato2  = 20
}
// oppure leggermente meglio
let risultato3 = 20
if (condizione) {
    risultato3 = 10
}
