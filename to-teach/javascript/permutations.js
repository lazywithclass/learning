function getPermutations(str) {
  if (str.length === 0) {
    return []
  }

  let permutations = [str[0]]

  for (let i = 1; i < str.length; i++) {
    const currentChar = str[i]
    const newPermutations = []

    for (let j = 0; j < permutations.length; j++) {
      const permutation = permutations[j]

      for (let k = 0; k <= permutation.length; k++) {
        const newPermutation = permutation.slice(0, k) + currentChar + permutation.slice(k)
        newPermutations.push(newPermutation)
      }
    }

    permutations = []
    permutations.push(...newPermutations)
  }

  return permutations
}

console.log(getPermutations('ciao'))
