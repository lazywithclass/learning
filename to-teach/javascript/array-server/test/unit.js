const { test, mock } = require('node:test')
const assert = require('node:assert')
const { get, push, pop, reset } = require('../routes')

// in an ideal world, we would've used beforeEach
// and not a reset function

test('put', () => {
  reset()
  const body = 42
  const req = {
    body
  }
  let res = {
    status: mock.fn(),
    send: mock.fn()
  }

  push(req, res)
  assert.equal(res.status.mock.calls[0].arguments[0], 200)
  assert.equal(res.send.mock.calls.length, 1)

  res = {
    status: mock.fn(),
    json: mock.fn()
  }
  get({}, res)
  assert.equal(res.status.mock.calls[0].arguments[0], 200)
  assert.deepEqual(res.json.mock.calls[0].arguments[0], [body])
})

// notice how get is after put, and still works thanks to reset
// try to comment the reset calls to see the problem
test('get', () => {
  reset()
  const res = {
    status: mock.fn(),
    json: mock.fn()
  }

  get({}, res)

  assert.equal(res.status.mock.calls[0].arguments[0], 200)
  assert.deepEqual(res.json.mock.calls[0].arguments[0], [])
})

test('delete', () => {
  reset()
  const body = 42
  let req = {
    body
  }
  let res = {
    status: mock.fn(),
    send: mock.fn()
  }

  push(req, res)

  req = {
  }
  res = {
    status: mock.fn(),
    send: mock.fn()
  }

  pop(req, res)
  assert.equal(res.status.mock.calls[0].arguments[0], 200)
  assert.equal(res.send.mock.calls.length, 1)

  res = {
    status: mock.fn(),
    json: mock.fn()
  }
  get({}, res)
  assert.equal(res.status.mock.calls[0].arguments[0], 200)
  assert.deepEqual(res.json.mock.calls[0].arguments[0], [])
})

// post per creare un nuovo array?
