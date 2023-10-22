let map = {
  name: 'Howard',
  secondName: 'Phillips',
  surname: 'Lovecraft'
}

let key = 'name'
console.log(map[key])

console.log(map.key) // no!
console.log(map.name)

console.log(Object.keys(map))
console.log(Object.values(map))
