import scala.util.parsing.combinator._
import scala.collection.mutable._

// grammar
//
// ; I made myself more comfortable by mixing BNF and regexp syntax
// ; will not define "ident" which is a parser per se
//
// <filename> ::= <ident>(.<ident>)+
// <remove>   ::= "remove" <filename>
// <rename>   ::= "rename" <filename> <filename>
// <backup>   ::= "backup" (<filename>)+
// <merge>    ::= "merge" <filename> <filename> <filename>
// <actions>  ::= <remove> | <rename> | <backup> | <merge> | <actions>
// <task>     ::= "task" <ident> { <actions> }

trait Action {
  def exec: String
}

case class Remove(name: String) extends Action {
  def exec = s"removing $name"
}

case class Rename(from: String, to: String) extends Action {
  def exec = s"renaming $from -> $to"
}

case class Backup(names: List[String]) extends Action {
  def exec = s"will backup these $names"
}

case class Merge(first: String, second: String, last: String) extends Action {
  def exec = s"merging $first + $second -> $last"
}

class LogLangv2Parser extends JavaTokenParsers {

  def filename = """"[a-z\+]+(\.[\+a-z]+)+"""".r

  def remove = "remove"~>filename ^^ { s => Remove(s) }
  def rename = "rename"~>filename~filename ^^ { case from~to => Rename(from, to) }
  def backup = "backup"~>rep(filename) ^^ { case ss => Backup(ss) }
  def merge = "merge"~>filename~filename~filename ^^ { case fst~snd~lst => Merge(fst, snd, lst) }

  def actions = rep(remove|rename|backup|merge)

  def task = ("task"~>ident)~("{"~>actions<~"}") ^^ {
    case name~actions => println(s"Task $name")
                         actions.zipWithIndex.map(action => {
                                                    action match {
                                                      case (a, i) => println(s"  [op${i+1}] ${a.exec}")
                                                    }
                                                  })
  }


  def program = rep(task)
}

object LogLangv2Evaluator {

  def main(args: Array[String]): Unit = {
    val src = scala.io.Source.fromFile(args(0))
    val code = src.mkString
    println(code)

    val parser = new LogLangv2Parser()
    val parsed = parser.parseAll(parser.program, code)
    parsed match {
      case parser.Success(result, _) => println("Done")
      case parser.Failure(msg, extra) => println(s"Failure: $msg $extra")
      case parser.Error(msg, extra) => println(s"Error: $msg $extra")
    }
  }
}
