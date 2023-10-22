function flowers(n) {
  if (n < 1) return

  flowers(n / 2)
  console.log(createFlower(n))
  flowers(n / 2)
}

function createFlower(n) {
  let s = ''
  for (let i = 0; i < n; i++) {
    s += '-'
  }
  return s + '@'
}

// flowers(4)

function flowersIterative(n) {
  if (n < 1) {
    return
  }

  const stack = []

  while (stack.length > 0 || n >= 1) {
    while (n >= 1) {
      stack.push(n)
      n = n / 2
    }

    n = stack.pop()
    console.log(createFlower(n))
    n = n / 2
  }
}

flowersIterative(4)
