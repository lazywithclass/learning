## Shift array

Write a function that accepts an array as argument, it should shift all elements of the array by two to the right,
all elements "exiting" from the array should re-enter on the left.

For example

``` javascript
let array = [ 2, 7, 3, 0, 42 ]
shiftToTheRight(array, 3)
console.log(array) // [ 0, 42, 2, 7, 3 ]

shiftToTheRight(array, 1)
console.log(array) // [ 7, 3, 0, 42, 2 ]
```

