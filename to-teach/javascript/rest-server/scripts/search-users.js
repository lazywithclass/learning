import axios from 'axios'

async function search(params) {
  // caso limite params lungo 1
  if (params.length % 2 == 1) {
    console.log('params should be even')
    return
  }

  let path = 'http://localhost:3000/users/search?'
  let separator = ''
  for (let i = 0; i < params.length; i+=2) {
    path += `${separator}${params[i]}=${params[i+1]}`
    separator = '&'
  }

  let res = await axios.get(path)
  console.log(res.data)

}

let [a, b, ...params] = process.argv
// or also:
// console.log(process.argv.splice(2))

search(params)
