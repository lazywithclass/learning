A tool that given some software tells you which scenarios you forgot to handle.

### Examples 

#### Error handling here?

```javascript
async function httpCall(url) {
    const res = await fetch(url)
    const json = await res.json()
    return json
}
```

##### Paths

What if `result` is `undefined`?

```javascript
function performTask() {
    const result = task()
    if (result) {
        doSomethingWith(result)
    }
}
```