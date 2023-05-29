// NOTICE
// lots of duplication, wont refactor


import fs from 'node:fs/promises'
import todos from '../db/todos.json' assert { type: 'json' }

const DB_PATH = './db/todos.json'

let NEXT = Object
  .keys(todos)
  .reduce((biggest, id) => biggest > id ? biggest : id, 0)


export const create = async (req, res) => {
  NEXT++
  todos[NEXT] = req.body

  // never use sync, go the async way
  // fs.writeFileSync(DB_PATH, JSON.stringify(users, null, '  '))

  await fs.writeFile(DB_PATH, JSON.stringify(todos, null, '  '))
  res
    .status(201)
    .send({
      message: 'todo created'
    })
}

export const get = (req, res) => {
  let todo = todos[req.params.id]
  if (todo) {
    res.send({ data: todo })
  } else {
    res
      .status(200)
      .send({
        data: {},
        error: true,
        message: 'todo not found'
      })
  }
}

export const getAll = (req, res) => {
  res.send(todos)
}

export const search = (req, res) => {
  const query = req.query
  let filtered = Object.values(todos)
      .filter(u => u.name === query.title)
  res.send(filtered)
}

export const update = async (req, res) => {
  let todo = todos[req.params.id]
  if (todo) {
    let newTodo = { ...todo, ...req.body }
    todo[req.params.id] = newTodo
    await fs.writeFile(DB_PATH, JSON.stringify(todos, null, '  '))
    res.send(newTodo)
  } else {
    res
      .status(200)
      .send({
        data: {},
        error: true,
        message: 'todo not found'
      })
  }
}

export const remove = async (req, res) => {
  let todo = todos[req.params.id]
  if (todo) {
    delete todos[req.params.id]
    await fs.writeFile(DB_PATH, JSON.stringify(todos, null, '  '))
    res.status(200).end()
  } else {
    res
      .status(200)
      .send({
        data: {},
        error: true,
        message: 'todo not found'
      })
  }
}
