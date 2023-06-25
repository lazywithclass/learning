const zero  = f => x => x
const one   = f => x => f(x)
const two   = f => x => f(f(x))
const three = f => x => f(f(f(x)))
const four  = f => x => f(f(f(f(x))))

// https://en.wikipedia.org/wiki/Church_encoding

// we need to call f g times
const mul = (f, g) => f(g)
