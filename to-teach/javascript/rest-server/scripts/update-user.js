import axios from 'axios'

async function call() {
  let res = await axios.put('http://localhost:3000/users/2', {
    name: 'Marie',
    surname: 'Curie'
  })
  console.log(res.status, res.data)
}

call()
