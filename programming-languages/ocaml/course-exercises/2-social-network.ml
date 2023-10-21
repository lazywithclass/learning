type node = Node of string
and entry = Entry of node * node list


(*
   a - b - d - e - i
       |       |
       c       h
      / \
     f   g
*)
let adjacency_list = [
  Entry (Node "a", [Node "b"]);
  Entry (Node "b", [Node "a"; Node "c"; Node "d"]);
  Entry (Node "c", [Node "b"; Node "f"; Node "g"]);
  Entry (Node "d", [Node "b"; Node "e"]);
  Entry (Node "e", [Node "d"; Node "h"; Node "i"]);
  Entry (Node "f", [Node "c"]);
  Entry (Node "g", [Node "c"]);
  Entry (Node "h", [Node "e"]);
  Entry (Node "i", [Node "e"]);
]


(* put these into a Friend module *)

let rec visit pred = function
  | [] -> None
  | (Entry (n, _)) as node :: xs ->
    if pred node
    then Some node
    else visit pred xs

let add_friend subject friend =
  let node = visit (fun node ->
      match node with
      | Entry (Node n, _) -> n = subject
      | _ -> false)
  in

let remove_friend = Fun.id

let get_friends = Fun.id

