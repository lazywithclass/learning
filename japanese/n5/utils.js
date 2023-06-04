// give the random function another couple arguments so that
// we can
//  * return something that hasnt come out yet
//  * return something the user failed to identifiy
export const random = max => Math.floor(Math.random() * max)

export const shuffle = a => {
  let j, x, i
  for (i = a.length - 1; i > 0; i--) {
    j = Math.floor(Math.random() * (i + 1))
    x = a[i]
    a[i] = a[j]
    a[j] = x
  }
  return a;
}
