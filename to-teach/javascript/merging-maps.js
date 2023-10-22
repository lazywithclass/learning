let map1 = {
  color: 'blue',
  name: 'puffetta',
  sex: 'female'
}

let map2 = {
  color: 'pink',
  name: 'gargamella',
  evil: false
}

// voglio creare una mappa result tale che
// * deve avere tutte le chiavi di tutte e due le mappe
// * eventuali duplicati vengono sovrascritti dalla seconda mappa

function mergeMaps(m1, m2) {
  let res = {}
  let keys = Object.keys(m1)
  for (let i = 0; i < keys.length; i++) {
    let key = keys[i]
    res[key] = m1[key]
  }

  // per ogni chiave di m2
  // prendo quella chiave e sovrascrivo / creo una relazione dentro res
  keys = Object.keys(m2)
  for (let i = 0; i < keys.length; i++) {
    let key = keys[i]
    // che ci sia o no una relazione identificata dalla chiave key
    // a questo punto sovrascriviamo o meno (quindi creiamo)
    res[key] = m2[key]
  }

  return res
}

let res = mergeMaps(map1, map2)
console.log(res)
