object Test {
  def apply(expr: String) = {
    val parsed = Calculator.parse(expr)
    val evald = Calculator.eval(parsed, List())
    println(s"$parsed -> $evald")
  }
}

// 1 + 2 * 3 - 4
// 4 * 3 + 1 - 8 / 4
// 4 ^ 2 / 2 + 1
