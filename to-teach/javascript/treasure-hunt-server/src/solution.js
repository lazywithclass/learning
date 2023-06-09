const axios = require('axios')


async function solution() {

  async function findTreasure(key) {
    let res = await axios.get(`https://37b2-2-44-90-143.ngrok-free.app/keys/${key}`, {
      headers: {
        user: 'alberto'
      }
    })
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

  findTreasure('a0b1c2d3e4f5')
}

solution()
