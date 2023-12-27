def readFile(path: String): Iterator[String] = {
  val source = scala.io.Source.fromFile(path)
  source.getLines
}

def replace(line: String): String = {
  line
    .replaceAll("one",   "o1e")
    .replaceAll("two",   "t2o")
    .replaceAll("three", "t3e")
    .replaceAll("four",  "f4")
    .replaceAll("five",  "f5e")
    .replaceAll("six",   "6")
    .replaceAll("seven", "7n")
    .replaceAll("eight", "e8t")
    .replaceAll("nine",  "n9e")
}

def combine(line: String): Int = {
  val digits = line.filter(_.isDigit).map(_.asDigit)
  val res = digits.length match {
    case 0 => 0
    case 1 => (digits.head * 10) + digits.head
    case _ => (digits.head * 10) + digits.last
  }
  println(s"$digits $res")
  res
}

def solution(path: String): Int = {
  readFile(path)
    .map(replace(_))
    .map(combine(_))
    .reduce((sum, single) => sum + single)
}
