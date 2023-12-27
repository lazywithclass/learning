def readFile(path: String): Iterator[String] = {
  val source = scala.io.Source.fromFile(path)
  source.getLines
}

def combine(line: String): Int = {
  val digits = line.filter(_.isDigit).map(_.asDigit)
  digits.length match {
    case 0 => 0
    case 1 => (digits.head * 10) + digits.head
    case _ => (digits.head * 10) + digits.last
  }
}

def solution(path: String): Int = {
  readFile(path).map(combine(_)).reduce((sum, single) => sum + single)
}
