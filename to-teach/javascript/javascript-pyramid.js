// write a function such that given JavaScript
// prints the following
//          s
//         s c
//       a s c r
//     v a s c r i
//   a v a s c r i p
// J a v a S c r i p t

// create two variables, LEFT and RIGHT
// create one variable LEN as length of the string
// given MIDDLE as LEN / 2
// if the string is even
//   LEFT starts at MIDDLE - 1, RIGHT starts at MIDDLE
// else 
//   LEFT and RIGHT start at Math.floor(MIDDLE)
// while LEFT >= 0 and RIGHT < LEN
//   given LINE as the accumulation of 2 * LEFT spaces
//   accumulate in LINE the characters in the string between LEFT and RIGHT
//   LEFT--
//   RIGHT++
//   print LINE
//   LINE = ''

function javascriptPyramid(str) {
    let left, right
    let len = str.length
    let middle = len / 2
    if (len % 2 == 0) {
        left = middle - 1
        right = middle
    } else {
        left = Math.floor(middle)
        right = Math.floor(middle)
    }

    while (left >= 0 && right < len) {
        let line = ''
        for (let i = 0; i < 2 * left; i++) {
            line += ' '
        }
        for (let i = left; i <= right; i++) {
            line += str[i] + ' '
        }
        left--
        right++
        console.log(line)
        line = ''
    }
}
javascriptPyramid('JavaScript')