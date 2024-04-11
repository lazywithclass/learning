const fs = require("fs")

function write() {
  let product = {
    id: 1,
    name: "calze"
  }
  fs.writeFileSync("out.json", JSON.stringify(product))
}

function read() {
  let contents = fs.readFileSync("out.json")
  let asString = contents.toString()
  let asObject = JSON.parse(asString)
  console.log(asObject.id)
}

read()
