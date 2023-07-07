// Given a binary tree T1 and a binary tree T2 write a function
// which checks if T1 is subtree of T2

// I want to create a BTree that has could be read left to right and then
// to the next level in an increasing fashion
function makeBTree(arr, fathers) {
  let newFathers = []
  while (fathers.length > 0) {
    let father = fathers.shift()
    if (arr.length > 0) {
      father.l = { v: arr.shift(), l: null, r: null }
      newFathers.push(father.l)
    }
    if (arr.length > 0) {
      father.r = { v: arr.shift(), l: null, r: null }
      newFathers.push(father.r)
    }
  }

  if (newFathers.length > 0) {
    makeBTree(arr, newFathers)
  }
}

// let root = {v: 0, l: null, r: null}
// makeBTree([1,2,3,4,5,6,7,8,9,10,11,12], [root])
// console.log(JSON.stringify(root, null, '  '))


// return true if t2 is subtree of t1, false otherwise
function isSubTree(t1, t2) {

  // do a pre order traversal on the tree
  // for each visit check if the node value is equal to the root value
  // of t2 if so start checking if the whole tree is contained

  function traverse(t1) {
    if (t1 == null) {
      return false
    }

    if (t1.v == t2.v && contained(t1, t2)) {
      return true
    }

    return traverse(t1.l) || traverse(t1.r)
  }

  // return true if t2 is subtree of t1, false otherwise
  function contained(t1, t2) {
    if (t2 == null) {
      return true
    }
    return t1.v == t2.v ?
      contained(t1.l, t2.l) && contained(t1.r, t2.r) : false
  }

  return traverse(t1)
}

let t1 = {v: 0, l: null, r: null}
makeBTree([1,2,3,4,5,6,7,8,9,10,11,12], [t1])
let t2 = {v: 2, l: null, r: null}
makeBTree([5,6,11,12], [t2])
console.log(isSubTree(t1, t2)) // true

t2 = {v: 2, l: null, r: null}
makeBTree([5,6], [t2])
console.log(isSubTree(t1, t2)) // true

t2 = {v: 1, l: null, r: null}
makeBTree([5,6], [t2])
console.log(isSubTree(t1, t2)) // false

t2 = {v: 2, l: null, r: null}
makeBTree([3,6], [t2])
console.log(isSubTree(t1, t2)) // false
