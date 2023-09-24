module Multi
type multi<'a when 'a : comparison> 

val empty : multi<'a> when 'a : comparison
val singleton : x:'a -> multi<'a> when 'a : comparison
val add : x:'a -> multi<'a> -> multi<'a> when 'a : comparison
val cardEl : x:'a -> multi<'a> -> int when 'a : comparison
val count : multi<'a> -> int when 'a : comparison
val contains : s:'a -> multi<'a> -> bool when 'a : comparison
val remove : x:'a -> multi<'a> -> multi<'a> when 'a : comparison
val toList : multi<'a> -> 'a list when 'a : comparison
val ofList : xs:'a list -> multi<'a> when 'a : comparison
