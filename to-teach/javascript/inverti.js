function invertiArray(arr) {
  for (let i = 0; i < arr.length; i++) {
    arr.reverse();
  }
  return arr;
}
console.log(invertiArray([7, 8, 6, 5, 4]));
