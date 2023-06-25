import fs from "node:fs/promises"
import axios from "axios"

function save(str) {
    // something
    fs.writeFileSync("./path/to/string", str) // this one blocks
    // something else
}

async function save2(str) {
    // something
    await fs.writeFile("./path/to/string", str) // this one DOESNT block
    // something else
}

// save2 translated to use promises
function save3(str) {
    // something
    fs
        .writeFile("./path/to/string", str)
        .then(() => {
            // something else
            // something else 2
            // something else 3
            // something else 4
        })
}

// the problem with promises:
function problem(url1, url2) {
    // the result of the first is required in the second
    axios
        .get(url1)
        .then((res) => {
            // use res somehow
            axios.get(url2).then(() => {

            })
        })

    // instead with async / await:
    // const res = await axios.get(url1)
    // use res somehow
    // await axios.get(url2)
}