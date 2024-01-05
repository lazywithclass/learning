import scala.util.parsing.combinator._
import scala.collection.mutable._


// possible BNF
//
// <variable> ::= "$[0-9]"
//
// <numpos>   ::= (<variable> | 0)"\+*"
// <numneg>   ::= (<variable> | 0)"-*"
//
// <funname>  ::= "[A-Z]"
// <funexpr>  ::= <app> | <ternary> | <variable> | <numpos> | <numneg>
// <fun>      ::= def <funname> "[0-9]" "=" <funexpr>
//
// <appexpr>  ::= <variable> | <numpos> | <numneg> | <app> | <appexpr>
// <app>      ::= <appexpr> <funname>
//
// <ternexpr> ::= <variable> | <numpos> | <numneg> | <funapp> | <ternexpr>
// <ternary>  ::= <cond> "?" "[" <ternexpr> "]" ":" "[" <ternexpr> "]"


trait Expr {}

case class Variable(pos: Int) extends Expr {}
case class Num(n: Int) extends Expr {}
case class Fun(name: String, args: Int, body: Expr) extends Expr {}
case class App() extends Expr {}

// I assume there are no errors in the .wtf code, no function application without
// function definition, etc
class WTFParser(defs: HashMap[String, Expr], instrs: List[Expr]) extends JavaTokenParsers {

  def variable = "$"~>"[0-9]".r ^^ { v => Variable(v.toInt) }

  def numpos = "0"~>"\\+*".r ^^ { s => Num(s.length) }
  def numneg = "0"~>"[-]*".r ^^ { s => Num(-s.length) }
  // TODO varpos varneg da fare. trattare + e - come function application?

  def funname = "[A-Z]".r
  // TODO aggiungere app ternary e rep
  def funexpr = (numpos|numneg|variable)
  // TODO the body should be parsed at a later time
  def fun = "def"~>(funname~"[0-9]".r<~"=")~funexpr ^^ {
    case name~args~body => defs(name) = Fun(name, args.toInt, body)
  }

  // TODO aggiungere app e appexpr
  def appexpr = rep(variable|numpos|numneg)
  def app = appexpr~funname ^^ {
    case args~name => val fun = defs(name).asInstanceOf[Fun]
      fun.body match {
        case Variable(pos) => args(pos-1)
        case Num(n)        => n
      }
  }

  // TODO consider using other tokens
  // TODO for some reason this doesnt work with negative numbers
  def ternaryCond = variable | numpos | numneg
  def ternaryBranch = variable | app | numpos | numneg
  def ternary = (ternaryCond<~"\\?".r)~("["~>"[^\\]]*".r<~"]"<~":")~("["~>"[^\\]]*".r<~"]") ^^ {
    //                                           ^                            ^
    // we should not evaluate the "then" or "else" branches until we know
    // the value of the condition, we match everything except the ]
    case Variable(v)~t~e => if (v == 0) parseTernary(t) else parseTernary(e)
    case Num(n)~t~e      => if (n == 0) parseTernary(t) else parseTernary(e)
    case x               => println(x)
  }

  def parseTernary(branch: String) = {
    parseAll(ternaryBranch, branch) match {
      case Success(result, _) => result
      // dealing with error? Nah
    }

  }

  def program = rep(fun | app | ternary) ^^ {
    res => // println(s"defs $defs, instrs $instrs, res $res")
           res
  }
}

object WTFEvaluator {

  def main(args: Array[String]): Unit = {
    val src = scala.io.Source.fromFile(args(0))
    val code = src.mkString
    println(code)

    val parser = new WTFParser(new HashMap[String, Expr](), List[Expr]())

    val parsed = parser.parseAll(parser.program, code)
    parsed match {
      case parser.Success(result, _) => println(result)
      case parser.Failure(msg, extra) => println(s"Failure: $msg $extra")
      case parser.Error(msg, _) => println(s"Error: $msg")
    }
  }
}
