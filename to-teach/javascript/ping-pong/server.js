const express = require('express')
const app = express()

let port
let otherServer = ''
let random = randomIntFromInterval(1, 100)

// assumo che url nella req sia sempre corretto
// quindi non ne testo il contenuto
app.put('/start', async (req, res) => {
  const url = req.query.url
  otherServer = url
  console.log(`other server url: ${otherServer}`)

  await guessNumber(otherServer)

  res.send()
})

app.get('/try/:guess', async (req, res) => {
  const guess = req.params.guess
  if (guess == random) {
    console.log(`number was guessed (${random}), going at rest`)
    res.json({ guessed: true })
  } else {
      console.log(`number wasnt guessed (guess: ${guess}, number: ${random})`)
    // siamo nel caso in cui il numero non e' stato individuato
    // quindi devo fare una chiamata da questo server all'altro
    // quale e' l'url dell'altro server?
    // 1) potrebbe essere dentro otherServer se lo start e' stato fatto qui
    // 2) potrebbe non esserci se lo start e' stato fatto altrove
    if (!otherServer) {
      otherServer = req.headers['x-origin']
    }
    res.json({ guessed: false })
    const result = await guessNumber(otherServer)
    console.log(`my turn now: ${result[1]}`)
    if (result[0]) {
      console.log(`this server guessed the number ${result[1]}`)
    }
  }
})

async function guessNumber(url) {
  // indovina un numero tra 1 e 100
  // invia questo numero all'url
  // ha indovinato?
  //   se si ho finito
  //   (se no l'altro server dovra' fare la chiamata) non ci interessa realmente in questa funzione, lo scriviamo solo come reminder

  const guess = randomIntFromInterval(1, 100)
  const res = await fetch(`${url}/try/${guess}`, {
    headers: {
      'x-origin': `http://localhost:${port}`
    }
  })
  const json = await res.json()
  return [json.guessed, guess]
}

function randomIntFromInterval(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min)
}

function start(p) {
  port = p
  app.listen(port, () => {
    console.log(`server started on port ${port}, and thought ${random}`)
  })
}

if (process.argv[2]) {
  port = process.argv[2]
  start(process.argv[2])
}

module.exports = start
