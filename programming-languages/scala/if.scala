
// the idea is to parse and evaluate this code just in one
// step, trying to put the conditional logic outside the if parser


import scala.util.parsing.combinator._

class Parser extends JavaTokenParsers {
  def boolean = "true" | "false"

  def print = "PRINT"~>".+".r ^^ { s => println(s) }

  def ifte = "IF"~>boolean<~"THEN"~>print<~"ELSE"~>print<~"FI"

  def program = rep(print | ifte)

  def optional[T](condition: Boolean, parser: Parser[T]) = (p: Parser[Input]) =>
    if (condition)
      parser(p) else success()
  
}


object Evaluator {

  val code = """
PRINT "before"
IF false THEN
    PRINT "then"
ELSE
    PRINT "else"
FI
"""

  def main(args: Array[String]) = {
    val parser = new Parser()
    val result = parser.parseAll(parser.program, code)
    println(result)
  }
}
