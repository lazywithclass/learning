import axios from 'axios'

async function call(id) {
  const url = id ?
        `http://localhost:3000/users/${id}` : 'http://localhost:3000/users'
  let res = await axios.get(url)
  console.log(res.status, res.data)
}

call(process.argv[2])
