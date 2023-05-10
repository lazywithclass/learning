// ()
// (())()

function isValid(parens) {
    let opened = []
    for (let i = 0; i < parens.length; i++) {
        if (parens[i] == '(') {
            opened.unshift('(')
            continue
        }

        if (opened[0] == '(') {
            opened.shift()
        } else {
            return false
        }
    }
    return opened.length == 0
}
console.log(isValid("((()))"))
console.log(isValid("((())))")) // false
console.log(isValid("((()))(())"))
console.log(isValid("((()))()"))
console.log(isValid("(()(()))"))
console.log(isValid("(()")) // false
