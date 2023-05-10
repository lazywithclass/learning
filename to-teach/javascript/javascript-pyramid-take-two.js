// write a function such that given JavaScript
// prints the following
//          s
//         s c
//       a s c r
//     v a s c r i
//   a v a s c r i p
// J a v a S c r i p t

//           c
//         s c r
//       a s c r i
//     v a s c r i p
//   a v a s c r i p t
// J a v a S c r i p t !

// scrivo una funzione che prende in ingresso una stringa 
// e ritorna quella stringa senza tanti caratteri ad inizio e 
// fine stringa
// quanto e' il numero dentro charsNumber
function getLine(string, charsNumbers) {
    // posso usare slice e risolverlo in una riga
    // return string.slice(charsNumbers, string.length - charsNumbers)

    // oppure posso non essere a consocenza di slice e lo 
    // risolvo con un algoritmo
    let res = ''
    for (let i = charsNumbers; i < string.length - charsNumbers; i++) {
        res += string[i]
    }
    return res
}

// scrivo una funzione che mette gli spazi tra ogni carattere della
// stringa che ricevo in input
function insertSpacesBetween(string) {
    // let res = ''
    // for (let i = 0; i < string.length; i++) {
    //     res += string[i] + ' '
    // }

    // return res

    return string.split('').join(' ')
}

function insertSpacesLeft(string, n) {
    let res = string
    for (let i = 0; i < n; i++) {
        res = ' ' + res
    }

    return res
}

function lineBacchettaMagica(string, charsNumber) {
    let line = getLine(string, charsNumber)
    line = insertSpacesBetween(line)
    line = insertSpacesLeft(line, charsNumber * 2)
    return line
}

function solution(string) {
    let res = []
    let to = (string.length / 2)
    for (let i = 0; i < to; i++) {
        res.push(lineBacchettaMagica(string, i))
    }

    // o reverse dell'array e stampo da sx a dx

    // se siamo nella condizione in cui la stringa e' pari
    // allora dobbiamo mettere la punta della piramide
    if (string.length % 2 == 0) {
        let middleCharIndex = (string.length / 2) - 1
        let middleChar = string[middleCharIndex]
        console.log(insertSpacesLeft(middleChar, (to * 2) - 1))
    }

    // oppure stampo da dx a sx
    for (let i = res.length - 1; i >= 0; i--) {
        console.log(res[i])
    }
}

solution("JavaScript")