// let s = "a[b[f],c[g,h],d,e[i[m,n],l]"
let s = "a[c[d,e],f]"

// dato un nodo radice
// per ogni carattere
//   guardo il carattere corrente
//   lettera?
//     se si creo un nuovo nodo con quella lettera
//     appendo il nodo ai figli della radice
//   [?
//     se si ricorro passando l'ultimo nodo visto come radice
//     e la sottostringa
//   ]?
//     se si ritorno la radice
//   ,?
//     ignorato

function buildTree(s, root) {
  console.log(s)
  let nextRoot
  for (let char of s) {
    if (isLetter(char)) {
      nextRoot = { value: char, children: [] }
    }
    if (char === '[') {
      root.children = buildTree(s.substring(2), nextRoot)
    }
    if (char === ']') {
      return root
    }
  }

  return root
}

let result = buildTree(s, { children: [] })
console.log(JSON.stringify(result, null, '  '))

function isLetter(l) {
  return l.charCodeAt(0) >= 97 && l.charCodeAt(0) <= 122
}

