let n = 10

for (let j = 0; j < n / 2; j++) {
  let riga = ""
  for (let i = 1; i <= n; i++) {
    riga = riga + "\t" + i
  }
  console.log(riga)

  riga = ""
  for (let i = n; i > 0; i--) {
    riga = riga + "\t" + i
  }
  console.log(riga)
}
