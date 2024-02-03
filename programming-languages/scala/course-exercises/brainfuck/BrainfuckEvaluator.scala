import scala.util.parsing.combinator._
import scala.collection.mutable._


class BrainfuckParser(mem: Array[Int], var p: Int) extends JavaTokenParsers {

  def incp = ">" ^^ { _ => p = p + 1 }
  def decp = "<" ^^ { _ => p = p - 1 }
  def incr = "+" ^^ { _ => mem(p) = mem(p) + 1 }
  def decr = "-" ^^ { _ => mem(p) = mem(p) - 1 }
  def prnt = "." ^^ { _ => println(mem(p).toChar) }
  def read = "," ^^ { _ => mem(p) = scala.io.StdIn.readInt() }
  def whle = "["~>".*".r<~"]" ^^ {
    s => println(s)
  }

  def statement = incp | decp | incr | decr | prnt | read | whle

  def program = rep(statement) ^^ {
    _ => {
      println("Result is:")
      val threshold = 6

      val nums = Range(0, threshold).foldLeft("")((acc, cur) => s"$acc \t $cur")
      println(s"Cell # : $nums")

      val contents = mem.slice(0, threshold).foldLeft("")((acc, cur) => s"$acc \t $cur")
      println(s"Content: $contents")

      println(s"Pointer: $p")
      nums
    }
  }
}

object BrainfuckEvaluator {

  def main(args: Array[String]): Unit = {
    args.foreach(arg => {
                   val src = scala.io.Source.fromFile(arg)
                   val code = src.mkString
                   println(code)

                   val memory = new Array[Int](30000)
                   val parser = new BrainfuckParser(memory, 0)
                   parser.parseAll(parser.program, code) match {
                     case parser.Success(result, _) => println()
                     case parser.Failure(msg, extra) => println(s"Failure: $msg, $extra")
                     case parser.Error(msg, extra) => println(s"Error: $msg, $extra")
                   }
                 })
  }
}
