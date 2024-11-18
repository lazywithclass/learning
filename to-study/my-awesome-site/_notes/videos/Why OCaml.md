---
tags:
  - ocaml
---

<iframe width="560" height="315" src="https://www.youtube.com/embed/v1CmGbOGb2I?si=7rxChWRq01D2NnIz" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

	In programming languages data is almost invisible while the code is in plain sight.
	In spreadsheets data is in plain sight while code is almost invisible.

"Variables" in the sense that multiple values could be applied to them.

Example #1 https://www.youtube.com/watch?v=v1CmGbOGb2I&t=1906s

```python
def find_mismatches(d1,d2):
	mismatches = []; 
	for (key,data) in d1.items(): 
		if data != d2[key]: 
			mismatches.append(key) 
	return mismatches 
```

while in OCaml

```ocaml
open Core.Std

let find_mismatches map1 map2 =
	Map.to_sequence map1 |> 
		Sequence.filter_map ~f:(fun (key, data)) ->
		match Map.find map2 key with
			| None -> None
			| Some data' -> if data' <> data then Some key
							else None)
```

`Option` type forces you to take care of it. While it's not the case with `null`s. 

Example #2: https://youtu.be/v1CmGbOGb2I?t=2318

An interpreter written in OCaml, with a type representing the possible choice of the language which happens to be a boolean based language. As he says I don't think this is a great example because OCaml is perfect for this task.

The takeaway point seems to be that syntax and type system's expressiveness go a long way (3 to 1 compression ratio) to help you writing concise code.

I quit the video before the teaching part.