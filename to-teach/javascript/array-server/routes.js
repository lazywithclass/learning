let array = []

const get = (req, res) => {
  res.status(200)
  res.json(array)
}

const push = (req, res) => {
  array.push(req.body)
  res.status(200)
  res.send()
}

const pop = (req, res) => {
  array.pop()
  res.status(200)
  res.send()
}

// for testing purposes
const reset = () => array = []

module.exports = { get, push, pop, reset }
