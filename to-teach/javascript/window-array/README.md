## Window array

The idea is to have a sliding window over an array, so that at max `n` elements are shown at a given time, also the 
window can be moved to show elements.

Use closures to keep the required state private.

``` javascript
let moveWindow = makeWindowArray([ 3, 4, 1, 5, 7, 9, 0, 8 ], 4)
moveWindow()   // [ 3, 4, 1, 5 ]
moveWindow(1)  // [ 4, 1, 5, 7 ]
moveWindow(-1) // [ 3, 4, 1, 5 ]
```

