function findLongestSubstring(string) {
    let res = ''
    let curr = ''
    for (let i = 0; i < string.length; i++) {
        for (let j = i+1; j < string.length; j++) {
            if (curr.includes(string[j])) {
                if (curr.length > res.length) {
                    res = curr
                }
                curr = ''
                break
            }
            curr += string[j]
        }
    }
    return res
}

function findLongestSubstring2(string) {
    let res = ''
    let curr = ''

    let i = 0
    let j = 1
    while (i < string.length && j < string.length) {
        if (curr.includes(string[j])) {
            if (curr.length > res.length) {
                res = curr
            }
            curr = ''
            i++
            j = i
        } else {
            curr += string[j]
            j++
        }
    }
    return curr.length > res.length ? curr : res
}

function findLongestSubstring3(s){
    let parola=''
    let maxLenght=0
    let res=''
    for (let i = 0; i < s.length; i++) {
        const lettera = s[i];
        if (!parola.includes(lettera)) {
            parola+=lettera
        } else {
            if (parola.length>maxLenght) {
                maxLenght=parola.length
                res=parola
            }
            parola=''
        }
    }
    return res
}

function findLongestSubstring4(s){
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
    if (parola.length>res.length) {
        res = parola
    }
    return res
}


console.log(findLongestSubstring4('abcabcbb'));
console.log(findLongestSubstring4('casessssroma'));
console.log(findLongestSubstring4('pwwkew'));
console.log(findLongestSubstring4('stringaaacciu'));
console.log(findLongestSubstring4('abcdecfghil'));

