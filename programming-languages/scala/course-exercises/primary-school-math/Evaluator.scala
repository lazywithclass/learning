import scala.util.parsing.combinator._

class Parser(var longest:Int) extends JavaTokenParsers{

  def dottedLine = "[-]+\n".r
  def finalLine=".*".r

  def numbers:Parser[Int] = "[0-9]+".r^^{
    case n => {
      if (n.length + 1 > longest) longest = n.length + 1
      n.toInt
    }
  }

  def op = numbers~rep("+"~numbers|"-"~numbers)^^{
    case x~rest => rest.foldLeft(x) {
      case (left,"+"~right) => left+right
      case (left,"-"~right) => left-right
      case _ => throw new IllegalArgumentException("Error")
    }
  }

  def program = (op<~"=\n")~(dottedLine~>finalLine)^^{
    case expected ~ line => {
      val actual = line.toInt
      if (expected == actual) {
        println("Ok")
      } else {
        println(s"Expected $expected, actal $actual")
      }
    }
  }
}

object Evaluator {

  def main(args: Array[String]) = {
    args.foreach(file => {
                   val src = scala.io.Source.fromFile(file)
                   val code = src.mkString
                   println(code)

                   val parser = new Parser(0)
                   parser.parseAll(parser.program, code) match {
                     case parser.Success(result, _) => println(result)
                     case parser.Failure(msg, extra) => println(s"Failure: $msg, $extra")
                     case parser.Error(msg, extra) => println(s"Error: $msg, $extra")
                   }
                 })
  }

}
