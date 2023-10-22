let string = "oh captain my captain!"
let occurrencies = {}

// cant create 25 vars and 25 ifs, so:

for (let i = 0; i < string.length; i++) {
  let letter = string[i]
  if (occurrencies[letter]) {
    occurrencies[letter] = occurrencies[letter] + 1
  } else {
    occurrencies[letter] = 1
  }
}
