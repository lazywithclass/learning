// dato un numero scelto a caso
// stampare un quadrato di numeri
// come in questo esempio
// per n 5
// 1 2 3 4 5
// 5 4 3 2 1
// 1 2 3 4 5
// 5 4 3 2 1
// 1 2 3 4 5

let n = 6
let partoDa1 = true
for (let i = 0; i < n; i++) {
    let riga = ""

    if (partoDa1) {
        for (let i = 1; i <= n; i++) {
            riga = riga + i + " "
        }
    } else {
        for (let i = n; i >= 1; i--) {
            riga = riga + i + " "
        }
    }
    console.log(riga)

    // invertire il booleano
    partoDa1 = !partoDa1
}