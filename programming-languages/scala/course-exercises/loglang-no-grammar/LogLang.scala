// ------------------
// TOKENS
// ------------------
case class TASK()
case class TASK_ID(s: String)

trait INSTRUCTION
case class BACKUP(s1: String, s2: String) extends INSTRUCTION
case class MERGE(to: String, from: List[String]) extends INSTRUCTION
case class REMOVE(s: String) extends INSTRUCTION
case class RENAME(from: String, to: String) extends INSTRUCTION


// ------------------
// PARSERS
// ------------------
import scala.util.parsing.combinator._


// TODO guarda JavaTokenParsers

object LogLangLexer extends RegexParsers {

  def apply(code: String) = {
    val parsers = task ~> taskId ~ rep(remove | rename | merge | backup)
    val res = parse(parsers ^^ { case id ~ instrs => (id, instrs) }, code)
    res match {
      case Success(matched, _) => LogLangEval(matched)
      case Failure(msg, _) => println(s"lexer failed: $msg")
      case Error(msg, _) => println(s"lexer failed: $msg")
    }
  }

  // Tasks
  def task = "task" ^^ (_ => TASK)

  def taskId: Parser[TASK_ID] = """[a-z-A-Z]+""".r ^^ { id => TASK_ID(id) }


  // Instructions
  // TODO error handling
  def filename: Parser[String] = """"[a-zA-Z_+][a-z-A-Z_+0-9]*\.[a-z]+"""".r

  def remove: Parser[REMOVE] =
    "remove" ~> filename ^^ { case name => REMOVE(name) }

  def rename: Parser[RENAME] =
    "rename" ~> rep(filename) ^^ { case ns => RENAME(ns(0), ns(1)) }

  def merge: Parser[MERGE] =
    "merge" ~> rep(filename) ^^ { case ns => MERGE("A", List("B", "C")) }

  def backup: Parser[BACKUP] =
    "backup" ~> rep(filename) ^^ { case ns => BACKUP(ns(0), ns(1)) }
}

object LogLangEval {

  def apply(task: Tuple2[TASK_ID, List[INSTRUCTION]]) = {
    val taskId = task._1
    val instructions = task._2
    eval(instructions)
  }

  def eval(instrs: List[INSTRUCTION]): Unit = {
    instrs match {
      case head :: tail => println(s"diomadonna $head")
                           eval(tail)
      case List()       => println("done")
    }
  }
}
