const axios = require('axios')

async function wait(ms) {
    // wrap setTimeout with a Promised so it could 
    // be awaited
    return new Promise(function(resolve) {
        console.log("wait")
        setTimeout(function() {
            console.log("finished waiting")
            resolve()
        }, ms)
    })
}

const DEFAULT_WAIT_TIME = 500
let WAIT_TIME = DEFAULT_WAIT_TIME

async function findId(url) {
    for (let i = 0; i < 10000; i++) {
        const key = i.toString().padStart(4, "0")
        await wait(WAIT_TIME)
        const res = await axios.get(url, {
            // do not throw an exception, never
            validateStatus: () => true,
            headers: { key }
        })
        if (res.status == 200) {
            console.log(`Key was found for ${url}: ${key}`)
            return
        }

        if (res.status == 404) {
            console.log(`${url}: ${key} is not a valid key`)
            WAIT_TIME = DEFAULT_WAIT_TIME
        } else if (res.status == 429) {
            // timeout so we increase the waiting time
            WAIT_TIME = WAIT_TIME * 2
            console.log("Increasing wait time to", WAIT_TIME)
            i--
        } else { // CATCH ALL, no need try catch
            console.log(res)
            console.log("ERROR!", res.status, "HANDLE IT!", key)
            return
        }
    }  
}

const url = process.argv[2]
if (!url) {
    console.log("enter a url")
    return
}
findId(url)