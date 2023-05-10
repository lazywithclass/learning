function isValid(parens) {
    let opened = []
    for (let i = 0; i < parens.length; i++) {

        if (parens[i] == '(' || parens[i] == '*') {
            opened.unshift(parens[i])
            continue
        }

        // we are dealing with a ')'

       if (firstNonWildcard('(', opened)) {
            opened.shift()
        } else {
            opened.unshift(')')
        }
    }

    // not really working
    let start = 0, end = opened.length - 1
    while (start < end) {
        if (!(opened[start] == ')' && opened[end] == '*' ||
            opened[start] == '*' && opened[end] == '(')) {
                return false
        }
        start++
        end--
    }

    return true
}

function firstNonWildcard(token, stack) {
    for (let i = 0; i < stack.length; i++) {
        if (stack[i] == '*') {
            continue
        }
        let found = stack[i] == token
        return [found, found ? i : -1]
    }
    return [false, -1]
}

// console.log(isValid('*)'))
// console.log(isValid('(*'))
// console.log(isValid('*'))
// console.log(isValid('(*))'))
// console.log(isValid('()*'))
// console.log(isValid('(()*)'))
// console.log(isValid('(*())'))
// console.log(isValid('**)'))
console.log(isValid('*(*'))
console.log('----------------')
// console.log(isValid('**('))

