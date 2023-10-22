let arr = [1, 2, 3, 2, 3]


// 1)
//
// let i = 0
// let j = arr.length - 1
// let palindrome = true
// while (i <= j) {
//   if (arr[i] != arr[j]) {
//     palindrome = false
//     break
//   }

//   i++
//   j--
// }

// console.log(palindrome)

// 2)
//
// let startingFromLeft = []
// for (let i = 0; i < arr.length; i++) {
//   startingFromLeft.push(arr[i])
// }

// let startingFromRight = []
// for (let i = arr.length - 1; i >= 0; i--) {
//   startingFromRight.push(arr[i])
// }

// let palindrome = true
// for (i = 0; i < arr.length; i++) {
//   if (startingFromLeft[i] !== startingFromRight[i]) {
//     palindrome = false
//   }
// }
//
// console.log(palindrome)

// 2e)
//
function isPalindrome(arr) {
  let startingFromLeft = []
  let startingFromRight = []
  for (let i = 0; i < arr.length; i++) {
    startingFromLeft.push(arr[i])
    startingFromRight.unshift(arr[i])
  }

  let palindrome = true
  for (i = 0; i < arr.length; i++) {
    if (startingFromLeft[i] !== startingFromRight[i]) {
      palindrome = false
    }
  }
  return palindrome
}

console.log(isPalindrome([1,2,3]))
console.log(isPalindrome([1]))
console.log(isPalindrome([1,2,3,4,3,2,1]))

// 3)
//
// let startingFromLeft = ""
// for (let i = 0; i < arr.length; i++) {
//   startingFromLeft += arr[i]
// }

// let startingFromRight = ""
// for (let i = arr.length - 1; i >= 0; i--) {
//   startingFromRight += arr[i]
// }

// console.log(startingFromLeft === startingFromRight)

// 4)
//
// let sx = arr.join('')
// let dx = arr.reverse().join('')
// console.log(sx === dx)
