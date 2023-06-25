const axios = require('axios')


async function findTreasure(key) {
  let res = await axios.get(`http://localhost:3000/keys/${key}`)
  if (res.data.treasure) {
    console.log(`found: ${key}`)
    return
  }

  if (!res.data.children) {
    console.log(res.data)
    return
  }
  for (let i = 0; i < res.data.children.length; i++) {
    await findTreasure(res.data.children[i].key)
  }
}

function extractKeys(data) {
 return data.children.map(c => c.key)
}

async function findTreasureNotRecursive(key) {
  let res = await axios.get(`http://localhost:3000/keys/${key}`)
  if (res.data.treasure) {
    console.log(`found: ${key}`)
    return
  }

  let keysLeft = extractKeys(res.data)
  while (keysLeft.length > 0) {
    let key = keysLeft.shift()
    res = await axios.get(`http://localhost:3000/keys/${key}`)
    if (res.data.treasure) {
      console.log(`found: ${key}`)
      return
    }
    keysLeft = [...keysLeft, ...extractKeys(res.data)]
  }
}

function solution() {
  findTreasure('a0b1c2d3e4f5')
  findTreasureNotRecursive('a0b1c2d3e4f5')
}

solution()
