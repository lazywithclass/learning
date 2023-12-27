class MyString(val jString: String) {
  private var extraData = ""
  override def toString: String = jString + extraData
}

object MyString {
  def apply(base: String, extras: String): MyString = {
    val s = new MyString(base)
    s.extraData = extras
    s
  }

  def apply(base: String): MyString = new MyString(base)
}

// see Obsidian notes on this
println(MyString("hello", " world"))
println(MyString("hello"))
