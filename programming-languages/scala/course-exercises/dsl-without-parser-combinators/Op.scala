object Op {
  def apply(op: String): Tuple2[Int, String] = {
    op match {
      case "^" => (4, "right")
      case "*" => (3, "left")
      case "/" => (3, "left")
      case "+" => (2, "left")
      case "-" => (2, "left")
    }
  }
}
