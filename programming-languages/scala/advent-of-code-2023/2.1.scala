def readFile(path: String): Iterator[String] = {
  val source = scala.io.Source.fromFile(path)
  source.getLines
}

case class RGB(r: Int, g: Int, b: Int)

def createRGB(token: String): RGB = {
  val tokens = token.split(", ")
  var r = 0
  var g = 0
  var b = 0
  tokens.foreach { token =>
    val s = token.split(" ")
    if (s(1) == "red") r += s(0).toInt
    if (s(1) == "green") g += s(0).toInt
    if (s(1) == "blue") b += s(0).toInt
  }
  RGB(r, g, b)
}

def getRGBAmounts(line: String): Unit = {
  line.split(": ")(1)
    .split("; ")
    .map(colors => createRGB(colors))
}

def solution(path: String): Int = {
  println(path)
  val rgbs = readFile(path)
    .toArray
    .map(getRGBAmounts(_))
    .reduce((totRgb, rgb) => {
              var r = Math.max(rgb.r, totRgb.r)
              var g = Math.max(rgb.g, totRgb.g)
              var b = Math.max(rgb.b, totRgb.b)
              RGB(r, g, b)
            })
  println(s"rgbs $rgbs")
  0
}
