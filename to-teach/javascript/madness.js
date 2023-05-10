function isMad(sentence) {
  const subjects = ["Lui", "Lei", "Egli", "Ella"]
  const verbs = ["are", "ere", "ire"]
  const punct = [',', '.', '!', '?', ':', ';', '-', '~']

  const extras = ['Church', 'mare']

  let isSubjectPresent = subjects.some(subj => sentence.includes(subj))
  let endsHarshly = sentence.endsWith('?!?')
  let aboutCthulhu = sentence.includes('Cthulhu')

  let startsWithVerbs = verbs
      .some(verb => sentence.split(' ')[0].endsWith(verb))
  let endsWithVerbs = verbs.some(verb => sentence.endsWith(verb))
  let startsOrEnds = startsWithVerbs && endsWithVerbs

  let startsWithPunct = punct.some(p => sentence.startsWith(p))

  let isNeverMad = extras.some(e => sentence.includes(e))

  return !isNeverMad && (
    !isSubjectPresent ||
    endsHarshly ||
    aboutCthulhu ||
    startsOrEnds ||
    startsWithPunct)
}


// TODO write "unit tests" using map string -> bool
let tests = {
  ".Quando guardi a lungo nell’abisso, l’abisso ti guarda dentro.": true,
  "Lui e’ pazzo.": false,
  "Lui e’ pazzo?!?": true,
  "Cthulhu fhtagn": true,
  "finire Lui qualcosa finire": true,
  "Lui ama giocare molto": false,
  ".Pazzissima e' Lei": true,
  "~ Pensava sempre al mare come a la mar, come lo chiamano in spagnolo quando lo amano ~": false,
  "~ Papa', come sta Church? ~": false,
}

let res = Object
    .keys(tests)
    .reduce((res, s) => isMad(s) === tests[s] && res, true)
console.log(res)
