import scala.util.parsing.combinator._
import scala.collection.mutable._

// grammar
//
// <number>  ::= wholeNumber
// <sign>    ::= + | -
// <dashes>  ::= - | <dashes>
//
// <line>    ::= <number> <sign> | <sign> <number> | <dashes> | <number>
//
// <program> ::= <line> | <program>

// TODO validate also a wrong order?
class Parser extends JavaTokenParsers {

  var longest: Int = 0

  def number = wholeNumber ^^ {
    s => if (s.length + 2 > longest) longest = s.length + 2
         s.toInt
  }
  def sign = "+"|"-"
  def dashes = repN(longest, "-")

  def ops = number~rep(sign~number) ^^ {
    case x~rest => rest.foldLeft(x) {
      case (result, "+"~right) => result + right
      case (result, "-"~right) => result - right
    }
  }

  def program = (ops<~"=")~(dashes~>("[-]?".r~number)) ^^ {
    case expected~("-"~actual) => expected == -actual
    case expected~actual       => expected == actual
  }
}

object Evaluator {

  def main(args: Array[String]) = {
    args.foreach(file => {
                   val src = scala.io.Source.fromFile(file)
                   val code = src.mkString
                   println(code)

                   val parser = new Parser()
                   parser.parseAll(parser.program, code) match {
                     case parser.Success(result, _) => println(result)
                     case parser.Failure(msg, extra) => println(s"Failure: $msg, $extra")
                     case parser.Error(msg, extra) => println(s"Error: $msg, $extra")
                   }
                 })
  }

}
