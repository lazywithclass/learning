import scala.util.parsing.combinator._
import scala.collection.mutable._


class ArnoldCParser(var env: HashMap[String, Int])
    extends JavaTokenParsers {

  def start = "IT'S"~>"SHOW"~>"TIME"
  def stop = "YOU"~>"HAVE"~>"BEEN"~>"TERMINATED"

  def strv = """".*""".r
  def intv = """\d+""".r ^^ { s => s.toInt }

  def print = "TALK TO THE HAND"~>(strv|intv) ^^ { s => println(s) }

  def vardec = "HEY CHRISTMAS TREE"~>ident~("YOU SET US UP"~> intv)^^ { case k~v => env(k) = v }

  // TODO missing case for when we have a variable
  def varass = "GET TO THE CHOPPER"~>ident~("HERE IS MY INVITATION"~>intv)~
    rep(operations | booleanOperations)<~"ENOUGH TALK" ^^ {
      case k ~ v ~ funxs =>
        val res = funxs.foldRight(v)((funx, res) => funx(res))
        env(k) = res
    }

  def sum = "GET UP"~>intv ^^ { op1 => (op2: Int) => op1 + op2 }
  def sub = "GET DOWN"~>intv ^^ { op1 => (op2: Int) => op1 - op2 }
  def mul = "YOU'RE FIRED"~>intv ^^ { op1 => (op2: Int) => op1 * op2 }
  def div = "HE HAD TO SPLIT"~>intv ^^ { op1 => (op2: Int) => op1 / op2 }

  def eq = "YOU ARE NOT YOU YOU ARE ME"~>intv ^^ {
    op1 => (op2: Int) => if(op1 == op2) 1 else 0
  }
  def gt = "LET OFF SOME STEAM BENNET"~>intv ^^ {
    op1 => (op2: Int) => if (op1 > op2) 1 else 0
  }
  def or = "CONSIDER THAT A DIVORCE"~>intv ^^ {
    op1 => (op2: Int) => if ((op1 == 1) || (op2 == 1)) 1 else 0
  }
  def and = "KNOCK KNOCK"~>intv ^^ {
    op1 => (op2: Int) => if ((op1 == 1) && (op2 == 1)) 1 else 0
  }

  // TODO missing case for when we have a variable
  def ifThenElse = ("BECAUSE I'M GOING TO SAY PLEASE"~>intv)~
        print<~
    "BULLSHIT"~>
        print<~
    "YOU HAVE NO RESPECT FOR LOGIC" ^^ {
      case a => println(a)
    }

  def operations = sum | sub | mul | div
  def booleanOperations = eq | gt | or | and

  def instructions = print | vardec | varass | ifThenElse

  def program = start ~> rep(instructions) <~ stop ^^ { case _ => println(env) }
}

object ArnoldCEvaluator {
  def main(args: Array[String]) = {
    val code = scala.io.Source.fromFile(args(0)).mkString
    println(code)

    val parser = new ArnoldCParser(new HashMap[String, Int]())
    parser.parseAll(parser.program, code) match {
      case parser.Success(parsed, _) => println(s"Success: $parsed")
      case parser.Failure(msg, _) => println(s"Failure: $msg")
      case parser.Error(msg, _) => println(s"Error: $msg")
    }

  }
}
