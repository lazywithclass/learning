const express = require('express')
const app = express()
const port = 3000
app.set('view engine', 'ejs')
app.use(express.static('public'))

const paths = require('../db/paths.json')


function showFirstLevel(obj) {
  let firstLevel = {...obj}
  firstLevel.children = firstLevel
    .children
    .map(child => ({ key: child.key, children: 'information hidden' }))
  return firstLevel
}

function find(key, paths) {
  if (key == paths.key) {
    return [true, paths]
  }

  for (let i = 0; i < paths.children.length; i++) {
    let found = find(key, paths.children[i])
    if (found[0]) {
      return found
    }
  }

  return [false]
}

// app.use((req, res, next) => {
//   if (!req.headers.user) {
//     res.status(401).send({
//       error: true,
//       message: 'you must specify a header property \'name\' with your zoom account name'
//     })
//     return
//   }
//   next()
// })

app.use((req, res, next) => {
  console.log(req.headers.user, new Date().getTime())
  next()
})

app.get('/', (req, res) => {
  res.send({
    error: true,
    message: 'Hello, this is not the endpoint you\'re looking for, you might want to start from /root; notice the redirect!'
  })
})

app.get('/root', (req, res) => {
  res.redirect('/keys/a0b1c2d3e4f5')
})

app.get('/keys/:key', (req, res) => {
  let found = find(req.params.key, paths)
  res.send(found[0] ? showFirstLevel(found[1]) : 'no')
})

app.get('/keys', (req, res) => {
  res.render('not-that-easy')
})

app.listen(port, '0.0.0.0', () => {
  console.log(`Example app listening on port ${port}`)
})
