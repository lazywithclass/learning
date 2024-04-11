async function get() {
  let res = await fetch("https://httpbin.org/geta")
  if (res.status >= 400) {
    console.log(res.status, await res.text())
  } else {
    let json = await res.json()
    console.log(res.status, json)
  }
}

async function post() {
  let res = await fetch("https://httpbin.org/post", {
    method: "POST",
    headers: {
      "Content-type": "application/json"
    },
    body: JSON.stringify({ answer: 42 })
  })
  let json = await res.json()
  console.log(res.status, json)
}

post()
