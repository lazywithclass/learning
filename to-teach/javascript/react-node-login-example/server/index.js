import 'dotenv/config'

import express from "express"
const app = express()
const port = 3001

import bodyParser from "body-parser"
app.use(bodyParser.json())

import cors from "cors"
app.use(cors())

import { login } from "./db.js"

app.post('/login', async (req, res) => {
  const [exists, _] = await login(req.body.username, req.body.password)
  if (exists) {
    res.json({ msg: "logged in" })
  } else {
    res.status(401).json({ msg: "Username or password not found" })
  }
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
