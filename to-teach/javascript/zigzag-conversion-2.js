function zigZag(str, n) {
    if(n >= str.length) return console.log(str)
    let map = new Map(), stamp = ''
    for(let i = 0, j = 1, count; i < str.length; i++, j += count) {
        if(!map.has(j)) map.set(j, str[i])
        else map.set(j, map.get(j)+str[i])
        if(j == 1) count = +1
        if(j == n) count = -1
    }
    map.forEach(function(v) { stamp += v })
    console.log(stamp)
}
zigZag('paypalishiring', 3)