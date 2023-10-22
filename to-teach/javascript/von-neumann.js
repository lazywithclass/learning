function denotato(n) {
    if (n === 0) {
        return '{}'
    } else {
        let numeri = []
        for (let i = 0; i < n; i++) {
            numeri.push(denotato(i)) // applico la funzione in modo ricorsivo
        }
        return '{' + numeri.join(', ') + '}'
    }
}

// Esempi di utilizzo:
console.log(denotaNumero(0))
console.log(denotaNumero(1))
console.log(denotaNumero(2))
console.log(denotaNumero(3))
console.log(denotaNumero(4))

console.log(denotato(0))
console.log(denotato(1))
console.log(denotato(2))
console.log(denotato(3))
