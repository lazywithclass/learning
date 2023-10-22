function findLongestSubstring1(s) {
  let parola = ''
  let res = ''
  for (let i = 0; i < s.length; i++) {
    const lettera = s[i]
    let foundIndex = parola.indexOf(lettera)
    if (foundIndex > -1) {
      if (parola.length > res.length) {
        res = parola
      }
      parola = parola.substring(foundIndex + 1, i)
    } else {
      parola += lettera
    }
  }
  if (parola.length > res.length) {
    res = parola
  }
  return res
}

function findLongestSubstring2(string) {
  let string = "abcabcdab"
  let longest = ""
  let current = ""
  for (let i = 0; i < string.length; i++) {
    if (current.indexOf(string[i]) == -1) {
      current += string[i]
    } else {
      if (current.length > longest.length) {
        longest = current
        current = ""
        i = string.lastIndexOf(string[i]) + 1
      }
    }
  }
  if (current.length > longest.length) {
    longest = current
  }

  return longest
}

console.log(findLongestSubstring1('stringaaacciu'))
console.log(findLongestSubstring2('stringaaacciu'))

console.log(findLongestSubstring1('casessssroma'));
console.log(findLongestSubstring2('casessssroma'));

console.log(findLongestSubstring1('abcabcbb'));
console.log(findLongestSubstring2('abcabcbb'));

console.log(findLongestSubstring1('pwwkew'));
console.log(findLongestSubstring2('pwwkew'));
