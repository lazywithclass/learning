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


// I could've used scala.util.parsing.combinator.JavaTokenParsers
// which allow for easier parsing
object LogLangLexer extends RegexParsers {

  def apply(code: String) = {
    val instrs = remove | rename | merge | backup
    val parsers = task ~> taskId ~ ("{" ~> rep(instrs) <~ "}")
    val res = parse(parsers ^^ { case id ~ instrs => (id, instrs) }, code)
    res match {
      case Success(matched, _) => Some(matched)
      case Failure(msg, _) => None
      case Error(msg, _) => None
    }
  }

  // Tasks
  def task = "task" ^^ (_ => TASK)

  def taskId: Parser[TASK_ID] = """[a-z-A-Z]+""".r ^^ { id => TASK_ID(id) }

  // Instructions
  // error handling is missing, maybe next time
  def filename: Parser[String] = """"[a-zA-Z_+][a-z-A-Z_+0-9.]*[a-z]+"""".r

  def remove: Parser[REMOVE] =
    "remove" ~> filename ^^ { case name => REMOVE(name) }

  def rename: Parser[RENAME] =
    "rename" ~> rep(filename) ^^ { case ns => RENAME(ns(0), ns(1)) }

  def merge: Parser[MERGE] =
    "merge" ~> rep(filename) ^^ { case ns => MERGE("A", List("B", "C")) }

  def backup: Parser[BACKUP] =
    "backup" ~> rep(filename) ^^ { case ns => BACKUP(ns(0), ns(1)) }
}

object LogLangEvaluator {

  def main(args: Array[String]) = {
    val input = """
task TaskOne {
 remove "application.debug.old"
 rename "application.debug" "application.debug.old"
}
"""
    val Some((taskId, instrs)) = LogLangLexer(input)
    println(instrs)

  }

  // here I would ideally run the commands etc
  // I am more interested in the parsing bit

}
