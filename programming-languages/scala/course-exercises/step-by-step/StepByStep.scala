import scala.util.parsing.combinator._


// this could be the grammar, more or less
// <expr>   ::= <number> | <term>
// <term>   ::= ( <expr> ("+"|"-"|""*"|"/") <expr> )
// <number> ::= any number
//
// ex
// 4
// (4 + 4)
// (1 + (4))
// (3 - (1 - 5))

trait Expr {
  def v: Int
}

case class Leaf(n: Int) extends Expr {
  def v = n
}
case class Sum(a: Expr, b: Expr) extends Expr {
  def v = a.v + b.v
}
case class Sub(a: Expr, b: Expr) extends Expr {
  def v = a.v - b.v
}
case class Mul(a: Expr, b: Expr) extends Expr {
  def v = a.v * b.v
}
case class Div(a: Expr, b: Expr) extends Expr {
  def v = a.v / b.v
}


class StepByStepParser() extends JavaTokenParsers {

  // not sure why this was wrong
  // anyway writing the BNF grammanr beforehand looks like a great improvement
  // def expr: Parser[Any] = term~opt(("+"|"-"|"*"|"/")~term)
  // def term: Parser[Any] = num | ("("~>expr<~")")
  // def num = wholeNumber^^{ s => s.toInt}

  def expr: Parser[Expr] = number | add | sub | mul | div
  def add: Parser[Expr] = "("~>(expr~"+"~expr)<~")" ^^ { case a~_~b => Sum(a, b) }
  def sub: Parser[Expr] = "("~>(expr~"-"~expr)<~")" ^^ { case a~_~b => Sub(a, b) }
  def mul: Parser[Expr] = "("~>(expr~"*"~expr)<~")" ^^ { case a~_~b => Mul(a, b) }
  def div: Parser[Expr] = "("~>(expr~"/"~expr)<~")" ^^ { case a~_~b => Div(a, b) }
  def number: Parser[Leaf] = wholeNumber ^^ { s => Leaf(s.toInt) }

  def program = expr
}

object Layer {

  def print(e: Expr): Unit = {
    println(e)
    e match {
      case Leaf(_) =>
      case _       => print(peel(e))
    }
  }

  def peel(e: Expr): Expr = {
    e match {
      case Leaf(a)               => Leaf(a)
      case Sum(a: Leaf, b: Leaf) => Leaf(a.v + b.v)
      case Sum(a, b)             => Sum(peel(a), peel(b))
      case Sub(a: Leaf, b: Leaf) => Leaf(a.v - b.v)
      case Sub(a, b)             => Sub(peel(a), peel(b))
      case Mul(a: Leaf, b: Leaf) => Leaf(a.v * b.v)
      case Mul(a, b)             => Mul(peel(a), peel(b))
      case Div(a: Leaf, b: Leaf) => Leaf(a.v / b.v)
      case Div(a, b)             => Div(peel(a), peel(b))
    }
  }
}

object StepByStepEvaluator {

  def main(args: Array[String]) = {
    val src = scala.io.Source.fromFile(args(0))
    val code = src.mkString

    println(code)

    val parser = new StepByStepParser()
    val parsed = parser.parseAll(parser.program, code)
    parsed match {
      case parser.Success(result, _) => Layer.print(result)
      case parser.Failure(msg, extra) => println(s"Failure: $msg, extra: $extra")
      case parser.Error(msg, _) => println(s"Error: $msg")
    }
  }
}
