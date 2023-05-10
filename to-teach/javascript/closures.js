let arr = [1,2,3,4]

const append = arr => n => arr.push(n)

const app = append(arr)
console.log(arr)
app(5)
app(6)
console.log(arr)

// continuations

const fatt = n => {
    if (n == 1) return n
    else return n * (fatt(n - 1))
}

(function (a) {})
(function (a ) { return a() })()
// lambda calculus