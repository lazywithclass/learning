// implement sift : int -> seq<int> -> seq<int>
// that costructs the seq removing all products of a

let rec sift a s = seq {
    let n = Seq.item 0 s
    if n % a = 0 then yield! sift a (Seq.skip 1 s)
    else yield n

    yield! sift a (Seq.skip 1 s)
}

// all natural numbers
let nat = Seq.initInfinite id
let sq1 = sift 2 nat
let sq2 = sift 3 nat

Seq.toList (Seq.take 10 nat)
Seq.toList (Seq.take 10 sq1)
Seq.toList (Seq.take 15 sq2)

let rec sieve s = seq {
    let x = Seq.item 0 s
    yield x
    let rest = Seq.skip 1 s
    yield! sieve (sift x rest)
}

let nat2 = Seq.skip 2 nat
let primes = sieve nat2
Seq.toList (Seq.take 10 primes)

let siftC a sq = Seq.cache (sift a sq)
let rec sieveC s = seq {
    let x = Seq.item 0 s
    yield x
    let rest = Seq.skip 1 s
    yield! sieveC (sift x rest)
}

let primesC = Seq.cache (sieveC nat2)
