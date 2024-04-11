function change(a) {
  ++a
}

let n = 1
change(n)
console.log(n)


function increment(o) {
  o.id++
}
let a = { id: 1 }
increment(a)
console.log(a)

console.log()
console.log("-------------------")
console.log()


let db = {
  books: [{id: 1, name: "lotr"}, {id: 2, name: "1984"}],
  libraries: [
    {id: 1, name: "hogwards", books: [1]}, 
    {id: 2, name: "minas tirith", books: []}
  ]
}

function remove(bookId, libraryId) {
  let library = db.libraries.find(lib => lib.id == libraryId)
  console.log(library)
  let newBooks = library.books.filter(book => book !== bookId)
  library.books = newBooks
  console.log(library)
  console.log(db.libraries)
}

function removePassBy(bookId, library) {
  console.log("libraries", db.libraries)
  console.log(library)
  let newBooks = library.books.filter(book => book !== bookId)
  library.books = newBooks
  console.log(library)
  // db.libraries changed
  console.log(db.libraries)
}

// remove(1, 1)

let library = db.libraries.find(lib => lib.id == 1)
removePassBy(1, library)
