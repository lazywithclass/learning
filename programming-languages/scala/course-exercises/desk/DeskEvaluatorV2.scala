import scala.util.parsing.combinator._
import scala.collection.mutable._
import scala.util.PropertiesTrait

// grammar
//
// <p>       ::= +
// <m>       ::= -
// <num>     ::= (<p> | <m>)[0-9]+
// <var>     ::= [a-z]
// <varinit> ::= <var> = <num> | , <varinit>
//
// <expr>    ::= (<var> | <num>) (<p> | <m>) | <expr>
//
// <program> ::= print <expr> where <varinit>

trait Expr {}
case class V(name: String) extends Expr
case class N(num: Int) extends Expr

// the idea here is to wrap the computation in types so that
// we "decorate" them based on whether they are a number, or a variable
class DeskParser extends JavaTokenParsers {

  def v = "[a-z]".r ^^ { s => V(s) }
  def n = "-?".r~wholeNumber ^^ {
    case "-"~n => N(-n.toInt)
    case _~n  => N(n.toInt)
  }
  def expr = repsep((v|n), "+")
  def varinit = repsep((v<~"=")~n, ",") ^^ {
    varinits => {
      val acc = new HashMap[String, Int]()
      varinits.foldLeft(acc)((acc, varinit) => {
                               varinit match {
                                 case k~v => acc(k.name) = v.num
                               }
                               acc
                             })
    }
  }

  def program = ("print"~>expr)~("where"~>varinit) ^^ {
    case vars~env => vars.foldLeft(0)((acc, v) => {
                                        v match {
                                          case V(name) => acc+env(name)
                                          case N(num)  => acc+num
                                        }
                                      })
  }
}

object DeskEvaluator {

  def main(args: Array[String]): Unit = {
    args.foreach(arg => {
                   val src = scala.io.Source.fromFile(arg)
                   val code = src.mkString

                   println(code)

                   val parser = new DeskParser()
                   parser.parseAll(parser.program, code) match {
                     case parser.Success(result, _)  => println(result)
                     case parser.Failure(msg, extra) => println(s"Failure: $msg, $extra")
                     case parser.Error(msg, extra)   => println(s"Error: $msg, $extra")
                   }
                 })
  }
}
