const express = require('express')
const app = express()
const bodyParser = require('body-parser')
app.use(bodyParser.json())

const routes = require('./routes')

app.get('/array', routes.get)
app.post('/array', routes.push)
app.delete('/array', routes.pop)

app.listen(3000, () => console.log('server started'))
