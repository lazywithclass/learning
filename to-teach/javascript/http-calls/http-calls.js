function getPosts() {
  // con .then io dico: fetch, fai la chiamata all'URL che ti ho passato
  // una volta che la risposta del server e' pronta
  // esegui il codice che sta nel corpo della funzione che ti ho passato
  fetch("http://localhost:3000/posts").then(function(res) {
    res.json().then(function(json) {
      console.log(json)
    })
  })
}

async function getPostsV2() {
  // per passare alla riga con il json DEVI aspettare che la riga subito
  // sotto questo commento abbia finito questo lo facciamo attraverso la
  // keyword await
  let res = await fetch("http://localhost:3000/posts")
  // qui invece dobbiamo aspettare che tutti i dati vengano convertiti da
  // 1 e 0 in json
  let json = await res.json()
  console.log(json)
}

async function createPost(title) {
  let res = await fetch("http://localhost:3000/posts", {
    method: "POST",
    body: JSON.stringify({ title: title })
  })
  console.log(res.status)
}

async function deletePost(id) {
  let res = await fetch("http://localhost:3000/posts/" + id, {
    method: "DELETE"
  })
  console.log(res.status)
}

// createPost("un altro post")
// createPost("un altro post ancora")

// come viene creato l'id?
// import { randomBytes } from 'node:crypto'
// console.log(randomBytes(2).toString('hex'))

async function updatePost(id, post) {
  let res = await fetch("http://localhost:3000/posts/" + id, {
    method: "PUT",
    body: JSON.stringify(post)
  })
  console.log(res.status)
}

// updatePost("1", { title: "un titolo", views: 101 })

// getPostsV2()



// scrivere una funzione che ritorna la somma di tutte le views di
// tutti i post

// dovremo fare una GET
// creiamo una variabile SOMMA
// dovremo fare un ciclo
//   per ogni post prendiamo il campo views
//   se c'e' lo sommiamo a SOMMA altrimenti contiamo come 0

async function sumAll() {
  let somma = 0
  let res = await fetch("http://localhost:3000/posts")
  let json = await res.json()

  // quando siete qua e' come se il contenuto della variabile json
  // fosse sempre stato nel vostro codice, o file
  // quindi come se fosse stato cosi
  // let json = [
  //   { title: 'un titolo', views: 101, id: '1' },
  //   { id: '2', title: 'another title', views: 200 },
  //   { id: 'baed', title: 'un altro post' },
  //   { id: 'c700', title: 'un altro post ancora' }
  // ]

  for (let i = 0; i < json.length; i++) {
    if (json[i].views) {
      somma = somma + json[i].views
    }
  }

  return somma
}

console.log(await sumAll())
getPostsV2()
