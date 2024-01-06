import scala.util.parsing.combinator._
import scala.collection.mutable._

// the idea here is to wrap the computation in functions so that
// we prepare them (as thunks) depending on whether they are a number, or a variable
class DeskParser(var table : HashMap[String, Int] = new HashMap[String, Int]) extends JavaTokenParsers{

  def program = "print" ~> exprs ~ ("where" ~> variables) ^^ {
    case exprs ~ _ => println(exprs())
                      table
  }

  def exprs: Parser[() => Int] = expr ~ ("+" ~> exprs) ^^ {
    case f1 ~ f2 => () => f1() + f2()
  } | expr
  def expr = number | value
  def variables = rep(variable)
  def variable = (ident <~ "=") ~ wholeNumber <~ ",?".r ^^ {
    case s ~ n => table += (s -> n.toInt)
  }

  def value = ident ^^ { s => () => table(s) }
  def number = wholeNumber ^^ { n => () => n.toInt }
}

object DeskEvaluator{

  def main(args: Array[String]): Unit = {

    val code = "print x+y+z+1+x+-3 where x = 25, y = 1, z=-7"
    val p = new DeskParser()

    p.parseAll(p.program, code) match {
      case p.Success(a,_) => println(a)
      case x => println(x)
    }
  }
}
