import scala.util.parsing.combinator._
import scala.collection.mutable._


// grammar
//
// <field> ::= [^,\n\r]*
// <record> ::= <field> | , <record>
class PrettyPrintingParser(
  var column: Int,
  var longest: HashMap[Int, Int]
) extends JavaTokenParsers {

  override val skipWhitespace = false

  def field = "[^,\n]*".r ^^ {
    f => if (longest.getOrElse(column, 0) < f.length) longest.put(column, f.length)
         column = column + 1
         f
  }
  def record = repsep(field, ",") ^^ {
    r => column = 0
         r
  }

  def p(what: String, amount: Int): Unit = {
    Range(1, amount).foreach(_ => print(what) )
  }

  def program = rep1sep(record, "\n".r) ^^ {
    result =>
      var linum = 0
      // TODO the above took way too much, I dont have desire to fix what follows
      result.foreach(line => {
                       if (line.length > 0) {
                         var colnum = 0
                         if (linum == 0) {
                           val dashes = longest.values
                             .foldLeft(0)((acc, n) => acc + n)
                           p("-", dashes + (result.length * 2) - 2)
                           println("")
                           line.foreach((s) => {
                                          print(s" $s")
                                          p(" ", longest(colnum) - s.length + 1)
                                          print(" |")
                                          colnum = colnum + 1
                                        })
                           println("")
                           p("-", dashes + (result.length * 2) - 2)
                           println("")
                         } else if(linum == result.length - 1) {
                           val dashes = longest.values
                             .foldLeft(0)((acc, n) => acc + n)
                           p("-", dashes + (result.length * 2) - 2)
                         } else {
                           line.foreach((s) => {
                                          print(s" $s")
                                          p(" ", longest(colnum) - s.length + 1)
                                          print(" |")
                                          colnum = colnum + 1
                                        })
                           println("")
                         }
                       }

                       linum = linum + 1
                     })
      result
  }
}

object PrettyPrintingEvaluator {

  def main(args: Array[String]): Unit = {
    val parser = new PrettyPrintingParser(0, new HashMap())
    args.foreach(arg => {
                   val src = scala.io.Source.fromFile(arg)
                   val code = src.mkString
                   println(code)

                   parser.parseAll(parser.program, code) match {
                     case parser.Success(result, _) => result
                     case parser.Failure(msg, extra) => println(s"Failure: $msg, $extra")
                     case parser.Error(msg, extra) => println(s"Error: $msg, $extra")
                   }
                 })
  }

}
