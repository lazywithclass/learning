// odd numbers sequence
let rec seqOdd s = seq {
    let n = Seq.item 1 s
    yield n
    yield! seqOdd (Seq.skip 2 s)
}

seqOdd (Seq.initInfinite id)


// sequence 1, 1, 2, 6, ..., n!, ...
let rec fact n =
    match n with
        | 0 | 1 -> 1
        | _ -> n * fact (n - 1)

let rec seqFacts s = seq {
    let n = Seq.item 0 s
    yield fact n
    yield! seqFacts (Seq.skip 1 s)
}

seqFacts (Seq.initInfinite id) |> Seq.take 10 |> Seq.toList


// sequence 1, 1, 2, 6, ..., n!, ...
// where i + 1'st element is generated from the i'th element by multiplication
// with i + 1
let rec seqFactsImproved s prev = seq {
    let n = Seq.item 0 s
    let curr = if n <= 1 then 1 else prev * (n)
    yield curr
    yield! seqFactsImproved (Seq.skip 1 s) curr
}

seqFactsImproved (Seq.initInfinite id) 0 |> Seq.take 10 |> Seq.toList


// given i and n selects the sublist [ai;ai+1;...;ai+n-1] of a sequence
// seq [a0;a1;...]
let rec seqSubList i n s = seq {
    let item = Seq.item 0 s
    if item >= i && item <= n then yield n
    yield! seqSubList i n (Seq.skip 1 s)
}

let sequence = seqSubList 2 4 (Seq.initInfinite id)
Seq.take 0 sequence


let cons a s = seq {
    yield a
    yield! s
}

let append s s' = seq {
    yield! s
    yield! s'
}

let sq1 = seq {
  yield 0
  yield 1
  yield 2
  yield 3
}

let sq2 = seq {
  yield 100
  yield! sq1
  yield 200
  yield! sq1
}

Seq.toList (cons 100 sq2)
Seq.toList (append sq1 sq1)

let head s = seq {
    yield Seq.item 0 s
}

let tail s = seq {
    yield! (Seq.skip 1 s)
}

Seq.toList (tail sq2)

let rec intFrom n = seq {
    yield n
    yield! intFrom (n + 1)
}

intFrom 5 |> Seq.take 10 |> Seq.toList

let allNat = intFrom 0
allNat |> Seq.take 10 |> Seq.toList

let rec map mapper s = seq {
    let x = Seq.item 0 s
    yield mapper x
    yield! map mapper (Seq.skip 1 s)
}

map (fun x -> x * x) allNat |> Seq.take 15 |> Seq.toList

let rec filter pred s = seq {
    let x = Seq.item 0 s
    if pred x then yield x
    yield! filter pred (Seq.skip 1 s)
}

filter (fun x -> x % 3 = 0) allNat |> Seq.take 20 |> Seq.toList
