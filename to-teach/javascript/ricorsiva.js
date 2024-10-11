function count(from, to) {
  if (from === to) {
    return
  }

  console.log(from)
  count(from + 1, to)
}

count(0, 10)
