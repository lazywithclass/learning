const express = require('express')
const app = express()
const port = 3000

const students = require('../db/students.json')
const SECRET = "6398"

app.get('/students', (req, res) => {
    if (req.headers.key == SECRET) {
        res.send(students)
    } else {
        res.status(404).end()
    }
})

// parrot
app.get('/key', (req, res) => {
    res.send(req.headers.key)
})

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})