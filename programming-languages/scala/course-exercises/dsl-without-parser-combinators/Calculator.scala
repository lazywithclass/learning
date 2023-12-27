// write a simple algebra interpreter as an internal DSL
//
// I didnt do the internal DSL part because seemed just a function invocation
//
// I am using Shunting Yard algorithm from Mr Dijkstra himself
// https://en.wikipedia.org/wiki/Shunting_yard_algorithm#Graphical_illustration
// I assume only Ints are allowed because I have fuck all interest in doing
// it with Float
// and also no parenthesis

object Calculator {

  def parse(line: String): List[String] = {
    // will I got to robothell 'cause of var?
    var parsed = List[String]()
    var ops = List[String]()
    var tokens = tokenise(line)
    // hello while, long time no see
    while (!tokens.isEmpty) {
      tokens.head.toIntOption match {
        // TODO fix append
        case Some(n) => parsed = parsed ::: List(tokens.head)
        case None    =>
          val (intoParsed, intoOps) = handleOpPrecedence(tokens.head, ops)
          parsed = parsed ::: intoParsed
          ops = intoOps
      }
      tokens = tokens.tail
    }
    parsed ::: ops
  }

  // fst is ops that should go into parsed
  // snd is ops that should become ops
  def handleOpPrecedence(opToken: String, opsTokens: List[String]): Tuple2[List[String], List[String]] = {
    if (opsTokens.isEmpty) {
      return (List(), List(opToken))
    }

    val (opPrec, opAssoc) = Op(opToken)
    val (topOpPrec, _) = Op(opsTokens.head)
    if (opPrec < topOpPrec || (opPrec == topOpPrec && opAssoc.equals("left"))) {
      val (intoParsed, intoOps) = handleOpPrecedence(opToken, opsTokens.tail)
      (opsTokens.head :: intoParsed, intoOps)
    } else {
      (List(), opToken :: opsTokens)
    }
  }

  def eval(expr: List[String], operands: List[Int]): Int = {
    if (expr.length == 1 && operands.isEmpty) {
      return expr(0).toInt
    }

    val hd = expr.head
    hd.toIntOption match {
      case Some(n) => eval(expr.tail, n :: operands)
      case None =>
        hd match {
          case "^" => val res = math.pow(operands(1), operands(0))
            eval(res.toInt.toString :: expr.tail, operands.drop(2))
          case "*" => val res = operands(1) * operands(0)
            eval(res.toString :: expr.tail, operands.drop(2))
          case "/" => val res = operands(1) / operands(0)
            eval(res.toString :: expr.tail, operands.drop(2))
          case "+" => val res = operands(1) + operands(0)
            eval(res.toString :: expr.tail, operands.drop(2))
          case "-" => val res = operands(1) - operands(0)
            eval(res.toString :: expr.tail, operands.drop(2))
        }
    }
  }

  def tokenise(line: String): List[String] = {
    line.split(" ").toList
  }

}
