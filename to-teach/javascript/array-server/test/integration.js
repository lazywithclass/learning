const { test, mock } = require('node:test')
const assert = require('node:assert')

test('get', async () => {
  const res = await fetch('http://localhost:3000/array')
  const json = await res.json()
  assert.deepEqual(json, [])
})

// we can do better

test('usage', async () => {
  await fetch('http://localhost:3000/array', {
    method: 'POST',
    headers: {
      "Content-type": "application/json"
    },
    body: JSON.stringify({ number: 40 })
  })
  await fetch('http://localhost:3000/array', {
    method: 'POST',
    headers: {
      "Content-type": "application/json"
    },
    body: JSON.stringify({ number: 41 })
  })
  await fetch('http://localhost:3000/array', {
    method: 'POST',
    headers: {
      "Content-type": "application/json"
    },
    body: JSON.stringify({ number: 42 })
  })

  res = await fetch('http://localhost:3000/array')
  let json = await res.json()
  assert.deepEqual(json, [{number: 40}, {number: 41}, {number: 42}])

  await fetch('http://localhost:3000/array', {
    method: 'DELETE'
  })
  await fetch('http://localhost:3000/array', {
    method: 'DELETE'
  })
  await fetch('http://localhost:3000/array', {
    method: 'DELETE'
  })

  res = await fetch('http://localhost:3000/array')
  json = await res.json()
  assert.deepEqual(json, [])
})
