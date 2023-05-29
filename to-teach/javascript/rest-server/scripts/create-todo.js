import axios from 'axios'

async function call() {
  let res = await axios.post('http://localhost:3000/todos', {
    title: 'Buy the milk'
  })
  console.log(res.status, res.data)
}

call()
