---
cssclasses:
  - cornell-note
tags:
  - programming-languages/scala
  - parsing
  - parser-combinators
  - grammars
  - bnf
---

## Run code in the REPL

If 1.scala contains

```scala
def add1(n: Int): Int = n + 1
```

then the following could be run

```bash
scala> :load 1.scala
Loading 1.scala...
add1: (n: Int)Int
scala> add1(5)
res1: Int = 6
```

## Difference `val` and `var`

`val` is used to define constants, `var` is for `var`iables.

## Class

### Case classes

Useful in pattern matching and to build immutable objects.

```scala
case class RGB(r: Int, g: Int, b: Int)
```

## Object

`object` defines a singleton

### Companion

From [the docs](https://docs.scala-lang.org/tour/singleton-objects.html#companion-objects):

	A companion class or object can access the private members of its companion. 
	Use a companion object for methods and values which are not specific to instances of the companion class.

`apply` is a factory for constructing instances of that type, you can then omit `new`

```scala
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

println(MyString("hello", " world"))
println(MyString("hello"))
```

We leverage named parameters in the `apply` function to avoid mistakes, also we do not change the original class' constructor so that interface remains untouched.

`unapply` is used in the context of pattern matching to destructure objects and extract their components.

## Trait

Lego blocks.

## DSL

	With Scala you can use the dual power of OO and functional programming to evolve your domain model. Using the OO features of Scala, you can abstract over types and values, specialize a component through subtyping, and do composition using mixins. You can also use the functional features of Scala through its higher-order functions, closures, and combinators. Finally, you can compose all of this using modules and get your final abstraction.

### Internal

Written idiomatically in the language.

* no `;` in Scala
* type inference
* operators as methods
* optional parentheses

### External

Different parser and language. Uses [Scala parser combinators](https://github.com/scala/scala-parser-combinators).

#### Scala parser combinators

All my exercises could be found [here](https://github.com/lazywithclass/learning/tree/master/programming-languages/scala), under course-exercises you will find lots of examples.

Usually three steps are required:
* lexer - identifies the tokens of the language
* parser - creates the structure using the lexer
* eval - produces a result traversing the parsed data

After a `parse` invocation you can get three results
* `Success` - parser was successfully applied and we get a match 
* `Failure` - parser wasn't successfully applied, maybe because token wasn't found, process will continue
* `Error` - means the process won't continue

Difference between `Failure` and `Error`: process will continue vs process will not continue.

Tip: use `"""regexp-here"""` so you don't have to `\"`.

[Docs](https://javadoc.io/static/org.scala-lang.modules/scala-parser-combinators_2.13/2.1.0/scala/util/parsing/combinator/PackratParsers.html) and [more docs](https://javadoc.io/static/org.scala-lang.modules/scala-parser-combinators_2.13/2.3.0/scala/util/parsing/combinator/Parsers$Parser.html)

Here are the most useful ones:
* `p ~ q` - succeeds if `p` succeeds and `q` succeeds on the input left over by `p`
* `p ~> q` succeeds if `p` succeeds and `q` succeeds on the input left over by `p` (keeps the right result only)
* `p <~ q` - succeeds if `p` succeeds and `q` succeeds on the input left over by `p` (keeps the left result only)
* `p | q` - succeeds if `p` succeeds or `q` succeeds
* `p ^^ f ` - succeeds if `p` succeeds; it returns `f` applied to the result of `p`
* `rep(p)` - repeatedly uses `p` to parse the input until `p` fails
* `repsep(p, q)` - repeatedly uses `p` interleaved with `q` to parse the input, until `p` fails
* `opt(p)` - is a parser that returns `Some(x)` if `p` returns `x` and `None` if `p` fails

Combinators could be used in pattern matching:

```scala
// given for example "c e" as input
def silly = "[a-z]"~"[a-z]"
parseAll(silly, code) match {
  case fst~snd => println(fst, snd)
}
```

When writing a parser for a language I've found it useful to 
* first write the [[BNF]] list of derivation rules
* for each derivation rule write a parser combinator

## Functions and methods

Functions cannot be written without parenthesis.

## Useful stuff

Do not use the REPL. I've seen it misbehave.

**The following saved me hours of bug searching:** while writing combinators it's easy to miss silly mistakes, always print each combinator result before returning the value, this helps a lot when there's a lot going on, follows a simple example 

```scala
def field = ident ^^ { s => println(s); s }
```

### Printing

```scala
val n = 4
val m = 2
println(s"$n $m")
// or the following if you have a method invocation
println(s"${something.method}")
```
