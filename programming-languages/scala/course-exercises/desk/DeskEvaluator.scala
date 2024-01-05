import scala.util.parsing.combinator._
import scala.collection.mutable.HashMap

class DeskParser extends JavaTokenParsers {

  override val whiteSpace = " ".r
  override def skipWhitespace = false

  def intv = wholeNumber ^^ { s => s.toInt }
  def expr = ("[a-z]".r | intv)~"[+-]?".r
  def init = (ident<~" ?= ?".r)~intv<~",? ?".r

  def program = ("print "~>rep(expr)<~" where ")~rep(init) ^^ {
    case exprs ~ e => {
      val env = tohashmap(e)
      var tot = 0
      for (e <- exprs) {
        val fn = lookup(e, env)
        tot = fn(tot)
      }
      println(tot)
      println(env)
    }
  }

  def tohashmap(env: List[String ~ Int]): HashMap[String, Int] = {
    env.foldLeft(new HashMap[String, Int]())((newenv, e) => {
                                               e match {
                                                 case k~v => newenv(k) = v
                                               }
                                               newenv
                                             })
  }

  def lookup(expr: Any ~ Any, env: HashMap[String, Int]): Int => Int = {
    expr match {
      case (s: String) ~ "+" => (x: Int) => x + env(s)
      case (s: String) ~ "-" => (x: Int) => x - env(s)
      case (n: Int) ~ "+"    => (x: Int) => x + n
      case (n: Int) ~ "-"    => (x: Int) => x - n
      case "+" ~ (n: Int)    => (x: Int) => x + n
      case "-" ~ (n: Int)    => (x: Int) => x - n
      case x => println(s"DIOMADONNA $x")
                (x: Int) => x
    }
  }
}

object DeskEvaluator {
  def main(args: Array[String]) = {
    // val code = """print x+y+z+1+x+-3 where x = 25, y = 1, z=-7"""
    val code = """print x+y+1+x+-3 where x = 25, y = 1"""

    val parser = new DeskParser()
    val res = parser.parseAll(parser.program, code)
    res match {
      case parser.Success(parsed, _) => println(parsed)
      case parser.Failure(msg, _) => println(s"Failure: $msg")
      case parser.Error(msg, _) => println(s"Error: $msg")
    }
  }
}
